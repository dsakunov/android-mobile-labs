package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R

enum class TaskSort(@StringRes val labelRes: Int) {
    DATE_ASC(R.string.sort_date_asc),
    DATE_DESC(R.string.sort_date_desc),
    PRIORITY_ASC(R.string.sort_priority_asc),
    PRIORITY_DESC(R.string.sort_priority_desc)
}

fun TaskSort.comparator(): Comparator<Task> = when (this) {
    TaskSort.DATE_ASC -> compareBy<Task> { it.date }.thenBy { it.id }
    TaskSort.DATE_DESC -> compareByDescending<Task> { it.date }.thenBy { it.id }
    TaskSort.PRIORITY_ASC -> compareBy<Task> { it.priority }.thenBy { it.date }
    TaskSort.PRIORITY_DESC -> compareByDescending<Task> { it.priority }.thenBy { it.date }
}
