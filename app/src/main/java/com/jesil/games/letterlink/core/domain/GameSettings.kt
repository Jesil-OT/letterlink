package com.jesil.games.letterlink.core.domain

data class GameSettings(
    val targetScore : Int = 10,
    val turnSeconds: Int = 30,
    val playerNames: List<String> = listOf("Player 1", "Player 2"),
)
