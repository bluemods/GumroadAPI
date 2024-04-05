package com.gumroad.api.adapters

import com.gumroad.api.models.Currency
import com.gumroad.api.models.enums.*
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

internal class DayStampAdapter : TypeAdapter<String>() {
    override fun write(out: JsonWriter, value: String?) {
        out.value(value.stripWhitespace())
    }

    override fun read(reader: JsonReader): String? {
        return if (reader.peek() == JsonToken.STRING) {
            reader.nextString().stripWhitespace()
        } else {
            reader.skipValue()
            null
        }
    }

    private fun String?.stripWhitespace(): String = this?.trim()?.replace(" {2,}".toRegex(), " ") ?: ""
}

internal class CurrencyAdapter : TypeAdapter<Currency>() {
    override fun write(out: JsonWriter, value: Currency?) {
        value?.let { out.value(value.usdCents) } ?: out.nullValue()
    }

    override fun read(reader: JsonReader): Currency? {
        return when(reader.peek()) {
            JsonToken.NUMBER -> Currency(reader.nextLong())
            JsonToken.STRING -> reader.nextString().toLongOrNull()?.let { Currency(it) }
            else -> {
                reader.skipValue()
                null
            }
        }
    }
}

internal class PurchaseCustomFieldAdapter : TypeAdapter<Map<String, String>>() {
    override fun write(out: JsonWriter, value: Map<String, String>?) {
        out.beginArray()
        value?.forEach { (k, v) -> out.value("$k: $v") }
        out.endArray()
    }

    override fun read(reader: JsonReader): Map<String, String> {
        val map = linkedMapOf<String, String>()

        when(reader.peek()) {
            JsonToken.BEGIN_ARRAY -> {
                reader.beginArray()

                while (reader.hasNext()) {
                    when (reader.peek()) {
                        JsonToken.STRING -> {
                            val field = reader.nextString()
                            val fieldName = field.substringBefore(": ").trim()
                            val fieldValue = field.substringAfter(": ").trim()

                            map[fieldName] = fieldValue
                        }
                        else -> {
                            reader.skipValue()
                        }
                    }
                }
                reader.endArray()
            }
            else -> {
                reader.skipValue()
            }
        }
        return map
    }
}

// The API currently has a bug where in some cases it can return IDs as a JSON object
// Example: `"purchase_ids":{"":"AAAAAAAAAAAAAAAAAAAAAA=="}`
// This adapter detects this and extracts a list of IDs from it.
internal class PurchaseIdsAdapter : TypeAdapter<List<String>>() {
    override fun write(out: JsonWriter, value: List<String>?) {
        out.beginArray()
        value?.forEach(out::value)
        out.endArray()
    }

    override fun read(reader: JsonReader): List<String> {
        val purchaseIds = arrayListOf<String>()

        when(reader.peek()) {
            JsonToken.BEGIN_ARRAY -> {
                // Expected path
                reader.beginArray()
                while (reader.hasNext()) {
                    when (reader.peek()) {
                        JsonToken.STRING -> {
                            purchaseIds.add(reader.nextString())
                        }
                        else -> {
                            reader.skipValue()
                        }
                    }
                }
                reader.endArray()
            }
            JsonToken.BEGIN_OBJECT -> {
                // Malformed edge case path
                reader.beginObject()
                while (reader.hasNext()) {
                    val token = reader.peek()
                    if (token == JsonToken.NAME && reader.nextName() == "") {
                        val value = reader.nextString()
                        purchaseIds.add(value)
                    } else {
                        reader.skipValue()
                    }
                }
                reader.endObject()
            }
            else -> {
                reader.skipValue()
            }
        }
        return purchaseIds
    }
}

internal class RecurrenceAdapter : JsonDeserializer<Recurrence>, JsonSerializer<Recurrence> {
    override fun serialize(src: Recurrence, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.name.lowercase())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Recurrence {
        return valueOf(json.asString.uppercase())
    }
}

internal class ResourceSubscriptionTypeAdapter : JsonDeserializer<ResourceSubscriptionType>, JsonSerializer<ResourceSubscriptionType> {
    override fun serialize(src: ResourceSubscriptionType, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.name.lowercase())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ResourceSubscriptionType {
        return valueOf(json.asString.uppercase())
    }
}

internal class SubscriberStatusAdapter : JsonDeserializer<SubscriberStatus>, JsonSerializer<SubscriberStatus> {
    override fun serialize(src: SubscriberStatus, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.name.lowercase())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): SubscriberStatus {
        return valueOf(json.asString.uppercase())
    }
}

internal class SubscriptionEndReasonAdapter : JsonDeserializer<SubscriptionEndReason>, JsonSerializer<SubscriptionEndReason> {
    override fun serialize(src: SubscriptionEndReason, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.name.lowercase())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): SubscriptionEndReason {
        return valueOf(json.asString.uppercase())
    }
}

internal class SubscriptionUpdateTypeAdapter : JsonDeserializer<SubscriptionUpdateType>, JsonSerializer<SubscriptionUpdateType> {
    override fun serialize(src: SubscriptionUpdateType, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src.name.lowercase())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): SubscriptionUpdateType {
        return valueOf(json.asString.uppercase())
    }
}

private inline fun <reified T : Enum<T>> valueOf(type: String): T = java.lang.Enum.valueOf(T::class.java, type)
