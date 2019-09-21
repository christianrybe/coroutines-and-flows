package com.codete.coroutine

import kotlinx.coroutines.delay

class SuspendingShip {

    suspend fun startEngines(): Int {
        println("Starting engines")
        delay(700)
        return 190
    }

    suspend fun autoClean(): Boolean {
        println("Starting cleaning")
        delay(500)
        throw RuntimeException()
    }
}