package com.example.tasker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.R
import com.example.tasker.domain.Task

@Composable
fun DeleteTaskDialog(
    task: Task,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    ConfirmDialog(
        title = stringResource(R.string.delete_task_title),
        text = stringResource(R.string.delete_task_text, task.title),
        confirmText = stringResource(R.string.delete),
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}

@Preview(showBackground = true)
@Composable
private fun DeleteTaskDialogPreview() {
    PreviewWrapper {
        DeleteTaskDialog(task = sampleTask, onConfirm = {}, onDismiss = {})
    }
}
