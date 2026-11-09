package com.example.tasker.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
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

private val DarkColorScheme = darkColorScheme(
    primary = DarkColors.teal,
    onPrimary = Color.White,
    surface = DarkColors.surface,
    background = DarkColors.bg,
    onSurface = DarkColors.ink,
    onBackground = DarkColors.ink,
    onSurfaceVariant = DarkColors.muted,
    secondaryContainer = DarkColors.tealSoft,
    onSecondaryContainer = DarkColors.tealDark,
    surfaceContainerHigh = DarkColors.surface,
    surfaceContainerHighest = DarkColors.border,
    outline = DarkColors.border,
    outlineVariant = DarkColors.border
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
fun TaskerTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors

    CompositionLocalProvider(LocalAppColors provides colors) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}
