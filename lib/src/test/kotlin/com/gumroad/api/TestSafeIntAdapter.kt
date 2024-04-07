package com.gumroad.api

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.gumroad.api.adapters.SafeIntAdapter
import kotlin.test.Test
import kotlin.test.assertTrue

class TestSafeIntAdapter {

    @Test
    fun `test well formed`() {
        val decoded = attemptDecode(JsonObject().apply {
            addProperty("charge_occurrence_count", 420)
        })
        assertTrue { decoded.chargeOccurrenceCount == 420 }
    }

    @Test
    fun `test malformed`() {
        val decoded = attemptDecode(JsonObject().apply {
            addProperty("charge_occurrence_count", "")
        })
        assertTrue { decoded.chargeOccurrenceCount == 0 }
    }

    @Test
    fun `test object`() {
        val decoded = attemptDecode(JsonObject().apply {
            add("charge_occurrence_count", JsonObject().apply {
                addProperty("charge_occurrence_count", "0")
                addProperty("foo", "bar")
            })
        })
        assertTrue { decoded.chargeOccurrenceCount == 0 }
    }

    @Test
    fun `test array`() {
        val decoded = attemptDecode(JsonObject().apply {
            add("charge_occurrence_count", JsonArray().apply {
                add("foo")
            })
        })
        assertTrue { decoded.chargeOccurrenceCount == 0 }
    }

    private fun attemptDecode(input: JsonElement): IntTestHolder {
        return Gson().fromJson(input, IntTestHolder::class.java)
    }

    private data class IntTestHolder(
        @SerializedName("charge_occurrence_count")
        @JsonAdapter(SafeIntAdapter::class)
        val chargeOccurrenceCount: Int
    )
}