package com.jesil.games.letterlink.core.domain

data class Player(
    val id: Int,
    val name: String,
    val scores: Int = 0
)
