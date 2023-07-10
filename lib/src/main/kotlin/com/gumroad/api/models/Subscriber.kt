package com.gumroad.api.models

import com.gumroad.api.models.enums.Recurrence
import com.gumroad.api.models.enums.SubscriberStatus
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Describes a subscriber of a product.
 */
data class Subscriber(
    @SerializedName("id") val subscriptionId: String,
    @SerializedName("product_id") val productId: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("user_email") val userEmail: String,
    @SerializedName("purchase_ids") val purchaseIds: List<String>,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("user_requested_cancellation_at") val userRequestedCancellationAt: Date,
    @SerializedName("charge_occurrence_count") val chargeOccurrencesCount: String,
    @SerializedName("recurrence") val recurrence: Recurrence,
    @SerializedName("cancelled_at") val cancelledAt: Date,
    @SerializedName("ended_at") val endedAt: Date,
    @SerializedName("failed_at") val failedAt: Date,
    @SerializedName("free_trial_ends_at") val freeTrialEndsAt: Date,
    @SerializedName("license_key") val licenseKey: String,
    @SerializedName("status") val status: SubscriberStatus,
)