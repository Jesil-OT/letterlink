package com.jesil.games.letterlink.game.model

import com.jesil.games.letterlink.core.domain.GameState
import com.jesil.games.letterlink.core.domain.Reason

data class GameUiState(
    val game: GameState? = null,            // null while the word list loads
    val selectedIndex: Int? = null,         // which tile is selected (orange)
    val draft: String = "",                 // the word shown in the tiles right now
    val draftProblem: Reason? = null,       // live check of the draft (the red/green line)
    val hintLetters: Set<Char> = emptySet(),// keys to paint teal
    val secondsLeft: Int = 0,
    val paused: Boolean = false,
    val awaitingReady: Boolean = true,      // "Player 2, ready?" screen, timer paused
) {
    val loading: Boolean get() = game == null
}
