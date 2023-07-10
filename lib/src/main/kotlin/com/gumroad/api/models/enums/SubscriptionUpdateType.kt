package com.gumroad.api.models.enums

enum class SubscriptionUpdateType {
    /**
     * The subscription was updated.
     */
    UPGRADE,

    /**
     * The subscription was downgraded.
     */
    DOWNGRADE
}