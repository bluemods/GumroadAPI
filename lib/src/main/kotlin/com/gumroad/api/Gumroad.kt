package com.gumroad.api

import com.gumroad.api.adapters.*
import com.gumroad.api.repos.*
import com.gumroad.api.exceptions.GumroadApiException
import com.gumroad.api.models.Currency
import com.gumroad.api.models.enums.*
import com.gumroad.api.models.pingbacks.*
import com.gumroad.api.utils.FormToJsonConverter
import com.google.gson.*
import com.gumroad.api.exceptions.GumroadApiError
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
     * - Visit the [OAuth application dashboard](https://app.gumroad.com/oauth/applications/)
     * - Create an application. Redirect URI can be set to `http://127.0.0.1` if you are only using this API.
     * - Click "Generate Access Token"
     * - Pass the token to the [accessToken] parameter.
     *
     * If you are only using the [license verification][LicenseRepository.verifyLicense] endpoint,
     * the [accessToken] may be null, as it is not required for that endpoint.
     *
     * @param accessToken the access token from the OAuth dashboard.
     * @param client your custom OkHttpClient to use. This is useful for debugging requests.
     * @return the Gumroad API.
     */
    @JvmStatic
    @JvmOverloads
    fun create(accessToken: String?, client: OkHttpClient = OkHttpClient()): GumroadAPI {
        with(Retrofit.Builder()) {
            baseUrl("https://api.gumroad.com/v2/")
            addConverterFactory(GsonConverterFactory.create(gson))
            client(with(client.newBuilder()) {
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
     * NOTE: the incoming ping must be of `Content-Type: application/x-www-form-urlencoded`
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
     * NOTE: the incoming ping must be of `Content-Type: application/x-www-form-urlencoded`
     * (this is the raw POST body received at your pingback URL)
     *
     * @param urlEncodedData the raw form encoded POST body received from Gumroad
     * @see [GumroadResourcePing]
     * @see [ResourceSubscriptionRepository.createResourceSubscription]
     */
    @JvmStatic
    fun parseResourcePingback(urlEncodedData: String) : GumroadResourcePing {
        return gson.fromJson(FormToJsonConverter.convert(urlEncodedData), GumroadResourcePing::class.java)
    }

    private class GumroadInterceptor(private val accessToken: String?) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val pathSegments = request.url.pathSegments

            val isLicenseVerification = pathSegments.containsAll(listOf("v2", "licenses", "verify"))

            if (accessToken == null && !isLicenseVerification) {
                throw GumroadApiException(401, "The endpoint ${pathSegments.joinToString("/")} requires an accessToken")
            }

            val response: Response = if (isLicenseVerification) {
                // No authentication needed, don't add access_token
                chain.retry(request)
            } else {
                // Request needs authentication.
                // Add the accessToken to the request to authenticate
                val url = request.url.newBuilder().setQueryParameter("access_token", accessToken).build()

                chain.retry(request.newBuilder().url(url).build())
            }
            val code = response.code

            when {
                response.isSuccessful -> return response
                code == 401 -> throw GumroadApiException(code, "The accessToken passed into the Gumroad API is incorrect.")
                else -> {
                    val errorBody = response.body?.string()
                        ?: throw GumroadApiException(code, "Unexpected response code ${response.code} (no body)")

                    val error = if (isLicenseVerification) {
                        // License verification errors have a slightly different format
                        GumroadApiError(code, (JsonParser.parseString(errorBody) as? JsonObject)?.get("message")?.asString ?: "failed to parse JSON")
                    } else {
                        gson.fromJson(errorBody, GumroadApiError::class.java)
                            ?: throw GumroadApiException(code, "Unexpected response code ${response.code} (failed to parse error JSON)")
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
