package com.example.tasker.data.repository

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tasker.domain.AppSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val Context.settingsDataStore by preferencesDataStore(name = "settings")

object SettingsRepository {
    private val LANGUAGE = stringPreferencesKey("language")
    private val DARK_THEME = booleanPreferencesKey("dark_theme")
    private val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
    private val SHOW_PRIORITIES = booleanPreferencesKey("show_priorities")

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var appContext: Context

    var settings by mutableStateOf(AppSettings())
        private set

    fun init(context: Context) {
        if (::appContext.isInitialized) return
        appContext = context.applicationContext
        settings = runBlocking { appContext.settingsDataStore.data.first().toSettings() }
        scope.launch {
            appContext.settingsDataStore.data.map { it.toSettings() }.collect { settings = it }
        }
    }

    private fun Preferences.toSettings() = AppSettings(
        language = this[LANGUAGE] ?: "ru",
        darkTheme = this[DARK_THEME] ?: false,
        hideCompleted = this[HIDE_COMPLETED] ?: false,
        showPriorities = this[SHOW_PRIORITIES] ?: true
    )

    fun setDarkTheme(value: Boolean) = save { it[DARK_THEME] = value }

    fun setHideCompleted(value: Boolean) = save { it[HIDE_COMPLETED] = value }

    fun setShowPriorities(value: Boolean) = save { it[SHOW_PRIORITIES] = value }

    fun setLanguage(value: String) = save { it[LANGUAGE] = value }

    private fun save(block: (MutablePreferences) -> Unit) {
        scope.launch { appContext.settingsDataStore.edit { block(it) } }
    }
}
