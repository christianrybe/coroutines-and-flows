package com.codete

import com.codete.Spaceship.Companion.reportCleaning
import com.codete.coroutine.SuspendingShip
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

val spaceship = Spaceship()
val suspendingShip = SuspendingShip()

suspend fun main() {
    try {
        println("==========FIRST ATTEMPT=========")
        initiateStart1()
        Thread.sleep(700)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        println("==========SECOND ATTEMPT=========")
        initiateStart2()
        Thread.sleep(700)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        println("==========THIRD ATTEMPT=========")
        initiateStart3()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun initiateStart1() {
    val engineFuture = spaceship.startEngines()
    val cleaningFuture = spaceship.startAutoCleaning()

    println("Futures started!")

    spaceship.setOff(engineFuture.get())
    reportCleaning(cleaningFuture.get())
}

fun initiateStart2() {
    reportCleaning(
        spaceship
            .startEngines()
            .thenCombine(spaceship.startAutoCleaning())
            { speed, isCleaningOk ->
                spaceship.setOff(speed)
                isCleaningOk
            }
            .get()
    )
}

suspend fun initiateStart3() = coroutineScope {
    val engineDeferred = async { suspendingShip.startEngines() }
    val cleaningFuture = async { suspendingShip.autoClean() }

    println("Coroutines started!")

    spaceship.setOff(engineDeferred.await())
    reportCleaning(cleaningFuture.await())
}