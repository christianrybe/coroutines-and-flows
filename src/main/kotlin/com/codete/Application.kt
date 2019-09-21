package com.codete

import com.codete.Spaceship.Companion.reportCleaning

val spaceship = Spaceship()

fun main() {
    try {
        initiateStart1()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        initiateStart2()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun initiateStart1() {
    val engineFuture = spaceship.startEngines()
    val cleaningFuture = spaceship.startAutoCleaning()

    engineFuture.whenComplete { speed, failure -> if (failure == null) spaceship.setOff(speed) }
    cleaningFuture.whenComplete { result, failure -> if (failure == null) reportCleaning(result) }
}

fun initiateStart2() {
    spaceship.startEngines()
        .thenCombine(spaceship.startAutoCleaning()) { speed, two ->
            spaceship.setOff(speed)
            two
        }.whenComplete { result, failure -> if (failure == null) reportCleaning(result) }
}