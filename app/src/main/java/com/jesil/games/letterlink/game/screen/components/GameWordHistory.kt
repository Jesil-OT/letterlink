package com.jesil.games.letterlink.game.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesil.games.letterlink.core.components.WordHistoryItem
import com.jesil.games.letterlink.core.components.WordPlaceholder
import com.jesil.games.letterlink.ui.theme.GameInk
import com.jesil.games.letterlink.ui.theme.GameTextColor

@Composable
fun GameWordHistory(
   wordHistory: List<String>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {
            val placeHolderCount = if (!wordHistory.isEmpty()) 2 else 7
            if (!wordHistory.isEmpty()) {
                itemsIndexed(wordHistory) { index, word ->
                    val isLast = index == wordHistory.lastIndex
                    WordHistoryItem(
                        modifier = Modifier,
                        surfaceColor = if (isLast) GameInk else Color.White,
                        wordColor = if (isLast) Color.White else GameTextColor,
                        word = word,
                    )
                }
            }
            items(placeHolderCount){
                WordPlaceholder(
                    modifier = Modifier.size(width = 45.dp, height = 28.dp),
                )
            }
        }
    )
}
