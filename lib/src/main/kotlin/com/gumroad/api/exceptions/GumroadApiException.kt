package com.gumroad.api.exceptions

import com.gumroad.api.results.GumroadResult
import java.io.IOException

class GumroadApiException(val error: GumroadResult) : IOException(error.message)