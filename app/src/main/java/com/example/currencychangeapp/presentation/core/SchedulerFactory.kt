package com.example.currencychangeapp.presentation.core

import io.reactivex.Scheduler

interface SchedulerFactory {
    fun io() : Scheduler

    fun main() : Scheduler
}
