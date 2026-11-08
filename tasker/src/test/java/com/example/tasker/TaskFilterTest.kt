package com.example.tasker

import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import com.example.tasker.domain.TaskFilter
import com.example.tasker.domain.matches
import com.example.tasker.domain.matchesQuery
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class TaskFilterTest {
    private val today = LocalDate.of(2026, 10, 23)
    private val todayTask = Task(
        1, "Подготовить отчёт", "Прикрепить в гуглкласс", today, Priority.MEDIUM
    )
    private val doneTask = Task(2, "Изучить Kotlin", "", today.minusDays(3), Priority.LOW, true)
    private val tasks = listOf(todayTask, doneTask)

    @Test
    fun allFilterMatchesAnyTask() {
        assertEquals(2, tasks.count { it.matches(TaskFilter.ALL, today) })
    }

    @Test
    fun todayFilterMatchesOnlyTasksOfToday() {
        assertEquals(listOf(todayTask), tasks.filter { it.matches(TaskFilter.TODAY, today) })
    }

    @Test
    fun doneFilterMatchesOnlyCompletedTasks() {
        assertEquals(listOf(doneTask), tasks.filter { it.matches(TaskFilter.DONE, today) })
    }

    @Test
    fun queryIgnoresCase() {
        assertTrue(doneTask.matchesQuery("KOTLIN"))
    }

    @Test
    fun queryLooksInNote() {
        assertTrue(todayTask.matchesQuery("гуглкласс"))
    }

    @Test
    fun blankQueryMatchesEveryTask() {
        assertTrue(todayTask.matchesQuery("   "))
    }

    @Test
    fun unknownQueryMatchesNothing() {
        assertFalse(todayTask.matchesQuery("математика"))
    }
}
