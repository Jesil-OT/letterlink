package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.R
import com.jesil.games.letterlink.ui.theme.GameTextColor
import com.jesil.games.letterlink.ui.theme.GameYellow
import com.jesil.games.letterlink.ui.theme.Typographys
import java.util.Locale

@Composable
fun GameActionPause(
    modifier: Modifier = Modifier,
    onPause: () -> Unit,
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onPause, borderColor = GameTextColor,
        content = {
           Icon(
               imageVector = ImageVector.vectorResource(R.drawable.pause_icon),
               contentDescription = "Pause",
               tint = GameTextColor
           )
        }
    )
}

@Composable
fun GameActionBack(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onBack, borderColor = GameTextColor,
        content = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "Go back to menu",
                tint = GameTextColor
            )
        }
    )
}

@Composable
fun GameActionKeyboardItem(
    modifier: Modifier = Modifier,
    alphabet: String,
    face: Color,
    isSelected: Boolean = false,
    onAlphabetLetter: () -> Unit
) {
    val selectedBorder = if (isSelected) GameTextColor else Color(0xFFE3E6EA)
    GameActionSurface(
        modifier = modifier.size(width = 44.dp, height = 46.dp), onClick = onAlphabetLetter, borderColor = selectedBorder,
        backgroundColor = face,
        content = {
            Text(
                alphabet.uppercase(Locale.ROOT),
                style = Typographys.bodyMedium.copy(
                    fontSize = 18.sp,
                    color = GameTextColor
                )
            )
        }
    )
}

@Composable
fun GameActionHint(
    modifier: Modifier = Modifier,
    onHint: () -> Unit
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onHint, borderColor = GameTextColor,
        shape = RoundedCornerShape(16.dp),
        content = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.hint_icon),
                contentDescription = "Get word hint",
                tint = GameTextColor
            )
        }
    )
}

@Composable
fun GameActionDelete(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onDelete, borderColor = GameTextColor,
        shape = RoundedCornerShape(16.dp),
        content = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.delete_icon),
                contentDescription = "Clear your current selection",
                tint = GameTextColor
            )
        }
    )
}

@Composable
fun GameActionSubmit(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit
) {
    GameSurface(
        modifier = modifier
            .width(92.dp)
            .heightIn(min = 64.dp),
        face = GameYellow,
        depth = 5.dp,
        onClick = onSubmit,
        shape = RoundedCornerShape(22.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_next),
            contentDescription = "Submit your word",
            tint = GameTextColor
        )
    }
}