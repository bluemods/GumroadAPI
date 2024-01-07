package com.gumroad.api.repos

import com.gumroad.api.results.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages products.
 */
interface ProductRepository {

    /**
     * Retrieve all existing products for the authenticated user.
     */
    @GET("products")
    fun getProductsList(): Call<ProductList>

    /**
     * Retrieve the details of a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     */
    @GET("products/{id}")
    fun getProductById(@Path("id") id: String): Call<ProductResult>

    /**
     * Enable an existing product.
     *
     * @param id the ID of the product to enable.
     */
    @PUT("products/{id}/enable")
    fun enableProduct(@Path("id") id: String): Call<ProductResult>

    /**
     * Disable an existing product.
     *
     * @param id the ID of the product to disable.
     */
    @PUT("products/{id}/disable")
    fun disableProduct(@Path("id") id: String): Call<ProductResult>

    /**
     * Permanently delete a product.
     *
     * @param id the ID of the product to delete.
     */
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: String): Call<GumroadResult>

}