package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

/**
 * Describes an offer code for a product.
 */
data class OfferCode(
    /**
     * The ID of the offer code.
     */
    @SerializedName("id") val id: String,

    /**
     * The name of the offer code.
     */
    @SerializedName("name") val name: String,

    /**
     * The discount the offer code provides.
     */
    @SerializedName("amount_cents") val amount: Currency?,

    /**
     * The percent off the offer code provides.
     */
    @SerializedName("percent_off") val percentOff: Int?,

    /**
     * The max amount of times the offer code may be used.
     */
    @SerializedName("max_purchase_count") val maxPurchaseCount: Int,

    /**
     * If true, this offer code applies to all products
     * owned by the authenticated user.
     */
    @SerializedName("universal") val universal: Boolean,

    /**
     * How many times this offer code has been used.
     */
    @SerializedName("times_used") val timesUsed: Int,
)