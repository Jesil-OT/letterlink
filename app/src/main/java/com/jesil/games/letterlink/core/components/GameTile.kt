package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.ui.theme.GameInk
import com.jesil.games.letterlink.ui.theme.Typographys
import java.util.Locale.getDefault

@Composable
fun GameTile(
    letter: String,
    face: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    GameSurface(modifier.size(width = 50.dp, height = 58.dp), face = face, onClick = onClick) {
        Text(letter.uppercase(getDefault()), fontSize = 30.sp, style = Typographys.bodyLarge, color = GameInk)
    }
}

@Preview
@Composable
private fun GameTilePreview()  {
    Box(
        modifier = Modifier.size(200.dp).background(color = Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        GameTile(
            letter = "W",
            face = Color.White
        ){}
    }
}