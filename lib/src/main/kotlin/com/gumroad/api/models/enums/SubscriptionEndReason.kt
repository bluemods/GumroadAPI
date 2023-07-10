package com.gumroad.api.models.enums

enum class SubscriptionEndReason {
    /**
     * Cancelled
     */
    CANCELLED,

    /**
     * Payment failed
     */
    FAILED_PAYMENT,

    /**
     * Subscription ended
     */
    FIXED_SUBSCRIPTION_PERIOD_ENDED
}