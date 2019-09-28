package com.example.currencychangeapp.presentation.core

import io.reactivex.Scheduler
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SchedulerFactoryImplTest {

    @Mock
    lateinit var mockIoScheduler: Scheduler

    @Mock
    lateinit var mockMainScheduler: Scheduler

    lateinit var schedulerFactoryImpl: SchedulerFactoryImpl

    @Before
    fun setUp() {
        schedulerFactoryImpl = SchedulerFactoryImpl(mockIoScheduler, mockMainScheduler)
    }

    @Test
    fun `Test get io scheduler`() {
        schedulerFactoryImpl.io().`should equal`(mockIoScheduler)
    }

    @Test
    fun `Test get main scheduler`() {
        schedulerFactoryImpl.main().`should equal`(mockMainScheduler)
    }

}
