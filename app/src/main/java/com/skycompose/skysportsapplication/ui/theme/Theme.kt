package com.skycompose.skysportsapplication.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette =
    lightColors(
        primary = Color.Black
    )

@Composable
fun SkySportsApplicationTheme(
    content: @Composable() () -> Unit
) {
    val colors = LightColorPalette
    MaterialTheme(colors = colors, typography = SkyTypography, shapes = Shapes, content = content)
}
