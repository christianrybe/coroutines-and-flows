package com.codete

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

    companion object {
        const val ENGINES_COUNT = 5

        fun reportCleaning(result: Boolean?) {
            println("Cleaning completed with result $result")
        }
    }
}