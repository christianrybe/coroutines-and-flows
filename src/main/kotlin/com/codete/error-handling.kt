package com.codete

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private fun dataFlow() = flow {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    throw InappropriateException()
}

@ExperimentalCoroutinesApi
fun <T> Flow<T>.sendMailOnInappropriateException(): Flow<T> =
    catch { if (it is InappropriateException) println("Sending email to the BI team...") }

fun Flow<Int>.failAfterThree(): Flow<Int> = transform { value ->
    if (value <= 3) emit(value) else throw RuntimeException()
}

@ExperimentalCoroutinesApi
suspend fun main() {
    coroutineScope {
        launch {
            dataFlow()
                .sendMailOnInappropriateException()
                .failAfterThree()
                .catch { e -> println("Caught exception $e") }
                .collect { println(it) }
        }
    }
}

class InappropriateException: RuntimeException()