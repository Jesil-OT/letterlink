package com.jesil.games.letterlink.core.domain

sealed interface TurnResult {
    data object None : TurnResult
    data class Scored(val playerId: Int, val points: Int) : TurnResult

    // reason == null means the timer ran out
    data class Missed(val playerId: Int, val reason: Reason?) : TurnResult
}
