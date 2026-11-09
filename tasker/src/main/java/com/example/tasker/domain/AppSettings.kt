package com.example.tasker.domain

data class AppSettings(
    val darkTheme: Boolean = false,
    val hideCompleted: Boolean = false,
    val showPriorities: Boolean = true
)
