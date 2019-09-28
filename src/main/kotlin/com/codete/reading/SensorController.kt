package com.codete.reading

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

object SensorController {

    fun getAllReadings(): List<SensorReading> =
        listOf(
            getTempReading(),
            getSolarReading()
        )

    private fun getTempReading(): SensorReading {
        Thread.sleep(500) //sensor processing data
        return SensorReading("temp", -270.1)
    }

    private fun getSolarReading(): SensorReading {
        Thread.sleep(700) //sensor processing data
        return SensorReading("sun", 372.0)
    }

    fun getAllReadingsInFlow(): Flow<SensorReading> = flow {
        emit(getTempReadingSuspending())
        emit(getSolarReadingSuspending())
    }

    private suspend fun getTempReadingSuspending(): SensorReading = withContext(Dispatchers.IO) {
        Thread.sleep(1500) //sensor processing data
        SensorReading("temp", -270.1)
    }

    private suspend fun getSolarReadingSuspending(): SensorReading = withContext(Dispatchers.IO) {
        Thread.sleep(1500) //sensor processing data
        SensorReading("sun", 372.0)
    }
}

fun main() {
//    getReadings1()
    getReadings2()
}

private fun getReadings1() {
    runBlocking {
        launch {
            for (i in 1..8) {
                println("Actively correcting path...")
                delay(500)
            }
        }
        println("Waiting for the coroutine to launch...")
        delay(500)

        println("Starting blocking measurements...")
        val readings = SensorController.getAllReadings()
        println(readings)

        println("Starting non-blocking measurements...")
        val flow = SensorController.getAllReadingsInFlow()
        flow.collect { println(it) }
    }
}

@ExperimentalCoroutinesApi
private fun getReadings2() {
    runBlocking {
        launch {
            repeat(15) {
                println("Actively correcting path...")
                delay(500)
            }
        }
        println("Waiting for the coroutine to launch...")
        delay(500)

        println("Starting blocking measurements...")
        val readings = SensorController.getAllReadings()
        println(readings)
        readings.forEach {
            Thread.sleep(1000) //Mark's validation
            println(it)
        }

        println("Starting non-blocking measurements...")
        val flow = SensorController.getAllReadingsInFlow()
        println(flow)
        flow
            .validateMeasurement()
            .collect { println(it) }
    }
}

@ExperimentalCoroutinesApi
fun Flow<SensorReading>.validateMeasurement(): Flow<SensorReading> =
    transform { sensorReading ->
        delay(1000)
        if (sensorReading.value > 1000) throw IllegalStateException()
    }