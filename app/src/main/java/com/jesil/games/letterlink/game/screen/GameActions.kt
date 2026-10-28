package com.jesil.games.letterlink.game.screen

sealed interface GameActions{
    data class OnTileSelected(val tileIndex: Int): GameActions
    data class OnLetterPicked(val letter: Char): GameActions
    data object OnHint: GameActions
    data object OnSubmit: GameActions
    data object OnClear: GameActions
}
