package com.example.tasker.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = LightColors.teal,
    onPrimary = Color.White,
    surface = LightColors.surface,
    background = LightColors.bg,
    onSurface = LightColors.ink,
    secondaryContainer = LightColors.tealSoft
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(18.dp),
    small = RoundedCornerShape(18.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(18.dp)
)

object TaskerTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}

@Composable
fun TaskerTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppColors provides LightColors) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}
