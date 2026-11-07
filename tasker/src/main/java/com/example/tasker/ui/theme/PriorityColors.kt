package com.example.tasker.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.tasker.domain.Priority

fun Priority.textColor(): Color = when (this) {
    Priority.HIGH -> RedText
    Priority.MEDIUM -> AmberMeta
    Priority.LOW -> GreenText
}

fun Priority.selectedBg(): Color = when (this) {
    Priority.HIGH -> RedBg
    Priority.MEDIUM -> AmberBg
    Priority.LOW -> GreenBg
}

fun Priority.selectedText(): Color = when (this) {
    Priority.HIGH -> RedText
    Priority.MEDIUM -> AmberText
    Priority.LOW -> GreenText
}
