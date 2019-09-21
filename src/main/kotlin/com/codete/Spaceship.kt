package com.codete

import java.util.concurrent.CompletableFuture


class Spaceship {

    //    val engines: Map<Int, Engine> = (1..ENGINES_COUNT).zip(generateSequence { Engine() }.take(ENGINES_COUNT).toList()).toMap()
    fun startEngines(): CompletableFuture<Int> {
//        Thread.sleep(5000)
        return CompletableFuture.supplyAsync { 120 }
    }

    fun startAutoCleaning(): CompletableFuture<Result> {
        val completableFuture = CompletableFuture<Result>()
        completableFuture.completeExceptionally(RuntimeException())
        return completableFuture
    }

    fun setOff(speed: Int) {
        println("Spaceship is taking off with the initial speed of $speed")
    }

    companion object {
        const val ENGINES_COUNT = 5

        fun reportCleaning(result: Result?) {
            println("Cleaning completed with result $result")
        }
    }
}