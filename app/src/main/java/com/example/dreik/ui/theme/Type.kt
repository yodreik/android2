package com.example.dreik.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dreik.R

val Montserrat = FontFamily(
    fonts = listOf(
        Font(R.font.montserrat_regular, FontWeight.Normal),
        Font(R.font.montserrat_bold, FontWeight.Bold),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    titleLarge = TextStyle(
        fontSize = 50.sp,
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        lineHeight = 60.sp
    ),
    labelSmall = TextStyle(
        fontSize = 35.sp,
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        lineHeight = 43.sp
    )
)