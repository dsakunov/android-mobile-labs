package com.example.tasker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.data.utils.shortText
import com.example.tasker.domain.Task
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.theme.textColor

@Composable
fun TaskCard(
    task: Task,
    onToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showPriority: Boolean = true
) {
    val colors = TaskerTheme.colors
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (task.done) colors.surfaceDone else colors.surface
        ),
        border = BorderStroke(1.dp, colors.border)
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { onToggle() },
                        colors = CheckboxDefaults.colors(
                            checkedColor = colors.checkOn,
                            uncheckedColor = colors.checkOff
                        )
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    task.title,
                    modifier = Modifier.weight(1f),
                    color = if (task.done) colors.muted else colors.ink,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (task.done) TextDecoration.LineThrough else null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(Icons.Outlined.ChevronRight, null, tint = colors.muted)
            }
            val hasNote = task.note.isNotBlank()
            Column(Modifier.padding(start = 36.dp, top = 2.dp)) {
                if (hasNote) NoteLine(task.note)
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = colors.muted)) {
                            append(task.date.shortText())
                            if (showPriority) append("  ·  ")
                        }
                        if (showPriority) {
                            withStyle(SpanStyle(color = task.priority.textColor(), fontWeight = FontWeight.Bold)) {
                                append(stringResource(task.priority.labelRes))
                            }
                        }
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
                if (!hasNote) NoteLine("")
            }
        }
    }
}

@Composable
private fun NoteLine(text: String) {
    Text(
        text,
        color = TaskerTheme.colors.muted,
        fontSize = 13.sp,
        minLines = 1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
private fun TaskCardPreview() {
    PreviewWrapper {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TaskCard(task = sampleTask, onToggle = {}, onClick = {})
            TaskCard(task = sampleTask.copy(done = true), onToggle = {}, onClick = {})
            TaskCard(task = sampleTask, onToggle = {}, onClick = {}, showPriority = false)
        }
    }
}
