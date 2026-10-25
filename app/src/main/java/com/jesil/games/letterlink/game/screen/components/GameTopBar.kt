package com.jesil.games.letterlink.game.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.core.components.GameActionBack
import com.jesil.games.letterlink.core.components.GameActionPause
import com.jesil.games.letterlink.ui.theme.GameTextColor
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun GameTopbar(
    modifier: Modifier = Modifier,
    round:  String,
    onPause: () -> Unit,
    onBack: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            GameActionBack (onClick = onBack)
            Text(
                modifier = Modifier.weight(1f),
                text = round,
                textAlign = TextAlign.Center,
                style = Typographys.bodyLarge.copy(
                    color = GameTextColor,
                    fontSize = 18.sp
                )
            )
            GameActionPause(onClick = onPause)
        }
    )
}