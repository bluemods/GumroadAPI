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
            reader.nextString()
        } else {
            reader.skipValue()
            null
        }
    }

    private fun String?.stripWhitespace() = this?.trim()?.replace(" {2,}".toRegex(), " ") ?: ""
}

internal class CurrencyAdapter : TypeAdapter<Currency>() {
    override fun write(out: JsonWriter, value: Currency?) {
        value?.let { out.value(value.usdCents) } ?: out.nullValue()
    }

    override fun read(reader: JsonReader): Currency? {
        return when(reader.peek()) {
            JsonToken.NUMBER -> Currency(reader.nextLong())
            JsonToken.STRING -> Currency(reader.nextString().toLong())
            else -> {
                reader.skipValue()
                null
            }
        }
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
        return valueOf<SubscriptionUpdateType>(json.asString.uppercase())
    }
}

private inline fun <reified T : Enum<T>> valueOf(type: String): T = java.lang.Enum.valueOf(T::class.java, type)
