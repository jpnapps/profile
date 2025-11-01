package com.jpn.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define your base color schemes
val LightSunnyTheme = lightColorScheme(
    primary = Color(0xFFFFC107),
    secondary = Color(0xFF4CAF50),
    background = Color(0xFFFFF8E1),
    onBackground = Color(0xFF212121),
)

val DarkMidnightTheme = darkColorScheme(
    primary = Color(0xFFFFA000),
    secondary = Color(0xFF81C784),
    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF),
)

val LightBlueTheme = lightColorScheme(
    primary = Color(0xFF2196F3),
    secondary = Color(0xFF03A9F4),
    background = Color(0xFFE3F2FD),
    onBackground = Color(0xFF212121),
)

val LightForestTheme = lightColorScheme(
    primary = Color(0xFF2E7D32),
    secondary = Color(0xFF81C784),
    background = Color(0xFFE8F5E9),
    onBackground = Color(0xFF1B5E20),
)

enum class AppThemeType {
    SUNNY, MIDNIGHT, BLUE, FOREST
}

fun getColorScheme(themeType: AppThemeType, darkTheme: Boolean): ColorScheme {
    return when (themeType) {
        AppThemeType.SUNNY -> if (darkTheme) DarkMidnightTheme else LightSunnyTheme
        AppThemeType.MIDNIGHT -> DarkMidnightTheme
        AppThemeType.BLUE -> LightBlueTheme
        AppThemeType.FOREST -> LightForestTheme
    }
}
