package com.example.currencychangeapp.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesProviderImplTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var mockSharedPreferences: SharedPreferences

    private lateinit var sharedPreferencesProviderImpl : SharedPreferencesProviderImpl

    @Before
    fun setUp() {
        whenever(context.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences)

        sharedPreferencesProviderImpl = SharedPreferencesProviderImpl(context)
    }

    @Test
    fun `Test get pref`() {
        val result = sharedPreferencesProviderImpl.provide("name")

        verify(context).getSharedPreferences("name", Context.MODE_PRIVATE)
        result.`should equal`(mockSharedPreferences)
    }
}
