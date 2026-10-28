package com.jesil.games.letterlink.game.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jesil.games.letterlink.core.domain.GameState
import com.jesil.games.letterlink.core.domain.Reason
import com.jesil.games.letterlink.core.domain.TurnResult
import com.jesil.games.letterlink.game.screen.components.GameInnerSection
import com.jesil.games.letterlink.game.screen.components.GameScoreBoard
import com.jesil.games.letterlink.game.screen.components.GameTopbar
import com.jesil.games.letterlink.game.screen.components.GameWordHistory
import com.jesil.games.letterlink.game.screen.components.MessageOverlay
import com.jesil.games.letterlink.ui.theme.GameYellow

@Composable
fun GameScreen() {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val game = state.game

    Box(Modifier.fillMaxSize().background(GameYellow)) {
        if (game == null) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
            return@Box
        }

        val one = game.players[0]
        val two = game.players[1]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GameYellow)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                GameTopbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    round = "Round 3",
                    onPause = viewModel::onPause,
                    onBack = {}
                )
                GameScoreBoard(
                    playerNumberOne = "1",
                    playerNumberTwo = "2",
                    playerLabelOne = if (game.activeIndex == 0) "${one.name} · your turn" else one.name,
                    playerLabelTwo = if (game.activeIndex == 1) "${two.name} · your turn" else two.name,
                    playerOnePoints = one.scores,
                    playerTwoPoints = two.scores,
                    playerLabelOneActive = game.activeIndex == 0,
                    playerLabelTwoActive = game.activeIndex == 1,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
                GameWordHistory(game.history)
                Spacer(Modifier.height(16.dp))
                GameInnerSection(
                    state = state,
                    onActions = viewModel::onAction,
                )
            }
        )

        when {
            game.winnerId != null -> {
                val winner = game.players.first { it.id == game.winnerId }
                MessageOverlay(
                    title = "${winner.name} wins!",
                    message = "${one.name} ${one.scores} – ${two.scores} ${two.name}",
                    buttonText = "Play again",
                    onButton = viewModel::onNewGame,
                    secondaryText = "Back to menu",
                    onSecondary = { /*to do back navigation*/},
                )
            }
            state.paused -> MessageOverlay(
                title = "Paused",
                message = null,
                buttonText = "Resume",
                onButton = viewModel::onResume,
            )
            state.awaitingReady -> MessageOverlay(
                title = "${game.activePlayer.name}, ready?",
                message = resultText(game),
                buttonText = "Ready",
                onButton = viewModel::onReady,
            )
            else -> Unit
        }
    }
}

@Preview
@Composable
private fun GameScreenPreview() {
    GameScreen()
}

// Turns the engine's lastResult into a sentence for the hand-over screen.private fun resultText(game: GameState): String? {
private fun resultText(game: GameState): String? {
    fun name(id: Int) = game.players.first { it.id == id }.name

    val what = when (val r = game.lastResult) {
        TurnResult.None -> null
        is TurnResult.Scored -> "${name(r.playerId)} scored ${r.points}"
        is TurnResult.Missed -> "${name(r.playerId)} " + when (r.reason) {
            null -> "ran out of time"
            Reason.NOT_A_WORD -> "played a word that isn’t in the list"
            Reason.ALREADY_USED -> "repeated a word"
            Reason.NOT_ONE_LETTER -> "changed more than one letter"
        }
    }
    val parts = listOfNotNull(
        what,
        if (game.stealPending) "Steal it for 2 points!" else null,
        if (game.restarted) "New starting word" else null,
    )
    return parts.takeIf { it.isNotEmpty() }?.joinToString(". ")
}


