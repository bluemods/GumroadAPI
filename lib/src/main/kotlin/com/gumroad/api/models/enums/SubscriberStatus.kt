package com.gumroad.api.models.enums

enum class SubscriberStatus {
    /**
     * Subscription is ongoing
     */
    ALIVE,

    /**
     * Subscription is ongoing, pending cancellation
     */
    PENDING_CANCELLATION,

    /**
     * Subscription is ongoing, pending failure
     */
    PENDING_FAILURE,

    /**
     * Payment has failed for this subscription
     */
    FAILED_PAYMENT,

    /**
     * Subscription has ended.
     */
    FIXED_SUBSCRIPTION_PERIOD_ENDED,

    /**
     * Subscription is cancelled.
     */
    CANCELLED
}