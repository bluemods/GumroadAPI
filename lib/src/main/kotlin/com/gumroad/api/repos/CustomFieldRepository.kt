package com.gumroad.api.repos

import com.gumroad.api.results.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages custom fields on products.
 */
interface CustomFieldRepository {

    /**
     * Retrieve all existing custom fields for a product.
     *
     * @param productId the ID of the product.
     */
    @GET("products/{id}/custom_fields")
    fun getCustomFieldList(@Path("id") productId: String): Call<CustomFieldList>

    /**
     * Create a new custom field for a product.
     *
     * @param productId the ID of the product.
     * @param fieldName the new field name.
     * @param required if true, the customer is required to enter a value in this field before purchasing.
     */
    @POST("products/{id}/custom_fields")
    fun createCustomField(
        @Path("id") productId: String,
        @Query("name") fieldName: String,
        @Query("required") required: Boolean = false
    ): Call<CustomFieldResult>

    /**
     * Edit an existing product's custom field.
     *
     * @param productId the ID of the existing product.
     * @param fieldName the field name to edit.
     * @param required if true, the customer is required to enter a value in this field before purchasing.
     */
    @PUT("products/{id}/custom_fields/{name}")
    fun editCustomField(
        @Path("id") productId: String,
        @Query("name") fieldName: String,
        @Query("required") required: Boolean
    ): Call<CustomFieldResult>

    /**
     * Permanently delete a product's custom field.
     *
     * @param productId the ID of the existing product.
     * @param fieldName the field name to delete.
     */
    @DELETE("products/{id}/custom_fields")
    fun deleteCustomField(@Path("id") productId: String, @Query("name") fieldName: String): Call<GumroadResult>

}