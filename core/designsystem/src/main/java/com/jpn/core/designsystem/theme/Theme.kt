package com.jpn.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Light color scheme
private val LightColorScheme = lightColorScheme(
    primary = SunnyYellow,
    onPrimary = TextLight,
    secondary = FreshGreen,
    onSecondary = TextLight,
    tertiary = BrightSkyBlue,
    onTertiary = TextLight,
    background = WarmWhite,
    onBackground = TextDark,
    surface = TextLight,
    onSurface = TextDark
)

// Optional: dark mode scheme (can tune later)
private val DarkColorScheme = darkColorScheme(
    primary = GoldenGlow,
    onPrimary = TextDark,
    secondary = GentleGreen,
    onSecondary = TextDark,
    tertiary = CrystalAqua,
    onTertiary = TextDark,
    background = CozyNightBlack,
    onBackground = TextLight,
    surface = SoftCharcoal,
    onSurface = TextLight
)

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

@Composable
fun JithishTheme(
    themeType: AppThemeType = AppThemeType.SUNNY,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        /*      darkTheme -> DarkColorScheme
              else -> LightColorScheme*/
        else -> getColorScheme(themeType, darkTheme)
    }

    val appColors = AppColors(
        studyLight = StudyLight,
        studyDark = StudyDark,
        programsLight = ProgramsLight,
        programsDark = ProgramsDark,
        exerciseLight = ExerciseLight,
        exerciseDark = ExerciseDark,
        waterLight = WaterLight,
        waterDark = WaterDark
    )

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography(themeType),
            content = content
        )
    }
}

data class AppColors(
    val studyLight: Color,
    val studyDark: Color,
    val programsLight: Color,
    val programsDark: Color,
    val exerciseLight: Color,
    val exerciseDark: Color,
    val waterLight: Color,
    val waterDark: Color
)

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}


/*private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    // Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)*/


/*
@Composable
fun JithishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
*/