package com.gumroad.api.models

import com.gumroad.api.models.enums.Recurrence
import com.google.gson.annotations.SerializedName

/**
 * Describes a product.
 */
data class Product(
    @SerializedName("custom_permalink") val customPermalink: String?,
    @SerializedName("custom_receipt") val customReceipt: String?,
    @SerializedName("custom_summary") val customSummary: String?,
    @SerializedName("custom_fields") val customFields: List<CustomField>,
    @SerializedName("description") val description: String,
    @SerializedName("deleted") val deleted: Boolean,
    @SerializedName("max_purchase_count") val maxPurchaseCount: Int,
    @SerializedName("name") val name: String,
    @SerializedName("preview_url") val previewUrl: String?,
    @SerializedName("require_shipping") val requireShipping: Boolean?,
    @SerializedName("subscription_duration") val subscriptionDuration: Recurrence?,
    @SerializedName("published") val published: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("id") val productId: String,
    @SerializedName("price") val price: Currency,
    @SerializedName("purchasing_power_parity_prices") val parityPrices: ParityPrices,
    @SerializedName("currency") val currency: String,
    @SerializedName("short_url") val shortUrl: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("formatted_price") val formattedPrice: String,
    @SerializedName("shown_on_profile") val shownOnProfile: Boolean,
    @SerializedName("sales_count") val salesCount: Long,
    @SerializedName("sales_usd_cents") val salesTotal: Currency,
    @SerializedName("is_tiered_membership") val isTieredMembership: Boolean,
    @SerializedName("recurrences") val recurrences: List<String>,
    @SerializedName("variants") val productVariants: List<ProductVariant>,

    // @SerializedName("customizable_price") val customizablePrice: Any, // TODO: type not documented
    // @SerializedName("file_info") val fileInfo: JsonObject, // TODO: type not documented
) {

    data class ParityPrices(
        @SerializedName("US") val usPrice: Currency,
        @SerializedName("IN") val indiaPrice: Currency,
        @SerializedName("EU") val euPrice: Currency,
    )

    data class ProductVariant(
        @SerializedName("title") val title: String,
        @SerializedName("options") val options: List<VariantOption>,
    ) {
        data class VariantOption(
            @SerializedName("name") val name: String,
            @SerializedName("price_difference") val priceDifference: Int,
            @SerializedName("is_pay_what_you_want") val isPayWhatYouWant: Boolean,
            @SerializedName("recurrence_prices") val recurrencePrices: Map<String, RecurrencePrice>,
        ) {
            data class RecurrencePrice(
                @SerializedName("price_cents") val price: Currency,
                @SerializedName("suggested_price_cents") val suggestedPrice: Currency?
            )
        }
    }
}
