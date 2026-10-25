package com.jesil.games.letterlink.core.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jesil.games.letterlink.ui.theme.GameInk
@Composable
fun GameSurface(
    modifier: Modifier = Modifier,
    face: Color = Color.White,
    shape: Shape = RoundedCornerShape(14.dp),
    depth: Dp = 4.dp,
    outline: Dp = 2.dp,
    ink: Color = GameInk,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val dy by animateDpAsState(if (pressed) depth else 0.dp, label = "press")

    Box(
        modifier = Modifier
            .padding(bottom = depth) // reserves room for the lip
            .then(modifier)          // caller's size = size of the face
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = source,
                        indication = null,
                        role = Role.Button,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                }
            ),
        propagateMinConstraints = true, // lets the face fill a fixed size
    ) {
        Box(
            Modifier
                .matchParentSize()
                .offset(y = depth)
                .background(ink, shape)
        )
        // the face: sits on top, drops onto the lip while pressed
        Box(
            modifier = Modifier
                .offset(y = dy)
                .background(face, shape)
                .border(outline, ink, shape),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}





