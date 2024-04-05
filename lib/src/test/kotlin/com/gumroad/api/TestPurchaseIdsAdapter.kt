package com.gumroad.api

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.gumroad.api.adapters.PurchaseIdsAdapter
import kotlin.test.Test
import kotlin.test.assertTrue

class TestPurchaseIdsAdapter {

    companion object {
        private const val FAKE_PURCHASE_ID = "AAAAAAAAAAAAAAAAAAAA=="
        private const val FAKE_PURCHASE_ID_2 = "AAAAAAAAAAAAAAAAAAAB=="
    }

    @Test
    fun `test well formed purchase ids`() {
        val decoded = attemptDecode(JsonObject().apply {
            add("purchase_ids", JsonArray().apply {
                add(FAKE_PURCHASE_ID)
                add(FAKE_PURCHASE_ID_2)
            })
        })
        assertTrue { FAKE_PURCHASE_ID in decoded.purchaseIds && FAKE_PURCHASE_ID_2 in decoded.purchaseIds }
    }

    @Test
    fun `test malformed purchase ids returned from api`() {
        val decoded = attemptDecode(JsonObject().apply {
            add("purchase_ids", JsonObject().apply {
                addProperty("", FAKE_PURCHASE_ID)
            })
        })
        assertTrue { FAKE_PURCHASE_ID in decoded.purchaseIds }
    }

    @Test
    fun `test malformed purchase ids that might be returned from api`() {
        val decoded = attemptDecode(JsonObject().apply {
            add("purchase_ids", JsonObject().apply {
                addProperty("", FAKE_PURCHASE_ID)

                // This has never been seen in the wild,
                // so for now additional values past the empty string should be ignored
                addProperty("1", FAKE_PURCHASE_ID_2)
            })
        })
        println(decoded)
        assertTrue { FAKE_PURCHASE_ID in decoded.purchaseIds }
        assertTrue { FAKE_PURCHASE_ID_2 !in decoded.purchaseIds }
    }

    private fun attemptDecode(input: JsonElement): PurchaseIdTestHolder {
        return Gson().fromJson(input, PurchaseIdTestHolder::class.java)
    }

    private data class PurchaseIdTestHolder(
        @SerializedName("purchase_ids")
        @JsonAdapter(PurchaseIdsAdapter::class)
        val purchaseIds: List<String>
    )
}