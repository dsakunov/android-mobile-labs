package com.example.tasker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.graphics.Color
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
import com.example.tasker.ui.theme.Border
import com.example.tasker.ui.theme.CheckOff
import com.example.tasker.ui.theme.CheckOn
import com.example.tasker.ui.theme.Ink
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.textColor

@Composable
fun TaskCard(
    task: Task,
    onToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (task.done) Color(0xFFFAFCFB) else Color.White
        ),
        border = BorderStroke(1.dp, Border)
    ) {
        Column(
            Modifier
                .defaultMinSize(minHeight = 92.dp)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { onToggle() },
                        colors = CheckboxDefaults.colors(checkedColor = CheckOn, uncheckedColor = CheckOff)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    task.title,
                    modifier = Modifier.weight(1f),
                    color = if (task.done) Muted else Ink,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (task.done) TextDecoration.LineThrough else null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(Icons.Outlined.ChevronRight, null, tint = Muted)
            }
            Column(Modifier.padding(start = 36.dp, top = 2.dp)) {
                if (task.note.isNotBlank()) {
                    Text(
                        task.note,
                        color = Muted,
                        fontSize = 13.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Muted)) { append("${task.date.shortText()}  ·  ") }
                        withStyle(SpanStyle(color = task.priority.textColor(), fontWeight = FontWeight.Bold)) {
                            append(stringResource(task.priority.labelRes))
                        }
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCardPreview() {
    PreviewWrapper {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TaskCard(task = sampleTask, onToggle = {}, onClick = {})
            TaskCard(task = sampleTask.copy(done = true), onToggle = {}, onClick = {})
        }
    }
}
