package com.varani.isitvegan.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkGreenGray10,
    onPrimary = GreenGray90,
    secondary = Green20,
    onSecondary = GreenGray80,
    error = Red80,
    onError = Red20,
    background = DarkGreenGray20,
    onBackground = GreenGray90,
    surface = GreenGray30,
    onSurface = GreenGray90,
)

private val LightColorPalette = lightColors(
    primary = Green40,
    onPrimary = Color.White,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    error = Red40,
    onError = Color.White,
    background = DarkGreenGray99,
    onBackground = DarkGreenGray10,
    surface = DarkGreenGray99,
    onSurface = DarkGreenGray10,
)

@Composable
fun IsItVeganTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}