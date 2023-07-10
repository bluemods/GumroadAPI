package com.gumroad.api.models.enums

enum class OfferType {
    CENTS,
    PERCENT;

    /**
     * Must be lowercase for the API.
     */
    override fun toString(): String {
        return name.lowercase()
    }
}