package com.codete.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class SuspendingShip {

    suspend fun startEngines(): Int = coroutineScope {
        println("Starting engines")
        delay(700)
        190
    }

    suspend fun autoClean(): Boolean = coroutineScope {
        println("Starting cleaning")
        delay(500)
        throw RuntimeException()
    }
}