package com.example.currencychangeapp.presentation.main.mapper

import android.content.Context
import android.content.res.Resources
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyStringMapperImpl
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyStringMapperImplTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var resources: Resources

    lateinit var currencyStringMapperImpl: CurrencyStringMapperImpl

    private val expectedId = 1
    private val expectedString = "string"

    @Before
    fun setUp() {
        currencyStringMapperImpl = CurrencyStringMapperImpl(context)

        whenever(context.resources).thenReturn(resources)
        whenever(context.packageName).thenReturn("packageName")
        whenever(resources.getIdentifier(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(
            expectedId
        )
        whenever(context.getString(expectedId)).thenReturn(expectedString)
    }

    @Test
    fun `Test get id resource`() {
        currencyStringMapperImpl.getCurrencyName("currencyCode").`should equal`(expectedString)
    }
}
