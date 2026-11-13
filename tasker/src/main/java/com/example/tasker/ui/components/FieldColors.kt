package com.example.tasker.ui.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun fieldColors(containerColor: Color = TaskerTheme.colors.bg) = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = containerColor,
    unfocusedContainerColor = containerColor,
    focusedBorderColor = TaskerTheme.colors.accent,
    unfocusedBorderColor = TaskerTheme.colors.border,
    focusedTextColor = TaskerTheme.colors.ink,
    unfocusedTextColor = TaskerTheme.colors.ink,
    cursorColor = TaskerTheme.colors.ink,
    focusedPlaceholderColor = TaskerTheme.colors.placeholder,
    unfocusedPlaceholderColor = TaskerTheme.colors.placeholder,
    focusedLeadingIconColor = TaskerTheme.colors.muted,
    unfocusedLeadingIconColor = TaskerTheme.colors.muted
)
