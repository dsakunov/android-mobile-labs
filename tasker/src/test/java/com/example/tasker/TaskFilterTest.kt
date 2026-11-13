package com.example.tasker

import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import com.example.tasker.domain.TaskFilter
import com.example.tasker.domain.isOverdue
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
    fun hideCompletedRemovesDoneTasksFromAllFilter() {
        assertFalse(doneTask.matches(TaskFilter.ALL, today, hideCompleted = true))
    }

    @Test
    fun hideCompletedRemovesDoneTasksFromTodayFilter() {
        val doneToday = doneTask.copy(date = today)
        assertFalse(doneToday.matches(TaskFilter.TODAY, today, hideCompleted = true))
    }

    @Test
    fun hideCompletedKeepsDoneFilterFull() {
        assertTrue(doneTask.matches(TaskFilter.DONE, today, hideCompleted = true))
    }

    @Test
    fun hideCompletedKeepsActiveTasks() {
        assertTrue(todayTask.matches(TaskFilter.ALL, today, hideCompleted = true))
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

    @Test
    fun taskInThePastIsOverdueUntilDone() {
        val pastTask = todayTask.copy(date = today.minusDays(1))
        assertTrue(pastTask.isOverdue(today))
        assertFalse(pastTask.copy(done = true).isOverdue(today))
    }

    @Test
    fun taskOfTodayIsNotOverdue() {
        assertFalse(todayTask.isOverdue(today))
    }

    @Test
    fun overdueFilterMatchesOnlyUnfinishedPastTasks() {
        val overdueTask = todayTask.copy(id = 3, date = today.minusDays(4))
        val all = tasks + overdueTask
        assertEquals(listOf(overdueTask), all.filter { it.matches(TaskFilter.OVERDUE, today) })
    }
}
