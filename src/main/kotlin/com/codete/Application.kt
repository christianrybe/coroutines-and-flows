package com.codete


fun main() {

    val spaceship = Spaceship()

    val engineFuture = spaceship.startEngines()
    val cleaningFuture = spaceship.startAutoCleaning()

    engineFuture.whenComplete { _, failure -> if (failure == null) spaceship.calculateShortestPathToNextStation() }
}
