package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

/**
 * Describes a variant of a product.
 */
data class Variant(
    /**
     * The ID of the variant.
     */
    @SerializedName("id") val id: String,

    /**
     * The name of the variant.
     */
    @SerializedName("name") val name: String,

    /**
     * The max purchase count.
     */
    @SerializedName("max_purchase_count") val maxPurchaseCount: Int,

    /**
     * The price difference of the variant.
     */
    @SerializedName("price_difference_cents") val priceDifference: Currency
)