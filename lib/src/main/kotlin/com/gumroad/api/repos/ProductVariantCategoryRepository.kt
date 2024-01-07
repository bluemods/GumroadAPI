package com.gumroad.api.repos

import com.gumroad.api.results.GumroadResult
import com.gumroad.api.results.VariantCategoryList
import com.gumroad.api.results.VariantCategoryResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages product variant categories.
 */
interface ProductVariantCategoryRepository {

    /**
     * Retrieve all existing variant categories of a product.
     *
     * @param productId the ID of the product.
     */
    @GET("products/{productId}/variant_categories")
    fun getVariantCategoryList(
        @Path("productId") productId: String
    ): Call<VariantCategoryList>

    /**
     * Retrieve the details of a variant category of a product.
     *
     * @param productId the ID of the product.
     * @param categoryId the ID of the product category.
     */
    @GET("products/{productId}/variant_categories/{categoryId}")
    fun getVariantCategory(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String
    ): Call<VariantCategoryResult>

    /**
     * Create a new variant category on a product.
     *
     * @param productId the ID of the product.
     * @param categoryTitle the title of the new product variant category.
     */
    @POST("products/{productId}/variant_categories")
    fun createVariantCategory(
        @Path("productId") productId: String,
        @Query("title") categoryTitle: String
    ): Call<VariantCategoryResult>

    /**
     * Edit a variant category of an existing product.
     *
     * @param productId the ID of the product to edit.
     * @param categoryId the ID of the product category to edit.
     * @param categoryTitle the **new** title of the new product variant category.
     */
    @PUT("products/{productId}/variant_categories/{categoryId}")
    fun editVariantCategory(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String,
        @Query("title") categoryTitle: String
    ): Call<VariantCategoryResult>

    /**
     * Permanently delete a variant category of a product.
     *
     * @param productId the ID of the product to delete.
     * @param categoryId the ID of the product category to delete.
     */
    @DELETE("products/{productId}/variant_categories/{categoryId}")
    fun deleteVariantCategory(
        @Path("productId") productId: String,
        @Path("categoryId") categoryId: String
    ): Call<GumroadResult>

}