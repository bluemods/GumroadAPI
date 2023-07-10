package com.gumroad.api.models

import java.text.DecimalFormat

/**
 * Denotes an amount, in USD cents.
 */
data class Currency(
    /** Amount in USD cents. */
    val usdCents: Long
) {

    /**
     * Format the currency as a displayable string, such as `$1.00`
     */
    fun formatAsUSD(): String = DecimalFormat("$0.00").format(usdCents * 100L)

}