package com.gumroad.api.models

import com.google.gson.annotations.SerializedName

/**
 * Describes a variant category of a product.
 */
data class VariantCategory(
    /**
     * The ID of the category.
     */
    @SerializedName("id") val categoryId: String,

    /**
     * The user facing title of the category.
     */
    @SerializedName("title") val title: String
)