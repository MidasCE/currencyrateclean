package com.example.currencychangeapp.data.serializer

import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateSerializerTest {

    private lateinit var dateDeserializer: DateDeserializer

    @Before
    fun setUp() {
        dateDeserializer = DateDeserializer()
    }

    @Test
    fun `Test deserialize date`() {
        val date = dateDeserializer.deserialize(JsonPrimitive("2019-01-20 10:37"), null, null)
        date.`should equal`(SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2019-01-20 10:37"))
    }

    @Test
    fun `Test deserialize date without time`() {
        val date = dateDeserializer.deserialize(JsonPrimitive("2019-01-20"), null, null)
        date.`should equal`(SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2019-01-20 10:37"))
    }

    @Test(expected = JsonParseException::class)
    fun `Test deserialize invalid date`() {
        dateDeserializer.deserialize(JsonPrimitive("invalid"), null, null)
    }

}
