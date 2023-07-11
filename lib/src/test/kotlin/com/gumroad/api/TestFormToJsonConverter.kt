package com.gumroad.api

import com.google.gson.JsonObject
import com.gumroad.api.models.Currency
import com.gumroad.api.models.GumroadCard
import com.gumroad.api.models.enums.Recurrence
import com.gumroad.api.utils.FormToJsonConverter
import kotlin.test.*

class TestFormToJsonConverter {

    @Test
    fun testSimpleFormData() {
        val testFormData = "firstParam=1&secondParam=2"
        val json = FormToJsonConverter.convert(testFormData)

        assert(json["firstParam"].isJsonPrimitive)
        assert(json["secondParam"].isJsonPrimitive)

        assertEquals(json["firstParam"].asString, "1")
        assertEquals(json["secondParam"].asString, "2")
    }

    @Test
    fun testComplexFormData() {
        val testFormData = "nested[first]=1&nested[second]=2"
        val json = FormToJsonConverter.convert(testFormData)

        assert(json["nested"] is JsonObject)

        val nested = json["nested"] as JsonObject

        assert(nested.size() == 2)
        assert(nested["first"].isJsonPrimitive)
        assert(nested["second"].isJsonPrimitive)

        assert(nested["first"].asString == "1")
        assert(nested["second"].asString == "2")
    }

    @Test
    fun testIncomingGumroadPing() {
        val testFormData = "seller_id=asdfasdfasdfasdf%3D%3D" +
                "&product_id=asdfasdfasdfasdf%3D%3D" +
                "&product_name=Pencil" +
                "&permalink=PencilLink" +
                "&product_permalink=https%3A%2F%2Fpencil.gumroad.com%2Fl%2FSahilsPencil" +
                "&short_product_id=pncil" +
                "&email=test%40gumroad.com" +
                "&price=3000" +
                "&gumroad_fee=400" +
                "&currency=usd" +
                "&quantity=1" +
                "&discover_fee_charged=false" +
                "&can_contact=true" +
                "&referrer=https%3A%2F%2Fgumroad.com%2Fpencil%2F" +
                "&card%5Bvisual%5D=%2A%2A%2A%2A%20%2A%2A%2A%2A%20%2A%2A%2A%2A%201234" +
                "&card%5Btype%5D=visa" +
                "&card%5Bbin%5D=" +
                "&card%5Bexpiry_month%5D=" +
                "&card%5Bexpiry_year%5D=" +
                "&order_number=123456" +
                "&sale_id=asdfasdfasdfasdf%3D%3D" +
                "&sale_timestamp=2023-01-01T15%3A50%3A55Z" +
                "&full_name=John%20Doe" +
                "&license_key=FFFFFFFF-FFFFFFFF-FFFFFFFF-FFFFFFFF" +
                "&ip_country=North Korea" +
                "&is_gift_receiver_purchase=false" +
                "&recurrence=monthly" +
                "&refunded=false" +
                "&disputed=false" +
                "&dispute_won=false" +
                "&test=true"

        val ping = Gumroad.parsePingback(testFormData)

        assertEquals(ping.saleId, "asdfasdfasdfasdf==")
        assertEquals(ping.saleTimestamp.time, 1672588255000L)
        assertEquals(ping.orderNumber, 123456)
        assertEquals(ping.sellerId, "asdfasdfasdfasdf==")
        assertEquals(ping.productId, "asdfasdfasdfasdf==")
        assertEquals(ping.productPermalink, "https://pencil.gumroad.com/l/SahilsPencil")
        assertEquals(ping.shortProductId, "pncil")
        assertEquals(ping.shortProductId, "pncil")
        assertEquals(ping.productName, "Pencil")
        assertEquals(ping.email, "test@gumroad.com")
        assert(ping.urlParams.isNullOrEmpty())
        assertEquals(ping.fullName, "John Doe")
        assertNull(ping.purchaserId)
        assertNull(ping.subscriptionId)
        assertEquals(ping.ipCountryCode, "North Korea")
        assertEquals(ping.price, Currency(3000L))
        assertEquals(ping.recurrence, Recurrence.MONTHLY)
        assert(ping.urlParams.isNullOrEmpty())
        assertNull(ping.offerCode)
        assert(ping.test)
        assert(ping.urlParams.isNullOrEmpty())
        assert(ping.urlParams.isNullOrEmpty())
        assertFalse(ping.isRecurringCharge)
        assertFalse(ping.isPreorderAuthorization)
        assertEquals(ping.licenseKey, "FFFFFFFF-FFFFFFFF-FFFFFFFF-FFFFFFFF")
        assertEquals(ping.quantity, 1)
        assertNull(ping.shippingRateCents)
        assertNull(ping.affiliate)
        assertNull(ping.affiliateCreditAmount)
        assertFalse(ping.isGiftReceiverPurchase)
        assertNull(ping.gifterEmail)
        assertNull(ping.giftPrice)
        assertFalse(ping.refunded)
        assertFalse(ping.discoverFeeCharged)
        assertTrue(ping.canContact)
        assertEquals(ping.referrer, "https://gumroad.com/pencil/")
        assertEquals(ping.gumroadFee, Currency(400))
        assertEquals(ping.card, GumroadCard("**** **** **** 1234", "visa"))
    }
}