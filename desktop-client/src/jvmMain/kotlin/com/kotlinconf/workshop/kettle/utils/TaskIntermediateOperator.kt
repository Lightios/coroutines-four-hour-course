package com.kotlinconf.workshop.kettle.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Task: Implement a custom averageOfLast flow operator
fun Flow<Double>.averageOfLast(n: Int): Flow<Double> = flow {
    val lastElements = mutableListOf<Double>()

    collect { newValue ->
        lastElements.add(newValue)
        emit(lastElements.takeLast(5).average())
    }
}