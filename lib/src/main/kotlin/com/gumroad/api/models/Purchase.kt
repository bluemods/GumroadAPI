package com.gumroad.api.models

import com.google.gson.annotations.SerializedName
import com.gumroad.api.models.enums.Recurrence
import java.util.*

/**
 * Describes a licensed purchase made by a customer.
 *
 * All licensed purchases have a [License Key][licenseKey] associated with them,
 * and are used to verify purchases.
 */
data class Purchase(
    /** The ID of the seller. */
    @SerializedName("seller_id") val sellerId: String,

    /** The ID of the product. */
    @SerializedName("product_id") val productId: String,

    /** The name of the product. */
    @SerializedName("product_name") val productName: String,

    /** The permalink of the product. */
    @SerializedName("permalink") val permalinkId: String,

    /** The permalink of the product, in URL form. */
    @SerializedName("product_permalink") val productPermalink: String,

    /** The short product ID. */
    @SerializedName("short_product_id") val shortProductId: String,

    /** The email address of the customer. */
    @SerializedName("email") val buyerEmail: String,

    /** The price of the purchase. */
    @SerializedName("price") val price: Currency,

    /** The fee that Gumroad took from the sale. */
    @SerializedName("gumroad_fee") val gumroadFee: Currency,

    /** The currency type used for the product, e.g. `usd` */
    @SerializedName("currency") val currency: String,

    /** The quantity purchased of the product. */
    @SerializedName("quantity") val quantity: Int,

    /** True if this sale was subject to [Gumroad Discover](https://gumroad.com/discover) fees, false otherwise */
    @SerializedName("discover_fee_charged") val discoverFee: Boolean,

    /** True if the customer can contact the seller. */
    @SerializedName("can_contact") val canContact: Boolean,

    /**
     * The referrer that led to the purchase.
     *
     * This may be a URL if from an external source,
     * or may be an arbitrary string such as `direct` if not from an external source.
     */
    @SerializedName("referrer") val referrer: String,

    /** The card used to make the purchase. */
    @SerializedName("card") val card: GumroadCard,

    /** Numeric version of [saleId] */
    @SerializedName("order_number") val orderNumber: Long,

    /** The ID of the sale. */
    @SerializedName("sale_id") val saleId: String,

    /** Time that the purchase was made. */
    @SerializedName("sale_timestamp") val saleTimestamp: Date,

    /** The numeric ID of the purchaser. */
    @SerializedName("purchaser_id") val purchaserId: String,

    /** The ID of the subscription, if this is a subscription. */
    @SerializedName("subscription_id") val subscriptionId: String?,

    /** The variant selected for the purchase. Will be an empty string if no variant was selected. */
    @SerializedName("variants") val variants: String,

    /** The license key of the purchase. */
    @SerializedName("license_key") val licenseKey: String,

    /** True if this is a multi seat license. */
    @SerializedName("is_multiseat_license") val isMultiSeatLicense: Boolean,

    /** The country of the buyer's IP address. */
    @SerializedName("ip_country") val ipCountry: String,

    /** If present, the recurrence of the subscription, chosen by the customer */
    @SerializedName("recurrence") val recurrence: Recurrence?,

    /** True if this purchase is a gift, and the gift receiver made a subsequent purchase. */
    @SerializedName("is_gift_receiver_purchase") val isGiftReceiverPurchase: Boolean,

    /** True if the purchase has been refunded. */
    @SerializedName("refunded") val refunded: Boolean,

    /** True if the customer disputed the sale. */
    @SerializedName("disputed") val disputed: Boolean,

    /** True if the customer won a dispute against the sale. */
    @SerializedName("dispute_won") val disputeWon: Boolean,

    /** The creation time of the purchase. */
    @SerializedName("created_at") val createdAt: Date,

    /** Custom fields associated with the purchase. */
    @SerializedName("custom_fields") val customFields: List<Map<String, String>>,

    /**
     * True if the purchase is refunded.
     *
     * This is relevant for **non-subscription** products only.
     */
    @SerializedName("chargebacked") val chargebacked: Boolean,

    /**
     * If present, this is the timestamp of when the subscription was ended.
     *
     * This is relevant for **non-subscription** products only.
     */
    @SerializedName("subscription_ended_at") val subscriptionEndedAt: Date?,

    /**
     * If present, this is the timestamp of a subscription cancellation.
     *
     * This is relevant for **non-subscription** products only.
     */
    @SerializedName("subscription_cancelled_at") val subscriptionCancelledAt: Date?,

    /**
     * If present, this is the timestamp of a subscription failure
     * due to Gumroad being unable to charge the subscriber's card.
     *
     * This is relevant for **subscription** products only.
     */
    @SerializedName("subscription_failed_at") val subscriptionFailedAt: Date?,

    // Removed: duplicate of "sale_id" @SerializedName("id") val id: String,
)