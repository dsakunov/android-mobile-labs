package com.example.tasker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tasker.data.utils.setupEdgeToEdge
import com.example.tasker.ui.theme.TaskerTheme

class PlanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEdgeToEdge()
        setContent {
            TaskerTheme {}
        }
    }
}
