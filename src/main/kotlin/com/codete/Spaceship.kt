package com.codete

import java.util.concurrent.CompletableFuture

class Spaceship {

    //    val engines: Map<Int, Engine> = (1..ENGINES_COUNT).zip(generateSequence { Engine() }.take(ENGINES_COUNT).toList()).toMap()
    fun startEngines(): CompletableFuture<Unit> {
        return CompletableFuture()
    }

    fun startAutoCleaning(): CompletableFuture<Result> {
        return CompletableFuture()
    }

    fun calculateShortestPathToNextStation(): CompletableFuture<List<Int>> {
        return CompletableFuture()
    }

    companion object {
        const val ENGINES_COUNT = 5
    }
}