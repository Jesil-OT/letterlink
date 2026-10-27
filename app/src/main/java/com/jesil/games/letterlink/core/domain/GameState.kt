package com.jesil.games.letterlink.core.domain

data class GameState(
    val players: List<Player>,
    val history: List<String>,
    val activeIndex: Int = 0,
    val stealPending: Boolean = false,
    val missesInRow: Int = 0,
    val winnerId: Int? = null,
){
    val currentWord: String get() = history.last()
}
