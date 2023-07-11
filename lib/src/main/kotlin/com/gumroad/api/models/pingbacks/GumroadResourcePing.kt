package com.gumroad.api.models.pingbacks

import com.gumroad.api.models.enums.Recurrence
import com.gumroad.api.models.enums.SubscriptionEndReason
import com.gumroad.api.models.enums.SubscriptionUpdateType
import com.google.gson.annotations.SerializedName
import java.util.*

import com.gumroad.api.models.enums.ResourceSubscriptionType

/**
 * Used for the [Resource subscription pingback API](https://app.gumroad.com/api#resource-subscriptions)
 */
data class GumroadResourcePing(
    /** The ID of the subscription, if the purchase is part of a subscription */
    @SerializedName("subscription_id") val subscriptionId: String?,

    /** The ID of the product */
    @SerializedName("product_id") val productId: String,

    /** The name of the product */
    @SerializedName("product_name") val productName: String,

    /** The user ID of the subscriber */
    @SerializedName("user_id") val userId: String,

    /** The email address of the subscriber */
    @SerializedName("user_email") val userEmail: String,

    /** A list of charge IDs belonging to this subscription */
    @SerializedName("purchase_ids") val purchaseIds: List<String>,

    /** A timestamp when subscription was created */
    @SerializedName("created_at") val createdAt: Date,

    /** The number of charges made for this subscription */
    @SerializedName("charge_occurrence_count") val chargeOccurrenceCount: Int,

    /** If present, the recurrence of the payment option chosen by the buyer */
    @SerializedName("recurrence") val recurrence: Recurrence?,

    /** The number of charges made for this subscription */
    @SerializedName("free_trial_ends_at") val freeTrialEndsAt: Date?,

    /** If present, a dictionary */
    @SerializedName("custom_fields") val customFields: Map<String, String>?,

    /** License key from the original purchase if present */
    @SerializedName("license_key") val licenseKey: String?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * true if subscription has been cancelled, otherwise false */
    @SerializedName("cancelled") val cancelled: Boolean,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * timestamp at which subscription will be cancelled
     */
    @SerializedName("cancelled_at") val cancelledAt: Date,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * true if subscription has been cancelled by admin
     */
    @SerializedName("cancelled_by_admin") val cancelledByAdmin: Boolean,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * true if subscription has been cancelled by buyer, otherwise not present
     */
    @SerializedName("cancelled_by_buyer") val cancelledByBuyer: Boolean,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * true if subscription has been cancelled by seller, otherwise not present
     */
    @SerializedName("cancelled_by_seller") val cancelledBySeller: Boolean,

    /**
     * **Only meaningful for [ResourceSubscriptionType.CANCELLATION] pingbacks.**
     *
     * true if subscription has been cancelled automatically because of payment failure, otherwise not present
     */
    @SerializedName("cancelled_due_to_payment_failures") val cancelledDueToPaymentFailure: Boolean,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_UPDATED] pingbacks.**
     *
     * Upgrade or downgrade
     */
    @SerializedName("type") val subscriptionUpdateType: SubscriptionUpdateType?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_UPDATED] pingbacks.**
     *
     * Timestamp at which the change went or will go into effect
     */
    @SerializedName("effective_as_of") val subscriptionUpdateTime: Date?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_UPDATED] pingbacks.**
     *
     * Tier, subscription duration, price, and quantity of the subscription before the change
     */
    @SerializedName("old_plan") val oldSubscriptionPlan: SubscriptionPlan?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_UPDATED] pingbacks.**
     *
     * Tier, subscription duration, price, and quantity of the subscription after the change
     */
    @SerializedName("new_plan") val newSubscriptionPlan: SubscriptionPlan?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_ENDED] pingbacks.**
     *
     * Timestamp at which the subscription ended
     */
    @SerializedName("ended_at") val subscriptionEndedAt: Date?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_ENDED] pingbacks.**
     *
     * The reason for the subscription ending
     */
    @SerializedName("ended_reason") val subscriptionEndReason: SubscriptionEndReason?,

    /**
     * **Only meaningful for [ResourceSubscriptionType.SUBSCRIPTION_RESTARTED] pingbacks.**
     *
     * Timestamp at which the subscription was restarted
     */
    @SerializedName("restarted_at") val subscriptionRestartedAt: Date?
) {
    data class SubscriptionPlan(
        /** The chosen tier of the plan by the customer */
        @SerializedName("tier") val tier: Tier,

        /** The chosen recurrence of the plan by the customer */
        @SerializedName("recurrence") val recurrence: Recurrence,

        /** The price of the tier */
        @SerializedName("price") val price: Currency,

        /** How many subscriptions were purchased */
        @SerializedName("quantity") val quantity: Int
    ) {
        data class Tier(
            /** The ID of the tier. */
            @SerializedName("id") val id: String,

            /** The name of the tier */
            @SerializedName("name") val name: String
        )
    }
}