package com.gumroad.api

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.gumroad.api.adapters.SafeDateAdapter
import java.time.Instant
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TestSafeDateAdapter {

    @Test
    fun `test well formed`() {
        val testDateString = "2021-01-05T19:38:56Z"

        val decoded = attemptDecode(JsonObject().apply {
            addProperty("foo", testDateString)
        })
        assertEquals(Date.from(Instant.parse(testDateString)), decoded.testDate)
    }

    @Test
    fun `test malformed`() {
        val decoded = attemptDecode(JsonObject().apply {
            addProperty("foo", "")
        })
        assertNull(decoded.testDate)
    }

    @Test
    fun `test unix time`() {
        // Expected to fail (change if supported later)
        val decoded = attemptDecode(JsonObject().apply {
            addProperty("foo", (System.currentTimeMillis()/1000).toString())
        })
        assertNull(decoded.testDate)
    }

    @Test
    fun `test unix millis`() {
        // Expected to fail (change if supported later)
        val decoded = attemptDecode(JsonObject().apply {
            addProperty("foo", System.currentTimeMillis().toString())
        })
        assertNull(decoded.testDate)
    }

    private fun attemptDecode(input: JsonElement): DateTestHolder {
        return Gson().fromJson(input, DateTestHolder::class.java)
    }

    private data class DateTestHolder(
        @SerializedName("foo")
        @JsonAdapter(SafeDateAdapter::class)
        val testDate: Date?
    )
}