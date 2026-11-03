package com.example.tasker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import java.time.LocalDate

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val note: String,
    val dateEpochDay: Long,
    val priority: String,
    val done: Boolean
)

fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    note = note,
    dateEpochDay = date.toEpochDay(),
    priority = priority.name,
    done = done
)

fun TaskEntity.toTask() = Task(
    id = id,
    title = title,
    note = note,
    date = LocalDate.ofEpochDay(dateEpochDay),
    priority = runCatching { Priority.valueOf(priority) }.getOrDefault(Priority.MEDIUM),
    done = done
)
