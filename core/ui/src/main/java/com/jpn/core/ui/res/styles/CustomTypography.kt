package com.jpn.core.ui.res.styles

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.jpn.core.ui.res.fonts.MontserratFontFamily
import com.jpn.core.ui.res.values.TextSize

val CustomTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = TextSize.titleSize
    )
)
