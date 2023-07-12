package com.gumroad.api.utils

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.net.URI
import java.util.regex.Pattern

internal object FormToJsonConverter {

    @Suppress("RegExpRedundantEscape")
    private val pattern: Pattern = Pattern.compile("\\[([^\\]]*)\\]")

    /**
     * Converts an application/x-www-form-urlencoded string to a [JsonObject]
     *
     * @param uri the URI to parse.
     * @return a [JsonObject]
     */
    fun convert(uri: URI): JsonObject {
        return uri.toHttpUrlOrNull()?.let { convert(it) } ?: JsonObject()
    }

    /**
     * Converts an application/x-www-form-urlencoded string to a [JsonObject]
     *
     * @param formData the form data.
     * @return a [JsonObject]
     */
    fun convert(formData: String): JsonObject {
        return convert(formData.asHttpUrl())
    }

    /**
     * Converts a [HttpUrl] to a [JsonObject] using its query parameters.
     *
     * @param url the URL to parse.
     * @return a [JsonObject]
     */
    fun convert(url: HttpUrl): JsonObject {
        val ret = JsonObject()
        for (name in url.queryParameterNames) {
            for (value in url.queryParameterValues(name)) {
                val startIndex = name.indexOf('[')
                if (startIndex < 0) {
                    ret.addProperty(name, value)
                    continue
                }

                val key = name.substring(0, startIndex)
                val matcher = pattern.matcher(name)

                var nestedKey: String? = null
                var parent: JsonObject = ret
                var child: JsonObject = findOrPut(parent, key)

                while (matcher.find()) {
                    nestedKey = matcher.group(1)
                    parent = child
                    child = findOrPut(parent, nestedKey)
                }
                if (nestedKey != null) {
                    parent.addProperty(nestedKey, value)
                }
            }
        }
        return ret
    }

    private fun findOrPut(parent: JsonObject, key: String): JsonObject {
        var ret: JsonElement? = parent.get(key)
        if (ret !is JsonObject) {
            ret = JsonObject()
            parent.add(key, ret)
        }
        return ret
    }

    /** https://github.com/square/okhttp/issues/3223 */
    private fun String.asHttpUrl() : HttpUrl {
        return HttpUrl.Builder().scheme("https").host("foo").encodedQuery(this).build()
    }
}