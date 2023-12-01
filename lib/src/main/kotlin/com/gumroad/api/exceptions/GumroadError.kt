package com.gumroad.api.exceptions

import com.google.gson.annotations.SerializedName

/**
 * Describes an error returned from the API when something went wrong.
 */
data class GumroadError(
    /**
     * The HTTP status code.
     */
    @SerializedName("status") val status: Int,
    /**
     * The description of what triggered the error.
     */
    @SerializedName("error") val error: String,
)
