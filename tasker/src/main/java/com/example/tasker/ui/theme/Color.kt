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
    val teal: Color,
    val tealDark: Color,
    val tealSoft: Color,
    val tealDisabled: Color,
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
    val cardGreen: Color,
    val ringMint: Color
)

val LightColors = AppColors(
    bg = Color(0xFFF4F7F5),
    surface = Color.White,
    surfaceDone = Color(0xFFFAFCFB),
    ink = Color(0xFF16221F),
    muted = Color(0xFF6F7F7A),
    placeholder = Color(0xFF8E9B96),
    border = Color(0xFFE1E8E5),
    teal = Color(0xFF128C7A),
    tealDark = Color(0xFF0F6B5C),
    tealSoft = Color(0xFFD6EEE7),
    tealDisabled = Color(0xFF9ED0C6),
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
    cardGreen = Color(0xFF0A6B5B),
    ringMint = Color(0xFFA8F0DD)
)

val LocalAppColors = staticCompositionLocalOf { LightColors }
