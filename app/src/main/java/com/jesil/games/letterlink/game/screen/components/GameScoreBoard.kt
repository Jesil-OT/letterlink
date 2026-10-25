package com.jesil.games.letterlink.game.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jesil.games.letterlink.core.components.GameScoreCard
import com.jesil.games.letterlink.ui.theme.GameBrown
import com.jesil.games.letterlink.ui.theme.GameTeal

@Composable
fun GameScoreBoard(
    modifier: Modifier = Modifier,
    playerNumberOne: String,
    playerNumberTwo: String,
    playerLabelOne: String,
    playerLabelTwo: String,
    playerOnePoints: Int,
    playerTwoPoints: Int,
    playerLabelOneActive: Boolean,
    playerLabelTwoActive: Boolean,
) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        GameScoreCard(
            playerNumberOne,
            playerLabelOne,
            playerOnePoints,
            active = playerLabelOneActive,
            playerColor = GameTeal,
            modifier = Modifier.weight(1f)
        )
        GameScoreCard(
            playerNumberTwo,
            playerLabelTwo,
            playerTwoPoints,
            active = playerLabelTwoActive,
            playerColor = GameBrown,
            modifier = Modifier.weight(1f)
        )
    }
}