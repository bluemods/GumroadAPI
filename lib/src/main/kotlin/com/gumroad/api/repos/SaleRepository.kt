package com.gumroad.api.repos

import com.gumroad.api.results.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages sales.
 */
interface SaleRepository {

    /**
     * Retrieves all of the successful sales for the authenticated user. Available with the 'view_sales' scope.
     *
     * @param before (optional, date in form YYYY-MM-DD) - Only return sales before this date
     * @param after (optional, date in form YYYY-MM-DD) - Only return sales after this date
     * @param email (optional) - Filter sales by this email
     * @param productId (optional) - Filter sales by this product
     * @param orderId (optional) - Filter sales by this Order ID
     * @param pageKey (optional) - A key representing a page of results. It is given in the response as [SaleList.nextPageKey].
     */
    @GET("sales")
    fun getSaleList(
        @Query("before") before: String? = null,
        @Query("after") after: String? = null,
        @Query("email") email: String? = null,
        @Query("product_id") productId: String? = null,
        @Query("order_id") orderId: String? = null,
        @Query("page_key") pageKey: String? = null,
    ): Call<SaleList>

    /**
     * Retrieves the details of a sale by this user. Available with the 'view_sales' scope.
     *
     * @param saleId the ID of the sale to retrieve information for.
     */
    @GET("sales/{id}")
    fun getSale(@Path("id") saleId: String): Call<SaleResult>

    /**
     * Marks a sale as shipped. Available with the 'mark_sales_as_shipped' scope.
     *
     * @param saleId the sale ID.
     * @param trackingUrl the optional tracking URL.
     */
    @PUT("sales/{id}/mark_as_shipped")
    fun markSaleAsShipped(@Path("id") saleId: String, @Path("tracking_url") trackingUrl: String?): Call<SaleResult>

    /**
     * Refunds a sale. Available with the 'refund_sales' scope.
     *
     * @param saleId the sale ID.
     * @param amountCents (optional) - Amount in cents (in currency of the sale) to be refunded.
     *
     * If set, issue partial refund by this amount.
     *
     * If not set, issue full refund.
     *
     * You can issue multiple partial refunds per sale until it is fully refunded.
     */
    @PUT("sales/{id}/refund")
    fun refundSale(@Path("id") saleId: String, @Path("amount_cents") amountCents: Long?): Call<SaleResult>

}