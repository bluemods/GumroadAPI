package com.gumroad.api.exceptions

import java.io.IOException

class GumroadApiException(val error: GumroadError) : IOException() {
    constructor(errorMessage: String) : this(GumroadError(-1, errorMessage))

    override val message: String
        get() = "${error.status}: ${error.error}"
}