package com.example.tasker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import com.example.tasker.data.repository.SettingsRepository
import com.example.tasker.data.repository.TaskRepository
import com.example.tasker.data.utils.setupEdgeToEdge
import com.example.tasker.ui.screens.SettingsScreen
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.theme.isDark

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TaskRepository.init(applicationContext)
        setContent {
            val settings = SettingsRepository.settings
            val darkTheme = settings.themeMode.isDark()
            DisposableEffect(darkTheme) {
                setupEdgeToEdge(darkTheme)
                onDispose {}
            }
            TaskerTheme(darkTheme = darkTheme, accent = settings.accent) {
                SettingsScreen()
            }
        }
    }
}
