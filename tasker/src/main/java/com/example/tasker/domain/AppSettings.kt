package com.example.tasker.domain

data class AppSettings(
    val themeMode: ThemeMode = ThemeMode.LIGHT,
    val accent: AccentColor = AccentColor.TEAL,
    val sort: TaskSort = TaskSort.DATE_ASC,
    val defaultPriority: Priority = Priority.MEDIUM,
    val dateStyle: DateStyle = DateStyle.SHORT,
    val hideCompleted: Boolean = false,
    val showPriorities: Boolean = true
)
