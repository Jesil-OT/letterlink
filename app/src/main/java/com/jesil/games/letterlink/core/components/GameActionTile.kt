package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jesil.games.letterlink.R
import com.jesil.games.letterlink.ui.theme.GameTextColor

@Composable
fun GameActionPause(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onClick, borderColor = GameTextColor,
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
    onClick: () -> Unit,
) {
    GameActionSurface(
        modifier = modifier.size(48.dp), onClick = onClick, borderColor = GameTextColor,
        content = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                contentDescription = "Go back to menu",
                tint = GameTextColor
            )
        }
    )
}
