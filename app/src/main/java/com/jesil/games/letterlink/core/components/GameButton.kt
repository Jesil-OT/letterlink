package com.jesil.games.letterlink.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.ui.theme.GameInk
import com.jesil.games.letterlink.ui.theme.GameTextColor
import com.jesil.games.letterlink.ui.theme.GameYellow
import com.jesil.games.letterlink.ui.theme.Typographys

@Composable
fun GameButton(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    face: Color = GameYellow,
    onClick: () -> Unit,
) {
    GameSurface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp),
        face = face,
        depth = 5.dp,
        onClick = onClick,
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                title, style = Typographys.bodyLarge.copy(
                    fontSize = 20.sp,
                    color = GameTextColor
                )
            )
            if (subtitle != null) {
                Text(
                    subtitle, style = Typographys.bodySmall.copy(
                        fontSize = 14.sp,
                        color = GameTextColor.copy(alpha = 0.75f)
                    )
                )
            }
        }
    }
}
