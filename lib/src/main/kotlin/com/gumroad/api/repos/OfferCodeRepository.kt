package com.gumroad.api.repos

import com.gumroad.api.models.enums.OfferType
import com.gumroad.api.results.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages offer codes.
 */
interface OfferCodeRepository {

    /**
     * Retrieve all existing offer codes for a product.
     *
     * Either amount_cents or percent_off will be returned depending on
     * whether the offer code is a fixed amount off or a percentage off.
     *
     * A universal offer code is one that applies to all products.
     *
     * @param productId the ID of the product.
     */
    @GET("products/{id}/offer_codes")
    fun getOfferCodeList(@Path("id") productId: String): Call<OfferCodeList>

    /**
     * Retrieve the details of a specific offer code of a product.
     *
     * @param productId the ID of the product.
     * @param offerId the ID of the offer.
     */
    @GET("products/{id}/offer_codes/{offerId}")
    fun getOfferCode(@Path("id") productId: String, @Path("offerId") offerId: String): Call<OfferCodeResult>

    /**
     * Create a new offer code for a product.
     *
     * Default offer code is in cents.
     *
     * A universal offer code is one that applies to all products.
     *
     * @param productId the ID of the product.
     * @param name is the coupon code used at checkout.
     * @param amountOff the amount that the offer code will discount the product by.
     * @param offerType the type of the offer.
     * @param maxPurchaseCount the max amount of purchases that can be made with the offer code.
     * @param universal If true, this applies to all products.
     */
    @POST("products/{id}/offer_codes")
    fun createOfferCode(
        @Path("id") productId: String,
        @Query("name") name: String,
        @Query("amount_off") amountOff: Int,
        @Query("offer_type") offerType: OfferType = OfferType.CENTS,
        @Query("max_purchase_count") maxPurchaseCount: Int?,
        @Query("universal") universal: Boolean = false
    ): Call<OfferCodeResult>

    /**
     * Edit an existing product's offer code.
     *
     * @param productId the ID of the product.
     * @param offerId the ID of the offer.
     * @param maxPurchaseCount the **new** max amount of purchases that can be made with the offer code.
     */
    @PUT("products/{id}/offer_codes/{offerId}")
    fun editOfferCode(
        @Path("id") productId: String,
        @Path("offerId") offerId: String,
        @Query("max_purchase_count") maxPurchaseCount: Int?
    ): Call<OfferCodeResult>

    /**
     * Permanently delete a product's offer code.
     *
     * @param productId the ID of the product.
     * @param offerId the ID of the offer.
     */
    @DELETE("products/{id}/offer_codes/{offerId}")
    fun deleteOfferCode(
        @Path("id") productId: String,
        @Path("offerId") offerId: String
    ): Call<GumroadResult>

}