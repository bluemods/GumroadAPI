package com.gumroad.api.models

import com.gumroad.api.models.enums.ResourceSubscriptionType
import com.google.gson.annotations.SerializedName

/**
 * Describes an ongoing resource subscription.
 */
data class ResourceSubscription(
    /**
     * The subscription ID.
     */
    @SerializedName("id") val id: String,

    /**
     * The type of the subscription.
     */
    @SerializedName("resource_name") val type: ResourceSubscriptionType,

    /**
     * The URL that Gumroad will deliver events of the [type] to via a POST request.
     */
    @SerializedName("post_url") val postUrl: String,
)