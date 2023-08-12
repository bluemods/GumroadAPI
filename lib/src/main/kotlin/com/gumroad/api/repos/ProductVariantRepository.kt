package com.gumroad.api.repos

import com.gumroad.api.results.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages product variants.
 */
interface ProductVariantRepository {

    /**
     * Retrieve all of the existing variants in a variant category.
     *
     * @param productId the product ID.
     * @param categoryId the category ID.
     */
    @GET("products/{productId}/variant_categories/{categoryId}/variants")
    fun getVariantList(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
    ): Call<VariantList>

    /**
     * Retrieve the details of a variant of a product.
     *
     * @param productId the product ID.
     * @param categoryId the category ID.
     * @param variantId the variant ID.
     */
    @GET("products/{productId}/variant_categories/{categoryId}/variants/{variantId}")
    fun getVariant(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
        @Path("variantId") variantId: String
    ): Call<VariantResult>

    /**
     * Create a new variant of a product.
     *
     * @param productId the product ID.
     * @param categoryId the category ID.
     * @param name the name of the variant.
     * @param priceDifferenceCents the price difference, in USD cents.
     * @param maxPurchaseCount the max purchase count for the variant. If unspecified, the purchase count is unlimited.
     */
    @POST("products/{productId}/variant_categories/{categoryId}/variants")
    fun createVariant(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
        @Query("name") name: String,
        @Query("price_difference_cents") priceDifferenceCents: Long,
        @Query("max_purchase_count") maxPurchaseCount: Int? = null
    ): Call<VariantResult>

    /**
     * Edit a variant of an existing product.
     *
     * @param productId the product ID.
     * @param categoryId the category ID.
     * @param variantId the variant ID to edit.
     * @param name the name of the variant.
     * @param priceDifferenceCents the price difference, in USD cents.
     * @param maxPurchaseCount the max purchase count for the variant. If unspecified, the purchase count is unlimited.
     */
    @PUT("products/{productId}/variant_categories/{categoryId}/variants/{variantId}")
    fun editVariant(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
        @Path("variantId") variantId: String,
        @Query("name") name: String,
        @Query("price_difference_cents") priceDifferenceCents: Long,
        @Query("max_purchase_count") maxPurchaseCount: Int? = null
    ): Call<VariantResult>

    /**
     * Permanently delete a variant of a product.
     *
     * @param productId the product ID.
     * @param categoryId the category ID.
     * @param variantId the variant ID to delete.
     */
    @DELETE("products/{productId}/variant_categories/{categoryId}/variants/{variantId}")
    fun deleteVariant(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
        @Path("variantId") variantId: String
    ): Call<GumroadResult>

}