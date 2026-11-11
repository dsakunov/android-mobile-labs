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
import com.example.tasker.domain.AccentColor
import com.example.tasker.domain.AppSettings
import com.example.tasker.domain.DateStyle
import com.example.tasker.domain.Priority
import com.example.tasker.domain.TaskSort
import com.example.tasker.domain.ThemeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val Context.settingsDataStore by preferencesDataStore(name = "settings")

object SettingsRepository {
    private val THEME_MODE = stringPreferencesKey("theme_mode")
    private val ACCENT = stringPreferencesKey("accent")
    private val SORT = stringPreferencesKey("sort")
    private val DEFAULT_PRIORITY = stringPreferencesKey("default_priority")
    private val DATE_STYLE = stringPreferencesKey("date_style")
    private val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
    private val SHOW_PRIORITIES = booleanPreferencesKey("show_priorities")
    private val HIGHLIGHT_OVERDUE = booleanPreferencesKey("highlight_overdue")
    private val COMPACT_CARDS = booleanPreferencesKey("compact_cards")
    private val ADAPTIVE_CARDS = booleanPreferencesKey("adaptive_cards")

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
        themeMode = enumOf(THEME_MODE, ThemeMode.LIGHT),
        accent = enumOf(ACCENT, AccentColor.TEAL),
        sort = enumOf(SORT, TaskSort.DATE_ASC),
        defaultPriority = enumOf(DEFAULT_PRIORITY, Priority.MEDIUM),
        dateStyle = enumOf(DATE_STYLE, DateStyle.SHORT),
        hideCompleted = this[HIDE_COMPLETED] ?: false,
        showPriorities = this[SHOW_PRIORITIES] ?: true,
        highlightOverdue = this[HIGHLIGHT_OVERDUE] ?: true,
        compactCards = this[COMPACT_CARDS] ?: false,
        adaptiveCards = this[ADAPTIVE_CARDS] ?: false
    )

    fun setThemeMode(value: ThemeMode) = save { it[THEME_MODE] = value.name }

    fun setAccent(value: AccentColor) = save { it[ACCENT] = value.name }

    fun setSort(value: TaskSort) = save { it[SORT] = value.name }

    fun setDefaultPriority(value: Priority) = save { it[DEFAULT_PRIORITY] = value.name }

    fun setDateStyle(value: DateStyle) = save { it[DATE_STYLE] = value.name }

    fun setHideCompleted(value: Boolean) = save { it[HIDE_COMPLETED] = value }

    fun setShowPriorities(value: Boolean) = save { it[SHOW_PRIORITIES] = value }

    fun setHighlightOverdue(value: Boolean) = save { it[HIGHLIGHT_OVERDUE] = value }

    fun setCompactCards(value: Boolean) = save { it[COMPACT_CARDS] = value }

    fun setAdaptiveCards(value: Boolean) = save { it[ADAPTIVE_CARDS] = value }

    private inline fun <reified T : Enum<T>> Preferences.enumOf(key: Preferences.Key<String>, default: T): T =
        enumValues<T>().firstOrNull { it.name == this[key] } ?: default

    private fun save(block: (MutablePreferences) -> Unit) {
        scope.launch { appContext.settingsDataStore.edit { block(it) } }
    }
}
