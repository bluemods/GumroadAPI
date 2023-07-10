package com.gumroad.api.repos

import com.gumroad.api.results.SubscriberList
import com.gumroad.api.results.SubscriberResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages subscribers.
 */
interface SubscriberRepository {

    /**
     * Retrieves all of the active subscribers for one of the authenticated user's products.
     *
     * Available with the 'view_sales' scope
     *
     * A subscription is terminated if any of failed_at, ended_at, or cancelled_at timestamps
     * are populated and are in the past.
     *
     * @param productId The product ID to get subscribers for.
     * @param email (optional) - Filter subscribers by this email
     */
    @GET("products/{id}/subscribers")
    fun getSubscribers(@Path("id") productId: String, @Query("email") email: String?): Call<SubscriberList>

    /**
     * Retrieves the details of a subscriber to this user's product.
     *
     * Available with the 'view_sales' scope.
     *
     * @param subscriberId The subscriber ID to get details of.
     */
    @GET("subscribers/{id}")
    fun getSubscriber(@Path("id") subscriberId: String): Call<SubscriberResult>

}