package com.gumroad.api

import com.gumroad.api.models.Currency
import kotlin.test.*

class TestCurrency {

    @Test
    fun testCurrencyFormatting() {
        val oneDollar = Currency(100)
        assertEquals(oneDollar.formatAsUSD(), "$1.00")

        val oneCent = Currency(1)
        assertEquals(oneCent.formatAsUSD(), "$0.01")

        val oneDollarAndOneCent = Currency(101)
        assertEquals(oneDollarAndOneCent.formatAsUSD(), "$1.01")
    }
}