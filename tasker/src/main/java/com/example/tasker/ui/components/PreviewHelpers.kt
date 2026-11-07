package com.example.tasker.ui.components

import androidx.compose.runtime.Composable
import com.example.tasker.data.MockData
import com.example.tasker.domain.Task
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    TaskerTheme {
        content()
    }
}

val sampleTasks: List<Task> = MockData.initialTasks()
val sampleTask: Task = sampleTasks[1]
