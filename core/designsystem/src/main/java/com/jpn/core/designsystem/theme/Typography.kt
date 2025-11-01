package com.jpn.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jpn.core.designsystem.theme.res.fonts.MontserratFontFamily
import com.jpn.core.designsystem.theme.res.fonts.Poppins

// Build a proper Material3 Typography set
fun AppTypography(themeType: AppThemeType): Typography {
    val selectedFont = when (themeType) {
        AppThemeType.SUNNY -> Poppins
        AppThemeType.MIDNIGHT -> MontserratFontFamily
        AppThemeType.BLUE -> Poppins
        AppThemeType.FOREST -> Poppins
    }

    return Typography(
        displayLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp
        ),
        displayMedium = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 45.sp
        ),
        displaySmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),
        titleLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp
        ),
        titleMedium = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        titleSmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodySmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        labelLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        labelMedium = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        labelSmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
        )
    )
}
