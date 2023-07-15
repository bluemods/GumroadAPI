package com.gumroad.api

import com.gumroad.api.adapters.*
import com.gumroad.api.repos.*
import com.gumroad.api.exceptions.GumroadApiException
import com.gumroad.api.models.Currency
import com.gumroad.api.models.enums.*
import com.gumroad.api.models.pingbacks.*
import com.gumroad.api.results.GumroadResult
import com.gumroad.api.utils.FormToJsonConverter
import com.google.gson.*
import okhttp3.*
import okhttp3.Response
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object Gumroad {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Currency::class.java, CurrencyAdapter())
        .registerTypeAdapter(Recurrence::class.java, RecurrenceAdapter())
        .registerTypeAdapter(ResourceSubscriptionType::class.java, ResourceSubscriptionTypeAdapter())
        .registerTypeAdapter(SubscriberStatus::class.java, SubscriberStatusAdapter())
        .registerTypeAdapter(SubscriptionEndReason::class.java, SubscriptionEndReasonAdapter())
        .registerTypeAdapter(SubscriptionUpdateType::class.java, SubscriptionUpdateTypeAdapter())
        .create()

    /**
     * Creates an instance of the Gumroad API.
     *
     * It is recommended to create only one instance for each account and reuse it, as this will improve performance.
     *
     * To obtain an access token, do the following:
     *
     * - Visit https://app.gumroad.com/oauth/applications/
     * - Create an application. Redirect URI can be set to `http://127.0.0.1` if you are only using this API.
     * - Click "Generate Access Token"
     * - Pass the token to the [accessToken] parameter.
     *
     * @param accessToken the access token from the OAuth dashboard.
     * @return the Gumroad API.
     */
    @JvmStatic
    fun create(accessToken: String?): GumroadAPI {
        with(Retrofit.Builder()) {
            baseUrl("https://api.gumroad.com/v2/")
            addConverterFactory(GsonConverterFactory.create(gson))
            client(with(OkHttpClient.Builder()) {
                callTimeout(Duration.ofSeconds(10))
                addInterceptor(GumroadInterceptor(accessToken))
                build()
            })
            return build().create(GumroadAPI::class.java)
        }
    }

    /**
     * Parses a pingback from Gumroad, [described here](https://app.gumroad.com/ping).
     *
     * NOTE: the incoming ping must be of `ContentType: x-www-formurlencoded`
     * (this is the raw POST body received at your pingback URL)
     *
     * @param urlEncodedData the raw form encoded POST body received from Gumroad
     * @see [GumroadPing]
     */
    @JvmStatic
    fun parsePingback(urlEncodedData: String) : GumroadPing {
        return gson.fromJson(FormToJsonConverter.convert(urlEncodedData), GumroadPing::class.java)
    }

    /**
     * Parses a resource pingback from Gumroad, previously activated through a resource subscription
     *
     * @see [GumroadResourcePing]
     * @see [ResourceSubscriptionRepository.createResourceSubscription]
     */
    @JvmStatic
    fun parseResourcePingback(json: String) : GumroadResourcePing {
        return gson.fromJson(json, GumroadResourcePing::class.java)
    }

    private class GumroadInterceptor(private val accessToken: String?) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val pathSegments = request.url.pathSegments

            val requireAuth = !pathSegments.containsAll(listOf("licenses", "verify"))

            if (accessToken == null && requireAuth) {
                throw GumroadApiException(GumroadResult().apply {
                    message = "The endpoint ${pathSegments.joinToString("/")} requires an accessToken"
                })
            }

            val response: Response = if (accessToken == null || !requireAuth) {
                // No authentication needed, don't add access_token
                chain.retry(request)
            } else {
                // Request needs authentication.
                // Add the accessToken to the request to authenticate
                val url = request.url.newBuilder().setQueryParameter("access_token", accessToken).build()

                chain.retry(request.newBuilder().url(url).build())
            }

            when {
                response.isSuccessful -> {
                    return response
                }
                response.code == 401 -> {
                    throw GumroadApiException(GumroadResult().apply { message = "THe accessToken passed into the Gumroad API is incorrect." })
                }
                else -> {
                    val error = gson.fromJson(response.body?.string(), GumroadResult::class.java)
                        ?: GumroadResult().apply {
                            message = "Unexpected response code ${response.code}"
                        }
                    throw GumroadApiException(error)
                }
            }
        }

        private fun Interceptor.Chain.retry(request: Request): Response {
            var response = proceed(request)
            var retryCount = 0
            while (response.code in 500..504 && retryCount < 3) {
                response.close()
                retryCount++
                response = proceed(request)
            }
            return response
        }
    }
}
