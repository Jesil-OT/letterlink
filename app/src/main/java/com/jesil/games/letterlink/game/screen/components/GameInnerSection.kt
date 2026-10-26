package com.jesil.games.letterlink.game.screen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.core.components.GameActionDelete
import com.jesil.games.letterlink.core.components.GameActionHint
import com.jesil.games.letterlink.core.components.GameActionKeyboardItem
import com.jesil.games.letterlink.core.components.GameActionSubmit
import com.jesil.games.letterlink.core.components.GameTile
import com.jesil.games.letterlink.core.components.GameTimer
import com.jesil.games.letterlink.ui.theme.GameOrange
import com.jesil.games.letterlink.ui.theme.GameTeal
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun GameInnerSection(modifier: Modifier = Modifier) {
    val currentWord = "Tens"
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val alphabetIndex = remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
    Box(
        modifier
            .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 25.dp),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    GameTimer(timer = "00:24")
                    Spacer(Modifier.height(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        content = {
                            currentWord.forEachIndexed { index, wordChar ->
                                val isSelected = selectedIndex.value == index
                                GameTile(
                                    letter = wordChar.toString(),
                                    face = if (isSelected) GameOrange else Color.White,
                                    letterSize = 45.sp,
                                    modifier = Modifier.size(height = 76.dp, width = 68.dp),
                                    onClick = {
                                        selectedIndex.value = index
                                    }
                                )
                            }
                        }
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = "Pick a tile, then a new letter",
                        style = Typographys.bodyMedium.copy(
                            color = Color(0xFF4A5568),
                            fontSize = 14.sp
                        )
                    )
                    Spacer(Modifier.height(15.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        content = {
                            itemsIndexed(alphabets){ index, alphabet ->
                                val playerOneSelection = alphabetIndex.value == index
                                val face = if (playerOneSelection) GameTeal else Color.White
                                GameActionKeyboardItem(
                                    alphabet = alphabet.toString(),
                                    face = face,
                                    onAlphabetLetter = {
                                        alphabetIndex.value = index
                                    },
                                    isSelected = playerOneSelection
                                )
                            }
                        }
                    )
                    Spacer(Modifier.weight(1f))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            GameActionHint(onHint = {})
                            GameActionSubmit(onSubmit = {
                                selectedIndex.value = null
                                alphabetIndex.value = null
                            })
                            GameActionDelete(onDelete = {
                                selectedIndex.value = null
                                alphabetIndex.value = null
                            })
                        }
                    )
                }
            )
        }
    )
}

private val alphabets = listOf(
    'a', 'b', 'c', 'd', 'e', 'f', 'g',
    'h', 'i', 'j', 'k', 'l', 'm', 'n',
    'o', 'p', 'q', 'r', 's', 't', 'u',
    'v', 'w', 'x', 'y', 'z'
)

@Preview
@Composable
private fun GameInnerSectionPreview() {
    GameInnerSection()
}