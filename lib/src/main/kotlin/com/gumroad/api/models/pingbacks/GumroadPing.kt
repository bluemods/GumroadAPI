package com.gumroad.api.models.pingbacks

import com.gumroad.api.models.GumroadCard
import com.gumroad.api.models.Currency
import com.gumroad.api.models.enums.Recurrence
import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Used for the [Ping API](https://app.gumroad.com/ping)
 */
data class GumroadPing(
    /** The ID of the sale */
    @SerializedName("sale_id") val saleId: String,

    /** The timestamp of the sale */
    @SerializedName("sale_timestamp") val saleTimestamp: Date,

    /** Numeric version of sale_id */
    @SerializedName("order_number") val orderNumber: Long,

    /** The ID of the seller */
    @SerializedName("seller_id") val sellerId: String,

    /** The ID of the product */
    @SerializedName("product_id") val productId: String,

    /** The permalink of the product */
    @SerializedName("product_permalink") val productPermalink: String,

    /** The short product ID */
    @SerializedName("short_product_id") val shortProductId: String,

    /** The name of the product */
    @SerializedName("product_name") val productName: String,

    /** The email of the buyer */
    @SerializedName("email") val email: String,

    /** A dictionary of the url parameters */
    @SerializedName("url_params") val urlParams: Map<String, String>?,

    /** If present, the name of the buyer */
    @SerializedName("full_name") val fullName: String?,

    /** The ID of the purchaser's Gumroad account, if the purchaser has one */
    @SerializedName("purchaser_id") val purchaserId: String?,

    /** The ID of the subscription, if the purchase is part of a subscription */
    @SerializedName("subscription_id") val subscriptionId: String?,

    /** If present, the country of the buyer's IP address */
    @SerializedName("ip_country") val ipCountryCode: String?,

    /** The price paid, in USD cents */
    @SerializedName("price") val price: Currency,

    /** If present, the recurrence of the payment option chosen by the buyer */
    @SerializedName("recurrence") val recurrence: Recurrence?,

    /** If present, a dictionary */
    @SerializedName("variants") val variants: Map<String, String>?,

    /** If present */
    @SerializedName("offer_code") val offerCode: String?,

    /** If you are buying your own product, for testing purposes */
    @SerializedName("test") val test: Boolean,

    /** If present, a dictionary */
    @SerializedName("custom_fields") val customFields: Map<String, String>?,

    /** If present, a dictionary */
    @SerializedName("shipping_information") val shippingInfo: Map<String, String>?,

    /** If relevant, a boolean */
    @SerializedName("is_recurring_charge") val isRecurringCharge: Boolean,

    /** If relevant, a boolean */
    @SerializedName("is_preorder_authorization") val isPreorderAuthorization: Boolean,

    /** Present if licenses are enabled for the product */
    @SerializedName("license_key") val licenseKey: String?,

    /** The amount of products purchased */
    @SerializedName("quantity") val quantity: Int,

    /** The shipping paid, in USD cents, if the product is a physical product */
    @SerializedName("shipping_rate") val shippingRateCents: Currency?,

    /** If present, the affiliate's email address */
    @SerializedName("affiliate") val affiliate: String?,

    /** If present, the amount paid to the affiliate in USD cents */
    @SerializedName("affiliate_credit_amount_cents") val affiliateCreditAmount: Currency?,

    /** True if a gift, false otherwise */
    @SerializedName("is_gift_receiver_purchase") val isGiftReceiverPurchase: Boolean,

    /** Email address of a gifter */
    @SerializedName("gifter_email") val gifterEmail: String?,

    /** The price paid by the gifter */
    @SerializedName("gift_price") val giftPrice: Currency?,

    /** True if this sale has been refunded, false otherwise */
    @SerializedName("refunded") val refunded: Boolean,

    /** True if this sale was subject to [Gumroad Discover](https://gumroad.com/discover) fees, false otherwise */
    @SerializedName("discover_fee_charged") val discoverFeeCharged: Boolean,

    /** True if the user can be contacted */
    @SerializedName("can_contact") val canContact: Boolean,

    /** The referrer URL that linked the Gumroad site before purchase */
    @SerializedName("referrer") val referrer: String,

    /** Gumroad's fee */
    @SerializedName("gumroad_fee") val gumroadFee: Currency,

    /** Payment instrument details */
    @SerializedName("card") val card: GumroadCard,
)