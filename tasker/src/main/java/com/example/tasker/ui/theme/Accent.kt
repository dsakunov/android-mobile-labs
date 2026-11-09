package com.example.tasker.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.tasker.domain.AccentColor

fun AccentColor.swatch(): Color = when (this) {
    AccentColor.TEAL -> Color(0xFF128C7A)
    AccentColor.BLUE -> Color(0xFF2F6FDE)
    AccentColor.ORANGE -> Color(0xFFE8741A)
}

fun AppColors.withAccent(accent: AccentColor, darkTheme: Boolean): AppColors = when (accent) {
    AccentColor.TEAL -> this
    AccentColor.BLUE -> if (darkTheme) {
        copy(
            accent = Color(0xFF3B7BE8),
            accentDark = Color(0xFF8DB4F5),
            accentSoft = Color(0xFF1D2F4E),
            accentDisabled = Color(0xFF2A3F63),
            accentCard = Color(0xFF1B3F85),
            accentRing = Color(0xFFB7D3FF),
            checkOn = Color(0xFF5C8FE8)
        )
    } else {
        copy(
            accent = Color(0xFF2F6FDE),
            accentDark = Color(0xFF1F4FA8),
            accentSoft = Color(0xFFD9E6FA),
            accentDisabled = Color(0xFFA9C4F0),
            accentCard = Color(0xFF1E4FA3),
            accentRing = Color(0xFFB7D3FF),
            checkOn = Color(0xFF6C97E0)
        )
    }
    AccentColor.ORANGE -> if (darkTheme) {
        copy(
            accent = Color(0xFFEF7F2A),
            accentDark = Color(0xFFFFB27A),
            accentSoft = Color(0xFF4A2E1A),
            accentDisabled = Color(0xFF5A3A24),
            accentCard = Color(0xFF9A4A10),
            accentRing = Color(0xFFFFD9B8),
            checkOn = Color(0xFFEF8F47)
        )
    } else {
        copy(
            accent = Color(0xFFE8741A),
            accentDark = Color(0xFFB24F08),
            accentSoft = Color(0xFFFCE4D0),
            accentDisabled = Color(0xFFF4C4A0),
            accentCard = Color(0xFFC2570C),
            accentRing = Color(0xFFFFD9B8),
            checkOn = Color(0xFFEE9A5C)
        )
    }
}
