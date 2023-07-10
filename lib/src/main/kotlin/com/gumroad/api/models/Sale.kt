package com.gumroad.api.models

import com.gumroad.api.adapters.DayStampAdapter
import com.gumroad.api.models.enums.Recurrence
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Describes a sale from a customer.
 */
data class Sale(
    /** The ID of the sale. */
    @SerializedName("id") val id: String,

    /** The email address of the customer. */
    @SerializedName("email") val email: String,

    /** The ID of the seller. */
    @SerializedName("seller_id") val sellerId: String,

    /** Example: `about 1 month ago` */
    @SerializedName("timestamp") val timestamp: String,

    /** Example: `23 Jan 2021 12:23 PM` */
    @SerializedName("daystamp") @JsonAdapter(DayStampAdapter::class) val daystamp: String,

    /** Actual creation time of the order */
    @SerializedName("created_at") val createdAt: Date,

    /** The name of the product. */
    @SerializedName("product_name") val productName: String,

    /** If true, the product has variants. */
    @SerializedName("product_has_variants") val productHasVariants: Boolean,

    /** The price of the sale. */
    @SerializedName("price") val price: Currency,

    /** The fee that Gumroad took from the sale. */
    @SerializedName("gumroad_fee") val gumroadFee: Currency,

    /** The duration of the subscription, if this is a subscription. */
    @SerializedName("subscription_duration") val subscriptionDuration: Recurrence?,

    /** The formatted display price. */
    @SerializedName("formatted_display_price") val formattedDisplayPrice: String,

    /** The formatted total price. */
    @SerializedName("formatted_total_price") val formattedTotalPrice: String,

    /** The currency symbol. */
    @SerializedName("currency_symbol") val currencySymbol: String,

    /** The amount refundable by the seller. */
    @SerializedName("amount_refundable_in_currency") val amountRefundable: String,

    /** The ID of the product. */
    @SerializedName("product_id") val productId: String,

    /** The permalink of the product. */
    @SerializedName("product_permalink") val productPermalink: String,

    /** True if the seller has partially refunded this sale. */
    @SerializedName("partially_refunded") val partiallyRefunded: Boolean,

    /** True if the customer has charged back this sale. */
    @SerializedName("chargedback") val chargedBack: Boolean,

    /** The email address of the customer. */
    @SerializedName("purchase_email") val purchaseEmail: String,

    /** The zip code of the customer, if present. */
    @SerializedName("zip_code") val zipCode: String?,

    /** True if the customer has paid. */
    @SerializedName("paid") val paid: Boolean,

    /** The variants of the product. */
    @SerializedName("variants") val variants: Map<String, String>,

    /** The chosen variant type by the customer. */
    @SerializedName("variants_and_quantity") val variantsAndQuantity: String,

    /** True if the [customFields] map is not empty. */
    @SerializedName("has_custom_fields") val hasCustomFields: Boolean,

    /** A map of custom fields supplied by the customer. */
    @SerializedName("custom_fields") val customFields: Map<String, String>,

    /** The ID of the order. */
    @SerializedName("order_id") val orderId: Long,

    /** True if the product is a physical good that requires shipment. */
    @SerializedName("is_product_physical") val isProductPhysical: Boolean,

    /** The ID of the purchaser. */
    @SerializedName("purchaser_id") val purchaserId: String,

    /** True if the subscription is recurring. */
    @SerializedName("is_recurring_billing") val isRecurringBilling: Boolean,

    /** True if the customer can contact the seller. */
    @SerializedName("can_contact") val canContact: Boolean,

    /** True if the customer is following the seller. */
    @SerializedName("is_following") val isFollowing: Boolean,

    /** True if the customer disputed the sale. */
    @SerializedName("disputed") val disputed: Boolean,

    /** True if the customer won a dispute against the sale. */
    @SerializedName("dispute_won") val disputeWon: Boolean,

    /** True if this is an additional contribution. */
    @SerializedName("is_additional_contribution") val isAdditionalContribution: Boolean,

    /** True if Gumroad charged fees for the product being listed on [Gumroad Discover](https://gumroad.com/discover). */
    @SerializedName("discover_fee_charged") val discoverFeeCharged: Boolean,

    /** True if Gumroad charged fees for the product being listed on [Gumroad Discover](https://gumroad.com/discover). */
    @SerializedName("is_gift_sender_purchase") val isGiftSenderPurchase: Boolean,
    @SerializedName("is_gift_receiver_purchase") val isGiftReceiverPurchase: Boolean,

    /** The referrer. */
    @SerializedName("referrer") val referrer: String?,

    /** The card used for the purchase. */
    @SerializedName("card") val card: GumroadCard,

    /** The total rating of the product, if present. */
    @SerializedName("product_rating") val productRating: String?,

    /** The total review count of the product. */
    @SerializedName("reviews_count") val reviewsCount: Int,

    /** The average rating of the product. */
    @SerializedName("average_rating") val averageRating: Double,

    /** The ID of the subscription, if present. */
    @SerializedName("subscription_id") val subscriptionId: String?,

    /** True if the subscription is cancelled. */
    @SerializedName("cancelled") val cancelled: Boolean,

    /** True if the subscription is cancelled. */
    @SerializedName("ended") val ended: Boolean,

    /** True if the subscription is a recurring charge. */
    @SerializedName("recurring_charge") val recurringCharge: Boolean,

    /** The license key of the sale, if present. */
    @SerializedName("license_key") val licenseKey: String?,

    /** The license ID, if present. */
    @SerializedName("license_id") val licenseId: String?,

    /** True if the license for this sale is disabled. */
    @SerializedName("license_disabled") val licenseDisabled: Boolean,

    /** The affiliate, if present. */
    @SerializedName("affiliate") val affiliate: Affiliate?,

    /** The offer code used, if present */
    @SerializedName("offer_code") val saleOfferCode: SaleOfferCode?,

    /** The quantity purchased of the product. */
    @SerializedName("quantity") val quantity: Int,

    // This appears to be a duplicate of productHasVariants, excluding it
    // @SerializedName("has_variants") val hasVariants: Boolean
) {
    data class Affiliate(
        /** Email of the affiliate */
        @SerializedName("email") val email: String,

        /** The amount. */
        @SerializedName("amount") val amount: String
    )

    data class SaleOfferCode(
        /** The name of the offer code. */
        @SerializedName("name") val name: String,

        /** The discount the customer received using the offer code. */
        @SerializedName("displayed_amount_off") val displayedAmountOff: String
    )
}