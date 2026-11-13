package com.example.tasker.domain

import java.time.LocalDate

data class Task(
    val id: Long,
    val title: String,
    val note: String,
    val date: LocalDate,
    val priority: Priority,
    val done: Boolean = false
)

fun Task.isOverdue(today: LocalDate): Boolean = !done && date.isBefore(today)
