package com.example.currencychangeapp.presentation.main.mapper

import android.content.Context
import android.content.res.Resources
import com.example.currencychangeapp.presentation.shared.mapper.CurrencyFlagResourceMapperImpl
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyFlagResourceMapperImplTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var resources: Resources

    lateinit var currencyFlagResourceMapperImpl: CurrencyFlagResourceMapperImpl

    private val expectedId = 1

    @Before
    fun setUp() {
        currencyFlagResourceMapperImpl =
            CurrencyFlagResourceMapperImpl(context)

        whenever(context.resources).thenReturn(resources)
        whenever(context.packageName).thenReturn("packageName")
        whenever(resources.getIdentifier(anyString(), anyString(), anyString())).thenReturn(
            expectedId
        )
    }

    @Test
    fun `Test get id resource`() {
        currencyFlagResourceMapperImpl.getCurrencyFlagResId("currencyCode")
            .`should equal`(expectedId)
    }
}
