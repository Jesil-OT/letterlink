package com.jesil.games.letterlink.game.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesil.games.letterlink.game.screen.components.GameInnerSection
import com.jesil.games.letterlink.game.screen.components.GameScoreBoard
import com.jesil.games.letterlink.game.screen.components.GameTopbar
import com.jesil.games.letterlink.game.screen.components.GameWordHistory
import com.jesil.games.letterlink.ui.theme.GameYellow

@Composable
fun GameScreen() {
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
                onPause = {},
                onBack = {}
            )
            GameScoreBoard(
                playerNumberOne = "1",
                playerNumberTwo = "2",
                playerLabelOne = "Player 1 · your turn",
                playerLabelTwo = "Player 2",
                playerOnePoints = 3,
                playerTwoPoints = 2,
                playerLabelOneActive = true,
                playerLabelTwoActive = false,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            GameWordHistory()
            Spacer(Modifier.height(16.dp))
            GameInnerSection()
        }
    )
}

@Preview
@Composable
private fun GameScreenPreview() {
    GameScreen()
}