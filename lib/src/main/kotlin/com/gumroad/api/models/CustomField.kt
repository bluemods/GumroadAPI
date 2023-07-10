package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

data class CustomField(
    /**
     * The name of the custom field.
     */
    @SerializedName("name") val name: String,

    /**
     * Whether it is required for the customer to input upon checkout.
     */
    @SerializedName("required") val required: Boolean,
)