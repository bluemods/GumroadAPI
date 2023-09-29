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
     * The numeric ID of the user.
     */
    @SerializedName("id") val numericId: String,

    /**
     * The registered email address of the user.
     */
    @SerializedName("email") val email: String,

    /**
     * The display name of the user.
     */
    @SerializedName("display_name") val displayName: String,

    /**
     * The profile pic of the user, if present.
     */
    @SerializedName("profile_url") val profileUrl: String?,

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
     * The custom CSS for the user, if specified.
     */
    @SerializedName("custom_css") val customCss: String?,

    /**
     * The currency type for the user.
     */
    @SerializedName("currency_type") val currencyType: String,

    /**
     * The Twitter username of the user, if specified.
     */
    @SerializedName("twitter_handle") val twitterHandle: String?
)