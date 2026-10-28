package com.jesil.games.letterlink.game.screen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.jesil.games.letterlink.core.domain.Reason
import com.jesil.games.letterlink.game.model.GameUiState
import com.jesil.games.letterlink.game.screen.GameActions
import com.jesil.games.letterlink.ui.theme.GameOrange
import com.jesil.games.letterlink.ui.theme.GameTeal
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun GameInnerSection(
    state: GameUiState,
    onActions: (GameActions) -> Unit,
    modifier: Modifier = Modifier
) {
    val game = state.game ?: return
    // The letter currently placed in the selected tile (null until a key is tapped).
    val picked: Char? = state.selectedIndex
        ?.takeIf { state.draft != game.currentWord }
        ?.let { state.draft[it] }

    val changed = state.draft != game.currentWord
    val message = when {
        state.selectedIndex == null -> "Pick a tile, then a new letter"
        !changed -> "Now pick a new letter"
        state.draftProblem == null -> "Looks good. Tap submit"
        else -> when (state.draftProblem) {
            Reason.NOT_A_WORD -> "Not in the word list"
            Reason.ALREADY_USED -> "Already played"
            Reason.NOT_ONE_LETTER -> "Change exactly one letter"
        }
    }

    val isProblem = changed && state.draftProblem != null

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
                    GameTimer(timer = formatTime(state.secondsLeft))
                    Spacer(Modifier.height(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        content = {
                            state.draft.forEachIndexed { index, wordChar ->
                                GameTile(
                                    letter = wordChar.uppercaseChar().toString(),
                                    face = if (state.selectedIndex == index) GameOrange else Color.White,
                                    letterSize = 45.sp,
                                    modifier = Modifier.size(height = 76.dp, width = 68.dp),
                                    onClick = {
                                        onActions(GameActions.OnTileSelected(index))
                                    }
                                )
                            }
                        }
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = message,
                        style = Typographys.bodyMedium.copy(
                            color = if (isProblem) Color(0xFFB3261E) else Color(0xFF4A5568),
                            fontSize = 14.sp,
                        )
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        contentPadding = PaddingValues(vertical = 15.dp),
                        content = {
                            items(alphabets) { alphabet ->
                                val face = when (alphabet) {
                                    picked -> GameTeal
                                    in state.hintLetters -> GameTeal.copy(alpha = 0.45f)
                                    else -> Color.White
                                }
                                GameActionKeyboardItem(
                                    alphabet = alphabet.toString(),
                                    face = face,
                                    onAlphabetLetter = {
                                        onActions(GameActions.OnLetterPicked(alphabet))
                                    },
                                    isSelected = alphabet == picked,
                                )
                            }
                        }
                    )
                    Spacer(Modifier.weight(1f))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            GameActionHint(onHint = { onActions(GameActions.OnHint) })
                            GameActionSubmit(onSubmit = { onActions(GameActions.OnSubmit) })
                            GameActionDelete(onDelete = { onActions(GameActions.OnClear) })
                        }
                    )
                }
            )
        }
    )
}

private fun formatTime(seconds: Int) = "%02d:%02d".format(seconds / 60, seconds % 60)
private val alphabets = ('a'..'z').toList()