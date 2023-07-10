package com.gumroad.api.models.enums

enum class Recurrence(val months: Int) {
    MONTHLY(1),
    QUARTERLY(3),
    BIANNUALLY(6),
    YEARLY(12)
}