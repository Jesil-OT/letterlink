package com.jesil.games.letterlink.game.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.core.components.GameButton
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun MessageOverlay(
    title: String,
    message: String?,
    buttonText: String,
    onButton: () -> Unit,
    secondaryText: String? = null,
    onSecondary: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .pointerInput(Unit) {}, // swallow touches so the game underneath can't be tapped
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                title, style = Typographys.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 20.sp
                ), textAlign = TextAlign.Center
            )
            if (message != null) {
                Text(
                    message, style = Typographys.bodyMedium.copy(
                        color = Color.Black,
                        fontSize = 18.sp
                    ), textAlign = TextAlign.Center
                )
            }
            GameButton(title = buttonText, onClick = onButton)
            if (secondaryText != null) {
                TextButton(onClick = onSecondary) {
                    Text(
                        secondaryText,
                        style = Typographys.bodyMedium.copy(
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    )
                }
            }
        }
    }
}
