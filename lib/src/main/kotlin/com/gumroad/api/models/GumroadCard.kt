package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

/**
 * Describes a card used for a purchase.
 */
data class GumroadCard(
    /**
     * The card number.
     *
     * Example: `**** **** **** 4242`
     */
    @SerializedName("visual") val visual: String?,

    /**
     * The type of card used.
     *
     * Example: `Visa`
     */
    @SerializedName("type") val type: String?
)