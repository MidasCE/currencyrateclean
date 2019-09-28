package com.example.currencychangeapp.presentation.core

import io.reactivex.Scheduler

class SchedulerFactoryImpl(
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : SchedulerFactory {
    override fun io(): Scheduler = ioScheduler

    override fun main(): Scheduler = mainScheduler
}
