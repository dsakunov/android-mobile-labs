package com.example.tasker.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val bg: Color,
    val surface: Color,
    val surfaceDone: Color,
    val ink: Color,
    val muted: Color,
    val placeholder: Color,
    val border: Color,
    val accent: Color,
    val accentDark: Color,
    val accentSoft: Color,
    val accentDisabled: Color,
    val checkOff: Color,
    val checkOn: Color,
    val switchOff: Color,
    val redText: Color,
    val redBg: Color,
    val amberText: Color,
    val amberMeta: Color,
    val amberBg: Color,
    val greenText: Color,
    val greenBg: Color,
    val accentCard: Color,
    val accentRing: Color
)

val LightColors = AppColors(
    bg = Color(0xFFF4F7F5),
    surface = Color.White,
    surfaceDone = Color(0xFFFAFCFB),
    ink = Color(0xFF16221F),
    muted = Color(0xFF6F7F7A),
    placeholder = Color(0xFF8E9B96),
    border = Color(0xFFE1E8E5),
    accent = Color(0xFF128C7A),
    accentDark = Color(0xFF0F6B5C),
    accentSoft = Color(0xFFD6EEE7),
    accentDisabled = Color(0xFF9ED0C6),
    checkOff = Color(0xFFA8B7B1),
    checkOn = Color(0xFF5FB0A2),
    switchOff = Color(0xFFBBC8C3),
    redText = Color(0xFFB3382C),
    redBg = Color(0xFFFADBD8),
    amberText = Color(0xFF8A5A12),
    amberMeta = Color(0xFFBF7A14),
    amberBg = Color(0xFFF8E8C8),
    greenText = Color(0xFF3F8A57),
    greenBg = Color(0xFFDDEFE3),
    accentCard = Color(0xFF0A6B5B),
    accentRing = Color(0xFFA8F0DD)
)

val DarkColors = AppColors(
    bg = Color(0xFF0F1513),
    surface = Color(0xFF18211E),
    surfaceDone = Color(0xFF141C19),
    ink = Color(0xFFE6EFEC),
    muted = Color(0xFF93A59F),
    placeholder = Color(0xFF70827C),
    border = Color(0xFF2A3834),
    accent = Color(0xFF1E9985),
    accentDark = Color(0xFF6FD3C1),
    accentSoft = Color(0xFF1E3B35),
    accentDisabled = Color(0xFF2B4A43),
    checkOff = Color(0xFF5B6E67),
    checkOn = Color(0xFF3FB6A0),
    switchOff = Color(0xFF3A4A45),
    redText = Color(0xFFFF8A7D),
    redBg = Color(0xFF4A2724),
    amberText = Color(0xFFF0B964),
    amberMeta = Color(0xFFE0A23F),
    amberBg = Color(0xFF47381A),
    greenText = Color(0xFF6FCB8B),
    greenBg = Color(0xFF20402C),
    accentCard = Color(0xFF0F5C4F),
    accentRing = Color(0xFFA8F0DD)
)

val LocalAppColors = staticCompositionLocalOf { LightColors }
