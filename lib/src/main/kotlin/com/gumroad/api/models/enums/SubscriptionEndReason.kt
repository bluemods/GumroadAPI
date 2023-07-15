package com.gumroad.api.models.enums

enum class SubscriptionEndReason {
    /**
     * Subscription ended due to cancellation
     */
    CANCELLED,

    /**
     * Subscription ended due to payment failure
     */
    FAILED_PAYMENT,

    /**
     * Subscription period ended
     */
    FIXED_SUBSCRIPTION_PERIOD_ENDED
}