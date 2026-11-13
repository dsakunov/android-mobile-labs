package com.example.tasker

import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import com.example.tasker.domain.TaskSort
import com.example.tasker.domain.comparator
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class TaskSortTest {
    private val today = LocalDate.of(2026, 10, 23)
    private val early = Task(1, "Раньше", "", today.minusDays(2), Priority.HIGH)
    private val middle = Task(2, "Сегодня", "", today, Priority.LOW)
    private val late = Task(3, "Позже", "", today.plusDays(5), Priority.MEDIUM)
    private val tasks = listOf(middle, late, early)

    @Test
    fun dateAscendingPutsNearestFirst() {
        assertEquals(listOf(early, middle, late), tasks.sortedWith(TaskSort.DATE_ASC.comparator()))
    }

    @Test
    fun dateDescendingPutsFarthestFirst() {
        assertEquals(listOf(late, middle, early), tasks.sortedWith(TaskSort.DATE_DESC.comparator()))
    }

    @Test
    fun priorityAscendingPutsLowFirst() {
        assertEquals(listOf(middle, late, early), tasks.sortedWith(TaskSort.PRIORITY_ASC.comparator()))
    }

    @Test
    fun priorityDescendingPutsHighFirst() {
        assertEquals(listOf(early, late, middle), tasks.sortedWith(TaskSort.PRIORITY_DESC.comparator()))
    }

    @Test
    fun equalPrioritiesAreOrderedByDate() {
        val lateLow = late.copy(priority = Priority.LOW)
        val sorted = listOf(lateLow, middle).sortedWith(TaskSort.PRIORITY_ASC.comparator())
        assertEquals(listOf(middle, lateLow), sorted)
    }
}
