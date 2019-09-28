package com.codete

import com.codete.coroutine.SuspendingShip
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.CompletableFuture


class Spaceship {

    fun startEngines(): CompletableFuture<Int> {
        return CompletableFuture.supplyAsync {
            println("Starting engines from Completable Future")
            Thread.sleep(700) //heavy work
            120
        }
    }

    fun startAutoCleaning(): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            println("Starting cleaning from Completable Future")
            Thread.sleep(500) //takes a while too
            throw RuntimeException()
        }
    }

    fun setOff(speed: Int) {
        println("Spaceship is taking off with speed: $speed")
    }

    fun reportCleaning(result: Boolean?) {
        println("Cleaning completed with result $result")
    }
}

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
    spaceship.reportCleaning(cleaningFuture.get())
}

fun initiateStart2() {
    val engineAndCleaningFuture = spaceship.startEngines()
        .thenCombine(spaceship.startAutoCleaning())
        { speed, isCleaningOk ->
            spaceship.setOff(speed)
            isCleaningOk
        }
    spaceship.reportCleaning(engineAndCleaningFuture.get())
}

suspend fun initiateStart3() = coroutineScope {
    val engineDeferred = async { suspendingShip.startEngines() }
    val cleaningDeferred = async { suspendingShip.autoClean() }

    println("Coroutines started!")

    spaceship.setOff(engineDeferred.await())
    spaceship.reportCleaning(cleaningDeferred.await())
}