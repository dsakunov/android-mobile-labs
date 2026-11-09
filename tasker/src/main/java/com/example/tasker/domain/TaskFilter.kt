package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R
import java.time.LocalDate

enum class TaskFilter(@StringRes val labelRes: Int) {
    ALL(R.string.filter_all),
    TODAY(R.string.filter_today),
    DONE(R.string.filter_done)
}

fun Task.matches(filter: TaskFilter, today: LocalDate, hideCompleted: Boolean = false): Boolean {
    if (hideCompleted && done && filter != TaskFilter.DONE) return false
    return when (filter) {
        TaskFilter.ALL -> true
        TaskFilter.TODAY -> date == today
        TaskFilter.DONE -> done
    }
}

fun Task.matchesQuery(query: String): Boolean {
    val text = query.trim()
    return text.isEmpty() ||
        title.contains(text, ignoreCase = true) ||
        note.contains(text, ignoreCase = true)
}
