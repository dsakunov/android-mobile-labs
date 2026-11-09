package com.example.tasker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.R
import com.example.tasker.domain.AppLanguage
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun LanguageDialog(
    selected: AppLanguage,
    onSelect: (AppLanguage) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = TaskerTheme.colors

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.surface,
        title = { Text(stringResource(R.string.language), color = colors.ink) },
        text = {
            Column {
                AppLanguage.entries.forEach { language ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(language) }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = language == selected,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colors.teal,
                                unselectedColor = colors.checkOff
                            )
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(stringResource(language.nameRes), color = colors.ink)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel), color = colors.muted)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LanguageDialogPreview() {
    PreviewWrapper {
        LanguageDialog(selected = AppLanguage.RU, onSelect = {}, onDismiss = {})
    }
}
