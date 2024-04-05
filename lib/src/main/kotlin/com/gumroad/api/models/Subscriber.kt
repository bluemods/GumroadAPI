package com.gumroad.api.models

import com.google.gson.annotations.JsonAdapter
import com.gumroad.api.models.enums.Recurrence
import com.gumroad.api.models.enums.SubscriberStatus
import com.google.gson.annotations.SerializedName
import com.gumroad.api.adapters.PurchaseIdsAdapter
import java.util.*

/**
 * Describes a subscriber of a product.
 */
data class Subscriber(
    /** The ID of the subscription. */
    @SerializedName("id") val subscriptionId: String,

    /** The ID of the product that is subscribed to. */
    @SerializedName("product_id") val productId: String,

    /** The name of the product that is subscribed to. */
    @SerializedName("product_name") val productName: String,

    /** The user ID of the subscriber. */
    @SerializedName("user_id") val userId: String,

    /** The email of the subscriber. */
    @SerializedName("user_email") val userEmail: String,

    /** The list of purchase IDs from the subscriber. */
    @SerializedName("purchase_ids") @JsonAdapter(PurchaseIdsAdapter::class) val purchaseIds: List<String>,

    /** The creation time of the subscription. */
    @SerializedName("created_at") val createdAt: Date,

    /** If present, the time that the user requested cancellation of the subscription. */
    @SerializedName("user_requested_cancellation_at") val userRequestedCancellationAt: Date?,

    /** The number of charges made for this subscription. */
    @SerializedName("charge_occurrence_count") val chargeOccurrenceCount: Int?,

    /** The recurrence of the subscription, chosen by the customer */
    @SerializedName("recurrence") val recurrence: Recurrence,

    /** If present, the time of cancellation of the subscription. */
    @SerializedName("cancelled_at") val cancelledAt: Date?,

    /** If present, the end time of the subscription. */
    @SerializedName("ended_at") val endedAt: Date?,

    /** If present, the failed payment time of the subscription. */
    @SerializedName("failed_at") val failedAt: Date?,

    /** If present, the free trial end time of the subscription. */
    @SerializedName("free_trial_ends_at") val freeTrialEndsAt: Date?,

    /** The license key of the subscription. */
    @SerializedName("license_key") val licenseKey: String,

    /** The current status of the subscription. */
    @SerializedName("status") val status: SubscriberStatus
)