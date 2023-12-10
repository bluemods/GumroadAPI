package com.gumroad.api.exceptions

import java.io.IOException

class GumroadApiException(val error: GumroadError) : IOException() {
    constructor(httpStatus: Int, errorMessage: String) : this(GumroadError(httpStatus, errorMessage))

    override val message: String
        get() = "${error.status}: ${error.error}"
}