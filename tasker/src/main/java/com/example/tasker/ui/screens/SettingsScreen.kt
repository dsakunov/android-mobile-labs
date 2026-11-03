package com.example.tasker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.R
import com.example.tasker.data.repository.SettingsRepository
import com.example.tasker.data.repository.TaskRepository
import com.example.tasker.data.utils.rememberToday
import com.example.tasker.domain.AppSettings
import com.example.tasker.ui.components.AppSwitch
import com.example.tasker.ui.components.BottomNavBar
import com.example.tasker.ui.components.PreviewWrapper
import com.example.tasker.ui.components.ScreenHeader
import com.example.tasker.ui.components.SectionLabel
import com.example.tasker.ui.components.SettingRow
import com.example.tasker.ui.components.SettingsCard
import com.example.tasker.ui.theme.Bg
import com.example.tasker.ui.theme.Border
import com.example.tasker.ui.theme.Muted

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val settings by remember { SettingsRepository.flow() }.collectAsState(initial = AppSettings())
    val doneCount = TaskRepository.tasks.count { it.done }

    Scaffold(
        modifier = modifier,
        containerColor = Bg,
        bottomBar = { BottomNavBar(selected = 2) }
    ) { innerPadding ->
        SettingsContent(
            settings = settings,
            doneCount = doneCount,
            onDarkThemeChange = SettingsRepository::setDarkTheme,
            onHideCompletedChange = SettingsRepository::setHideCompleted,
            onShowPrioritiesChange = SettingsRepository::setShowPriorities,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun SettingsContent(
    settings: AppSettings,
    doneCount: Int,
    onDarkThemeChange: (Boolean) -> Unit,
    onHideCompletedChange: (Boolean) -> Unit,
    onShowPrioritiesChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
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
                onClick = { }
            ) {
                Text(stringResource(R.string.language_current), color = Muted, fontSize = 13.sp)
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Outlined.ChevronRight, null, tint = Muted, modifier = Modifier.size(20.dp))
            }
            HorizontalDivider(color = Border)
            SettingRow(
                icon = Icons.Outlined.DarkMode,
                title = stringResource(R.string.dark_theme),
                subtitle = stringResource(R.string.dark_theme_sub),
                onClick = { onDarkThemeChange(!settings.darkTheme) }
            ) { AppSwitch(settings.darkTheme) }
            HorizontalDivider(color = Border)
            SettingRow(
                icon = Icons.Outlined.VisibilityOff,
                title = stringResource(R.string.hide_done),
                subtitle = stringResource(R.string.hide_done_sub),
                onClick = { onHideCompletedChange(!settings.hideCompleted) }
            ) { AppSwitch(settings.hideCompleted) }
            HorizontalDivider(color = Border)
            SettingRow(
                icon = Icons.Outlined.LocalOffer,
                title = stringResource(R.string.show_priorities),
                subtitle = stringResource(R.string.show_priorities_sub),
                onClick = { onShowPrioritiesChange(!settings.showPriorities) }
            ) { AppSwitch(settings.showPriorities) }
        }

        Spacer(Modifier.height(24.dp))
        SectionLabel(stringResource(R.string.section_data))
        SettingsCard {
            SettingRow(
                icon = Icons.Outlined.DeleteOutline,
                title = stringResource(R.string.remove_done),
                subtitle = stringResource(R.string.remove_done_sub, doneCount),
                onClick = { }
            ) {
                Icon(Icons.Outlined.ChevronRight, null, tint = Muted, modifier = Modifier.size(20.dp))
            }
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
            onDarkThemeChange = {},
            onHideCompletedChange = {},
            onShowPrioritiesChange = {}
        )
    }
}
