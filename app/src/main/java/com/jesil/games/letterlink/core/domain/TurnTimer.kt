package com.jesil.games.letterlink.core.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class TurnTimer(private val scope: CoroutineScope) {
    private val _secondsLeft = MutableStateFlow(0)
    val secondsLeft: StateFlow<Int> = _secondsLeft.asStateFlow()
    private var job: Job? = null

    fun start(seconds: Int, onFinish: () -> Unit) {
        job?.cancel()
        job = scope.launch {
            for (s in seconds downTo 1) {
                _secondsLeft.value = s
                delay(1_000.milliseconds)
            }
            _secondsLeft.value = 0
            onFinish()
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}
