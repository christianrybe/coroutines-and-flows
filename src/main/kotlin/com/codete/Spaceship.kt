package com.codete

import java.util.concurrent.CompletableFuture


class Spaceship {

    fun startEngines(): CompletableFuture<Int> {
        return CompletableFuture.supplyAsync {
            println("Starting engines from future")
            Thread.sleep(700)
            120
        }
    }

    fun startAutoCleaning(): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            println("Starting cleaning")
            Thread.sleep(500)
            throw RuntimeException()
        }
    }

    fun setOff(speed: Int) {
        println("Spaceship is taking off with the initial speed of $speed")
    }

    companion object {
        const val ENGINES_COUNT = 5

        fun reportCleaning(result: Boolean?) {
            println("Cleaning completed with result $result")
        }
    }
}