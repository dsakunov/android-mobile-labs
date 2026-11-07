package com.example.tasker.ui.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.tasker.ui.theme.Bg
import com.example.tasker.ui.theme.Border
import com.example.tasker.ui.theme.Ink
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.Placeholder
import com.example.tasker.ui.theme.Teal

@Composable
fun fieldColors(containerColor: Color = Bg) = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = containerColor,
    unfocusedContainerColor = containerColor,
    focusedBorderColor = Teal,
    unfocusedBorderColor = Border,
    focusedTextColor = Ink,
    unfocusedTextColor = Ink,
    cursorColor = Ink,
    focusedPlaceholderColor = Placeholder,
    unfocusedPlaceholderColor = Placeholder,
    focusedLeadingIconColor = Muted,
    unfocusedLeadingIconColor = Muted
)
