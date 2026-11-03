package com.example.tasker.data.repository

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tasker.domain.AppSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.settingsDataStore by preferencesDataStore(name = "settings")

object SettingsRepository {
    private val LANGUAGE = stringPreferencesKey("language")
    private val DARK_THEME = booleanPreferencesKey("dark_theme")
    private val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
    private val SHOW_PRIORITIES = booleanPreferencesKey("show_priorities")

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun flow(): Flow<AppSettings> = appContext.settingsDataStore.data.map { prefs ->
        AppSettings(
            language = prefs[LANGUAGE] ?: "ru",
            darkTheme = prefs[DARK_THEME] ?: false,
            hideCompleted = prefs[HIDE_COMPLETED] ?: false,
            showPriorities = prefs[SHOW_PRIORITIES] ?: true
        )
    }

    fun setDarkTheme(value: Boolean) = save { it[DARK_THEME] = value }

    fun setHideCompleted(value: Boolean) = save { it[HIDE_COMPLETED] = value }

    fun setShowPriorities(value: Boolean) = save { it[SHOW_PRIORITIES] = value }

    fun setLanguage(value: String) = save { it[LANGUAGE] = value }

    private fun save(block: (MutablePreferences) -> Unit) {
        scope.launch { appContext.settingsDataStore.edit { block(it) } }
    }
}
