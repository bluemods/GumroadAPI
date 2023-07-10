package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

/**
 * Describes the currently authenticated user.
 */
data class User(
    /**
     * The ID of the user.
     */
    @SerializedName("user_id") val userId: String,

    /**
     * The registered email address of the user.
     */
    @SerializedName("email") val email: String,

    /**
     * The URL to the user's Gumroad account.
     */
    @SerializedName("url") val url: String,

    /**
     * The name of the user.
     */
    @SerializedName("name") val name: String,

    /**
     * The bio of the user, if specified.
     */
    @SerializedName("bio") val bio: String?,

    /**
     * The Twitter username of the user, if specified.
     */
    @SerializedName("twitter_handle") val twitterHandle: String?
)