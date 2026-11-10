package com.example.tasker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.R
import com.example.tasker.data.repository.SettingsRepository
import com.example.tasker.data.repository.TaskRepository
import com.example.tasker.data.utils.rememberToday
import com.example.tasker.data.utils.setAppLanguage
import com.example.tasker.domain.AccentColor
import com.example.tasker.domain.AppLanguage
import com.example.tasker.domain.AppSettings
import com.example.tasker.domain.DateStyle
import com.example.tasker.domain.Priority
import com.example.tasker.domain.TaskSort
import com.example.tasker.domain.ThemeMode
import com.example.tasker.ui.components.AppSwitch
import com.example.tasker.ui.components.BottomNavBar
import com.example.tasker.ui.components.ChevronIcon
import com.example.tasker.ui.components.ChoiceDialog
import com.example.tasker.ui.components.ChoiceValue
import com.example.tasker.ui.components.ConfirmDialog
import com.example.tasker.ui.components.PreviewWrapper
import com.example.tasker.ui.components.ScreenHeader
import com.example.tasker.ui.components.SectionLabel
import com.example.tasker.ui.components.SettingRow
import com.example.tasker.ui.components.SettingsCard
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.theme.swatch

private enum class SettingsDialog { LANGUAGE, THEME, ACCENT, SORT, PRIORITY, DATE_STYLE, REMOVE_DONE }

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val settings = SettingsRepository.settings
    val doneCount = TaskRepository.tasks.count { it.done }
    val context = LocalContext.current
    var dialog by rememberSaveable { mutableStateOf<SettingsDialog?>(null) }

    Scaffold(
        modifier = modifier,
        containerColor = TaskerTheme.colors.bg,
        bottomBar = { BottomNavBar(selected = 2) }
    ) { innerPadding ->
        SettingsContent(
            settings = settings,
            doneCount = doneCount,
            onOpenDialog = { dialog = it },
            onHideCompletedChange = SettingsRepository::setHideCompleted,
            onShowPrioritiesChange = SettingsRepository::setShowPriorities,
            onHighlightOverdueChange = SettingsRepository::setHighlightOverdue,
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (dialog == SettingsDialog.LANGUAGE) {
        ChoiceDialog(
            title = stringResource(R.string.language),
            options = AppLanguage.entries,
            selected = AppLanguage.current(),
            label = { stringResource(it.nameRes) },
            onSelect = { language ->
                dialog = null
                context.setAppLanguage(language.tag)
            },
            onDismiss = { dialog = null }
        )
    }

    if (dialog == SettingsDialog.THEME) {
        ChoiceDialog(
            title = stringResource(R.string.theme),
            options = ThemeMode.entries,
            selected = settings.themeMode,
            label = { stringResource(it.labelRes) },
            onSelect = {
                dialog = null
                SettingsRepository.setThemeMode(it)
            },
            onDismiss = { dialog = null }
        )
    }

    if (dialog == SettingsDialog.ACCENT) {
        ChoiceDialog(
            title = stringResource(R.string.accent),
            options = AccentColor.entries,
            selected = settings.accent,
            label = { stringResource(it.labelRes) },
            onSelect = {
                dialog = null
                SettingsRepository.setAccent(it)
            },
            onDismiss = { dialog = null },
            leading = { Surface(Modifier.size(20.dp), shape = CircleShape, color = it.swatch()) {} }
        )
    }

    if (dialog == SettingsDialog.SORT) {
        ChoiceDialog(
            title = stringResource(R.string.sort),
            options = TaskSort.entries,
            selected = settings.sort,
            label = { stringResource(it.labelRes) },
            onSelect = {
                dialog = null
                SettingsRepository.setSort(it)
            },
            onDismiss = { dialog = null }
        )
    }

    if (dialog == SettingsDialog.PRIORITY) {
        ChoiceDialog(
            title = stringResource(R.string.default_priority),
            options = Priority.entries,
            selected = settings.defaultPriority,
            label = { stringResource(it.labelRes) },
            onSelect = {
                dialog = null
                SettingsRepository.setDefaultPriority(it)
            },
            onDismiss = { dialog = null }
        )
    }

    if (dialog == SettingsDialog.DATE_STYLE) {
        ChoiceDialog(
            title = stringResource(R.string.date_format),
            options = DateStyle.entries,
            selected = settings.dateStyle,
            label = { stringResource(it.labelRes) },
            onSelect = {
                dialog = null
                SettingsRepository.setDateStyle(it)
            },
            onDismiss = { dialog = null }
        )
    }

    if (dialog == SettingsDialog.REMOVE_DONE) {
        ConfirmDialog(
            title = stringResource(R.string.remove_done_title),
            text = stringResource(R.string.remove_done_text, doneCount),
            confirmText = stringResource(R.string.delete),
            onConfirm = {
                TaskRepository.deleteDone()
                dialog = null
            },
            onDismiss = { dialog = null }
        )
    }
}

@Composable
private fun SettingsContent(
    settings: AppSettings,
    doneCount: Int,
    onOpenDialog: (SettingsDialog) -> Unit,
    onHideCompletedChange: (Boolean) -> Unit,
    onShowPrioritiesChange: (Boolean) -> Unit,
    onHighlightOverdueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TaskerTheme.colors
    val today = rememberToday()

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ScreenHeader(
            label = stringResource(R.string.settings_label),
            title = stringResource(R.string.settings_title),
            today = today
        )

        Spacer(Modifier.height(26.dp))

        SectionLabel(stringResource(R.string.section_general))
        SettingsCard {
            SettingRow(
                icon = Icons.Outlined.Language,
                title = stringResource(R.string.language),
                subtitle = stringResource(R.string.language_sub),
                onClick = { onOpenDialog(SettingsDialog.LANGUAGE) }
            ) { ChoiceValue(stringResource(AppLanguage.current().nameRes)) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.DarkMode,
                title = stringResource(R.string.theme),
                subtitle = stringResource(R.string.theme_sub),
                onClick = { onOpenDialog(SettingsDialog.THEME) }
            ) { ChoiceValue(stringResource(settings.themeMode.labelRes)) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.Palette,
                title = stringResource(R.string.accent),
                subtitle = stringResource(R.string.accent_sub),
                onClick = { onOpenDialog(SettingsDialog.ACCENT) }
            ) { ChoiceValue(stringResource(settings.accent.labelRes)) }
        }

        Spacer(Modifier.height(24.dp))
        SectionLabel(stringResource(R.string.section_tasks))
        SettingsCard {
            SettingRow(
                icon = Icons.Outlined.SwapVert,
                title = stringResource(R.string.sort),
                subtitle = stringResource(settings.sort.labelRes),
                onClick = { onOpenDialog(SettingsDialog.SORT) }
            ) { ChevronIcon() }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.Flag,
                title = stringResource(R.string.default_priority),
                subtitle = stringResource(R.string.default_priority_sub),
                onClick = { onOpenDialog(SettingsDialog.PRIORITY) }
            ) { ChoiceValue(stringResource(settings.defaultPriority.labelRes)) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.CalendarMonth,
                title = stringResource(R.string.date_format),
                subtitle = stringResource(R.string.date_format_sub),
                onClick = { onOpenDialog(SettingsDialog.DATE_STYLE) }
            ) { ChoiceValue(stringResource(settings.dateStyle.labelRes)) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.VisibilityOff,
                title = stringResource(R.string.hide_done),
                subtitle = stringResource(R.string.hide_done_sub),
                onClick = { onHideCompletedChange(!settings.hideCompleted) }
            ) { AppSwitch(settings.hideCompleted) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.LocalOffer,
                title = stringResource(R.string.show_priorities),
                subtitle = stringResource(R.string.show_priorities_sub),
                onClick = { onShowPrioritiesChange(!settings.showPriorities) }
            ) { AppSwitch(settings.showPriorities) }
            HorizontalDivider(color = colors.border)
            SettingRow(
                icon = Icons.Outlined.EventBusy,
                title = stringResource(R.string.highlight_overdue),
                subtitle = stringResource(R.string.highlight_overdue_sub),
                onClick = { onHighlightOverdueChange(!settings.highlightOverdue) }
            ) { AppSwitch(settings.highlightOverdue) }
        }

        Spacer(Modifier.height(24.dp))
        SectionLabel(stringResource(R.string.section_data))
        SettingsCard {
            SettingRow(
                icon = Icons.Outlined.DeleteOutline,
                title = stringResource(R.string.remove_done),
                subtitle = stringResource(R.string.remove_done_sub, doneCount),
                onClick = if (doneCount > 0) ({ onOpenDialog(SettingsDialog.REMOVE_DONE) }) else null
            ) { ChevronIcon() }
        }

        Spacer(Modifier.height(24.dp))
        SectionLabel(stringResource(R.string.section_about))
        SettingsCard {
            SettingRow(
                icon = Icons.Outlined.Info,
                title = stringResource(R.string.app_name),
                subtitle = stringResource(R.string.version),
                onClick = null
            ) { }
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    PreviewWrapper {
        SettingsContent(
            settings = AppSettings(),
            doneCount = 1,
            onOpenDialog = {},
            onHideCompletedChange = {},
            onShowPrioritiesChange = {},
            onHighlightOverdueChange = {}
        )
    }
}
