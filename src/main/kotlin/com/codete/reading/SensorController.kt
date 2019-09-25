package com.codete.reading

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

object SensorController {

    fun getAllReadings(): List<SensorReading> =
        listOf(
            getTempReading(),
            getSolarReading()
        )


    private fun getTempReading(): SensorReading {
        Thread.sleep(500)
        return SensorReading("temp", -270.1)
    }

    private suspend fun getTempReadingSuspending(): SensorReading {
        delay(500)
        return SensorReading("temp", -270.1)
    }


    private fun getSolarReading(): SensorReading {
        Thread.sleep(700)
        return SensorReading("temp", 372.0)
    }

    private suspend fun getSolarReadingSuspending(): SensorReading {
        delay(700)
        return SensorReading("temp", 372.0)
    }

    fun getReadingsInFlow(): Flow<SensorReading> = flow {
        emit(getTempReadingSuspending())
        emit(getSolarReadingSuspending())
    }

}

suspend fun main() {
    println("Starting sync measurements...")
    val time = measureTimeMillis {
        val readings = SensorController.getAllReadings()
        println(readings)
    }

    println("Time took: $time")

    val time2 = measureTimeMillis {
        val flow = SensorController.getReadingsInFlow()
        println("Readings collected")
        flow.collect{ println(it)}
    }

    println("Time took $time2")
}

