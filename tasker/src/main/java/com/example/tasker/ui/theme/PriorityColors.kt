package com.example.tasker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.tasker.domain.Priority

@Composable
fun Priority.textColor(): Color = when (this) {
    Priority.HIGH -> TaskerTheme.colors.redText
    Priority.MEDIUM -> TaskerTheme.colors.amberMeta
    Priority.LOW -> TaskerTheme.colors.greenText
}

@Composable
fun Priority.selectedBg(): Color = when (this) {
    Priority.HIGH -> TaskerTheme.colors.redBg
    Priority.MEDIUM -> TaskerTheme.colors.amberBg
    Priority.LOW -> TaskerTheme.colors.greenBg
}

@Composable
fun Priority.selectedText(): Color = when (this) {
    Priority.HIGH -> TaskerTheme.colors.redText
    Priority.MEDIUM -> TaskerTheme.colors.amberText
    Priority.LOW -> TaskerTheme.colors.greenText
}
