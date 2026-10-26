package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.ui.theme.GameInk
import com.jesil.games.letterlink.ui.theme.GamePaintYellow
import com.jesil.games.letterlink.ui.theme.GameTextColor
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun GameScoreCard(
    player: String,
    label: String,
    points: Int,
    active: Boolean,
    playerColor: Color,
    modifier: Modifier = Modifier,
) {
    GameSurface(
        modifier = modifier.heightIn(80.dp),
        face = if (active) Color.White else GamePaintYellow,
        depth = 3.dp,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .background(playerColor, CircleShape)
                    .border(1.5.dp, GameInk, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    player, style = Typographys.bodyLarge.copy(
                        color = GameTextColor,
                        fontSize = 16.sp
                    )
                )
            }
            Column {
                Text(
                    label,
                    style = Typographys.bodyLarge.copy(
                        color = GameTextColor,
                        fontSize = 13.sp
                    ),
                )
                Text(
                    "$points pts",
                    style = Typographys.bodyLarge.copy(
                        color = GameTextColor,
                        fontSize = 20.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun GameTimer(
    modifier: Modifier = Modifier,
    timer: String
) {
    GameActionSurface(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(360.dp),
        borderColor = GameTextColor
    ) {
        Text(
            timer,
            style = Typographys.bodyLarge.copy(
                color = GameTextColor,
                fontSize = 16.sp
            )
        )
    }
}