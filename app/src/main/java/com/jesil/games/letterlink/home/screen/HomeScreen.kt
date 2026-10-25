package com.jesil.games.letterlink.home.screen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.core.components.GameButton
import com.jesil.games.letterlink.core.components.GameTile
import com.jesil.games.letterlink.ui.theme.GameBrown
import com.jesil.games.letterlink.ui.theme.GameOrange
import com.jesil.games.letterlink.ui.theme.GameTeal
import com.jesil.games.letterlink.ui.theme.GameTextColor
import com.jesil.games.letterlink.ui.theme.GameYellow
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameYellow)
            .padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            val infiniteTransition = rememberInfiniteTransition()
            val pulsate = infiniteTransition.animateFloat(
                initialValue = 50f,
                targetValue = 53f,
                animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                "LETTER".forEachIndexed { i, c ->
                    GameTile(
                        modifier = Modifier.size(height = 58.dp, width = pulsate.value.dp),
                        letter = c.toString(),
                        face = when (i) {
                            0 -> GameBrown
                            1 -> Color.White
                            2 -> GameTeal
                            3 -> GameOrange
                            4 -> Color.White
                            else -> GameTeal
                        }
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                "LINK".forEachIndexed { i, c ->
                    GameTile(
                        modifier = Modifier.size(height = 58.dp, width = pulsate.value.dp),
                        letter = c.toString(),
                        face = when (i) {
                            0 -> Color.White
                            1 -> GameOrange
                            2 -> GameTeal
                            else -> GameBrown
                        }
                    )
                }
            }
            Spacer(Modifier.height(25.dp))
            Text(
                text = "Change one letter, Outlast your rival.",
                style = Typographys.bodySmall.copy(
                    fontSize = 18.sp
                ),
                color = GameTextColor
            )
            Spacer(Modifier.height(30.dp))
            Box(
                Modifier
                    .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 25.dp),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        content = {
                            GameButton(
                                title = "Pass & Play",
                                subtitle = "Two players, one phone",
                                onClick = {}
                            )
                            Spacer(Modifier.height(12.dp))
                            GameButton(
                                title = "Vs Computer",
                                subtitle = "Practice against the bot",
                                face = Color.White,
                                onClick = {}
                            )
                            Spacer(Modifier.height(12.dp))
                            GameButton(
                                title = "Online",
                                subtitle = "Challenge a friend",
                                face = Color.White,
                                onClick = {}
                            )
                            Spacer(Modifier.weight(1f))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(30.dp),
                                content = {
                                    Text(
                                        text = "Leaderboard",
                                        style = Typographys.bodyMedium.copy(
                                            color = GameTextColor,
                                            fontSize = 16.sp
                                        )
                                    )
                                    Text(
                                        text = "How to play",
                                        style = Typographys.bodyMedium.copy(
                                            color = GameTextColor,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}