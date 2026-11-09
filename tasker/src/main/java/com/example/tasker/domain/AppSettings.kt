package com.example.tasker.domain

data class AppSettings(
    val themeMode: ThemeMode = ThemeMode.LIGHT,
    val accent: AccentColor = AccentColor.TEAL,
    val hideCompleted: Boolean = false,
    val showPriorities: Boolean = true
)
