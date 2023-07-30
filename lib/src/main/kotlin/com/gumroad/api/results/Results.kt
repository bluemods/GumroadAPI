package com.gumroad.api.results

import com.gumroad.api.models.*
import com.google.gson.annotations.SerializedName

open class GumroadResult {
    @SerializedName("success") var success: Boolean = false
    @SerializedName("message") var message: String? = null

    override fun toString(): String {
        return "GumroadResult(success=$success, message=$message)"
    }
}

data class ProductList(@SerializedName("products") val products: List<Product>) : GumroadResult()
data class ProductResult(@SerializedName("product") val product: Product) : GumroadResult()

data class VariantCategoryList(@SerializedName("variant_categories") val categories: List<VariantCategory>) : GumroadResult()
data class VariantCategoryResult(@SerializedName("variant_category") val category: VariantCategory) : GumroadResult()

data class VariantList(@SerializedName("variants") val variants: List<Variant>) : GumroadResult()
data class VariantResult(@SerializedName("variant") val variant: Variant) : GumroadResult()

data class OfferCodeList(@SerializedName("offer_codes") val offerCodes: List<OfferCode>) : GumroadResult()
data class OfferCodeResult(@SerializedName("offer_code") val offerCode: OfferCode) : GumroadResult()

data class CustomFieldList(@SerializedName("custom_fields") val customFields: List<CustomField>) : GumroadResult()
data class CustomFieldResult(@SerializedName("custom_field") val customField: CustomField) : GumroadResult()

data class ResourceSubscriptionList(@SerializedName("resource_subscriptions") val subscriptions: List<ResourceSubscription>) : GumroadResult()
data class ResourceSubscriptionResult(@SerializedName("resource_subscription") val subscription: ResourceSubscription) : GumroadResult()

data class SaleList(
    @SerializedName("next_page_url") val nextPageUrl: String?,
    @SerializedName("next_page_key") val nextPageKey: String?,
    @SerializedName("sales") val sales: List<Sale>
) : GumroadResult()

data class SaleResult(@SerializedName("sale") val sale: Sale)

data class SubscriberList(@SerializedName("subscribers") val subscribers: List<Subscriber>) : GumroadResult()
data class SubscriberResult(@SerializedName("subscribers") val subscriber: Subscriber) : GumroadResult()

data class UserResult(@SerializedName("user") val user: User) : GumroadResult()

data class LicenseResult(@SerializedName("success") val success: Boolean, @SerializedName("uses") val uses: Int, @SerializedName("purchase") val purchase: Purchase?)