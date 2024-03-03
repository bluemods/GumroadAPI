package com.gumroad.api.exceptions

import java.io.IOException

class GumroadApiException(val error: GumroadApiError) : IOException() {
    constructor(httpStatus: Int, errorMessage: String) : this(GumroadApiError(httpStatus, errorMessage))

    override val message: String
        get() = "${error.status}: ${error.error}"
}