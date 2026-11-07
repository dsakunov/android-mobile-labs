package com.example.tasker.data

import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import java.time.LocalDate

object MockData {

    fun initialTasks(): List<Task> {
        val today = LocalDate.now()
        return listOf(
            Task(1, "Изучить основы Kotlin", "", today.minusDays(20), Priority.LOW, true),
            Task(2, "Подготовить отчёт по лабе", "Прикрепить в гуглкласс", today, Priority.MEDIUM),
            Task(3, "Посвящение в студенты", "", today.plusDays(9), Priority.HIGH)
        )
    }
}
