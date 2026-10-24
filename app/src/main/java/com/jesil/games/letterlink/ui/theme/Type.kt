package com.jesil.games.letterlink.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jesil.games.letterlink.R

// Set of Material typography styles to start with
val Typographys = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_bold)),
        fontWeight = FontWeight.W700,
    /*    fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp*/
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_semibold)),
        fontWeight = FontWeight.W600
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_medium)),
        fontWeight = FontWeight.W500
    )
)