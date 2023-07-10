package com.gumroad.api.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Describes a purchase made by a customer.
 */
data class Purchase(
    @SerializedName("sale_id") val saleId: String,
    @SerializedName("product_permalink") val productLink: String,
    @SerializedName("can_contact") val canContact: Boolean,
    @SerializedName("dispute_won") val disputeWon: Boolean,
    @SerializedName("is_gift_receiver_purchase") val isGiftReceiver: Boolean,
    @SerializedName("order_number") val orderNumber: Int,
    @SerializedName("ip_country") val ipCountry: String,
    @SerializedName("short_product_id") val shortProductId: String,
    @SerializedName("discover_fee_charged") val discoverFee: Boolean,
    @SerializedName("price") val price: Double,
    @SerializedName("product_id") val productId: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("refunded") val refunded: Boolean,
    @SerializedName("seller_id") val sellerId: String,
    @SerializedName("email") val buyerEmail: String,
    @SerializedName("disputed") val disputed: Boolean,
    @SerializedName("gumroad_fee") val gumroadFee: Currency,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("sale_timestamp") val saleTimestamp: Date,
    @SerializedName("custom_fields") val customFields: List<Map<String, String>>,
    @SerializedName("product_name") val productName: String,
    @SerializedName("chargebacked") val chargebacked: Boolean,
    @SerializedName("license_key") val licenseKey: String,
    @SerializedName("referrer") val referrer: String,
    @SerializedName("card") val card: GumroadCard
)