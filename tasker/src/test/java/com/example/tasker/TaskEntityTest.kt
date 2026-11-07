package com.example.tasker

import com.example.tasker.data.database.toEntity
import com.example.tasker.data.database.toTask
import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class TaskEntityTest {
    private val task = Task(
        id = 7,
        title = "Подготовить отчёт",
        note = "Прикрепить в гуглкласс",
        date = LocalDate.of(2026, 10, 23),
        priority = Priority.HIGH,
        done = true
    )

    @Test
    fun taskSurvivesRoundTripThroughEntity() {
        assertEquals(task, task.toEntity().toTask())
    }

    @Test
    fun entityStoresDateAsEpochDay() {
        assertEquals(task.date.toEpochDay(), task.toEntity().dateEpochDay)
    }

    @Test
    fun entityStoresPriorityByName() {
        assertEquals("HIGH", task.toEntity().priority)
    }

    @Test
    fun unknownPriorityFallsBackToMedium() {
        val entity = task.toEntity().copy(priority = "UNKNOWN")
        assertEquals(Priority.MEDIUM, entity.toTask().priority)
    }
}
