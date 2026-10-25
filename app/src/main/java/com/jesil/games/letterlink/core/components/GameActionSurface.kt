package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun GameActionSurface(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    borderColor: Color,
    content: @Composable BoxScope.() -> Unit,
) {
    val source = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(
            interactionSource = source,
            indication = null,
            role = Role.Button,
            onClick = onClick,
        ),
        content = content
    )

}