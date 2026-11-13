package com.example.tasker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasker.domain.AccentColor
import com.example.tasker.domain.ThemeMode

private fun AppColors.toLightScheme(): ColorScheme = lightColorScheme(
    primary = accent,
    onPrimary = Color.White,
    surface = surface,
    background = bg,
    onSurface = ink,
    secondaryContainer = accentSoft
)

private fun AppColors.toDarkScheme(): ColorScheme = darkColorScheme(
    primary = accent,
    onPrimary = Color.White,
    surface = surface,
    background = bg,
    onSurface = ink,
    onBackground = ink,
    onSurfaceVariant = muted,
    secondaryContainer = accentSoft,
    onSecondaryContainer = accentDark,
    surfaceContainerHigh = surface,
    surfaceContainerHighest = border,
    outline = border,
    outlineVariant = border
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
fun ThemeMode.isDark(): Boolean = when (this) {
    ThemeMode.SYSTEM -> isSystemInDarkTheme()
    ThemeMode.LIGHT -> false
    ThemeMode.DARK -> true
}

@Composable
fun TaskerTheme(
    darkTheme: Boolean = false,
    accent: AccentColor = AccentColor.TEAL,
    content: @Composable () -> Unit
) {
    val colors = remember(darkTheme, accent) {
        (if (darkTheme) DarkColors else LightColors).withAccent(accent, darkTheme)
    }

    CompositionLocalProvider(LocalAppColors provides colors) {
        MaterialTheme(
            colorScheme = if (darkTheme) colors.toDarkScheme() else colors.toLightScheme(),
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}
