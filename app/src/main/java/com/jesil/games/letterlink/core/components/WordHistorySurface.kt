package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.ui.theme.GamePaintYellow
import com.jesil.games.letterlink.ui.theme.Typographys
import java.util.Locale.getDefault

@Composable
fun WordHistorySurface(
    modifier: Modifier = Modifier,
    surfaceColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier
            .clip(RoundedCornerShape(360.dp))
            .background(surfaceColor),
        contentAlignment = Alignment.Center,
        content = content
    )

}

@Composable
fun WordHistoryItem(
    modifier: Modifier = Modifier,
    surfaceColor: Color,
    wordColor: Color,
    word: String,
) {
    WordHistorySurface(
        modifier = modifier,
        surfaceColor = surfaceColor,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 13.dp, vertical = 6.dp),
                text = word.uppercase(getDefault()),
                style = Typographys.bodyMedium.copy(
                    color = wordColor,
                    fontSize = 14.sp
                )
            )
        }
    )
}

@Composable
fun WordPlaceholder(
    modifier: Modifier = Modifier
) {
    WordHistorySurface(
        modifier = modifier,
        surfaceColor = GamePaintYellow,
        content = {}
    )
}