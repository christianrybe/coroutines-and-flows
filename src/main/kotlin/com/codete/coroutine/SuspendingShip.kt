package com.codete.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
class SuspendingShip {

    suspend fun startEngines(): Int =
        withContext(Dispatchers.IO) {
            println("Starting engines")
            delay(700)
            190
        }

    suspend fun autoClean(): Boolean =
        withContext(Dispatchers.IO) {
            println("Starting cleaning")
            delay(500)
            throw RuntimeException()
        }
}