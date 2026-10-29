package com.example.tasker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tasker.data.repository.TaskRepository
import com.example.tasker.data.utils.setupEdgeToEdge
import com.example.tasker.ui.screens.TasksScreen
import com.example.tasker.ui.theme.TaskerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEdgeToEdge()
        TaskRepository.init(applicationContext)
        setContent {
            TaskerTheme {
                TasksScreen()
            }
        }
    }
}
