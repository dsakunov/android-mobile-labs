package com.example.tasker.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.R
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun ConfirmDialog(
    title: String,
    text: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val colors = TaskerTheme.colors

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.surface,
        title = { Text(title, color = colors.ink) },
        text = { Text(text, color = colors.muted) },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(contentColor = colors.redText)
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel), color = colors.muted)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmDialogPreview() {
    PreviewWrapper {
        ConfirmDialog(
            title = "Удалить задачу?",
            text = "Задача будет удалена без возможности восстановления.",
            confirmText = "Удалить",
            onConfirm = {},
            onDismiss = {}
        )
    }
}
