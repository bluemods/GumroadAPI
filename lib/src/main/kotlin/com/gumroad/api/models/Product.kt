package com.gumroad.api.models

import com.gumroad.api.models.enums.Recurrence
import com.google.gson.annotations.SerializedName

/**
 * Describes a product.
 */
data class Product(
    /** The custom permalink of the product. */
    @SerializedName("custom_permalink") val customPermalink: String?,

    /** The custom receipt of the product. */
    @SerializedName("custom_receipt") val customReceipt: String?,

    /** The custom summary of the product. */
    @SerializedName("custom_summary") val customSummary: String?,

    /** Custom fields for the product. */
    @SerializedName("custom_fields") val customFields: List<CustomField>,

    /** True if the product price is customizable by the customer */
    @SerializedName("customizable_price") val customizablePrice: Boolean,

    /** The description of the product. */
    @SerializedName("description") val description: String,

    /** True if the product is deleted. */
    @SerializedName("deleted") val deleted: Boolean,

    /** The max purchase count of the product. */
    @SerializedName("max_purchase_count") val maxPurchaseCount: Int,

    /** The name of the product. */
    @SerializedName("name") val name: String,

    /** The preview url of the product, if present. */
    @SerializedName("preview_url") val previewUrl: String?,

    /** True if this is a physical product that requires shipping. */
    @SerializedName("require_shipping") val requireShipping: Boolean,

    /** If this is a subscription */
    @SerializedName("subscription_duration") val subscriptionDuration: Recurrence?,

    /** True if this product is published (publicly viewable) */
    @SerializedName("published") val published: Boolean,

    /** The URL of the product. */
    @SerializedName("url") val url: String,

    /** The ID of the product. */
    @SerializedName("id") val productId: String,

    /** The price of the product. */
    @SerializedName("price") val price: Currency,

    /** The parity prices for the product. */
    @SerializedName("purchasing_power_parity_prices") val parityPrices: ParityPrices,

    /** The currency type used for the product, e.g. `usd` */
    @SerializedName("currency") val currency: String,

    /** The short url of the product */
    @SerializedName("short_url") val shortUrl: String,

    /** The thumbnail url for the product */
    @SerializedName("thumbnail_url") val thumbnailUrl: String,

    /** The tags for the product */
    @SerializedName("tags") val tags: List<String>,

    /** The formatted price of the product, e.g. `$1` */
    @SerializedName("formatted_price") val formattedPrice: String,

    /** True if the product is shown on the sellers profile. */
    @SerializedName("shown_on_profile") val shownOnProfile: Boolean,

    /** A dictionary describing a file delivered upon purchase. Known parameters are `Size` (size of file), `Resolution` (if an image) */
    @SerializedName("file_info") val fileInfo: Map<String, String>,

    /** The amount of sales this product has made. */
    @SerializedName("sales_count") val salesCount: Long,

    /** The total revenue this product has generated. */
    @SerializedName("sales_usd_cents") val salesTotal: Currency,

    /** True if this is a tiered membership. */
    @SerializedName("is_tiered_membership") val isTieredMembership: Boolean,

    /** if [isTieredMembership] is true, this is a list of available subscription durations; otherwise null. */
    @SerializedName("recurrences") val recurrences: List<Recurrence>?,

    /** A list of variants for this product. */
    @SerializedName("variants") val productVariants: List<ProductVariant>?
) {

    data class ParityPrices(
        @SerializedName("US") val usPrice: Currency,
        @SerializedName("IN") val indiaPrice: Currency,
        @SerializedName("EU") val euPrice: Currency,
    )

    data class ProductVariant(
        /** The title of the variant. */
        @SerializedName("title") val title: String,

        /** The options for the variant. */
        @SerializedName("options") val options: List<VariantOption>,
    ) {
        data class VariantOption(
            /** The name of the variant option. */
            @SerializedName("name") val name: String,

            /** The price difference, set for non-membership product options. */
            @SerializedName("price_difference") val priceDifference: Int,

            /** True if the customer can choose the price of the variant. */
            @SerializedName("is_pay_what_you_want") val isPayWhatYouWant: Boolean,

            /** Present for membership products; otherwise null */
            @SerializedName("recurrence_prices") val recurrencePrices: Map<String, RecurrencePrice>?,
        ) {
            data class RecurrencePrice(
                /** The recurrence price. */
                @SerializedName("price_cents") val price: Currency,

                /** The suggested price. May be present if [VariantOption.isPayWhatYouWant] is true. */
                @SerializedName("suggested_price_cents") val suggestedPrice: Currency?
            )
        }
    }
}
