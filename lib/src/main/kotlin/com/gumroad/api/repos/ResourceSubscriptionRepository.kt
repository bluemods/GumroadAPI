package com.gumroad.api.repos

import com.gumroad.api.models.enums.ResourceSubscriptionType
import com.gumroad.api.results.GumroadResult
import com.gumroad.api.results.ResourceSubscriptionList
import com.gumroad.api.results.ResourceSubscriptionResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages resource subscriptions.
 */
interface ResourceSubscriptionRepository {

    /**
     * Show all active subscriptions of the authenticated user for the input resource.
     *
     * @param resourceType the type of subscription resource to get the list for.
     */
    @GET("resource_subscriptions")
    fun getResourceSubscriptionList(
        @Query("resource_name") resourceType: ResourceSubscriptionType
    ): Call<ResourceSubscriptionList>

    /**
     * Subscribe to a resource.
     *
     * See the [API docs](https://app.gumroad.com/api#resource-subscriptions) for more information.
     *
     * @param resourceType the type of resource to subscribe to.
     * @param postUrl the URL that Gumroad will deliver events of [resourceType] to.
     */
    @PUT("resource_subscriptions")
    fun createResourceSubscription(
        @Query("resource_name") resourceType: ResourceSubscriptionType,
        @Query("post_url") postUrl: String
    ): Call<ResourceSubscriptionResult>

    /**
     * Unsubscribe from a resource.
     *
     * @param subscriptionId the ID of the subscription to unsubscribe from.
     */
    @DELETE("resource_subscriptions/{id}")
    fun deleteResourceSubscription(@Path("id") subscriptionId: String): Call<GumroadResult>

}