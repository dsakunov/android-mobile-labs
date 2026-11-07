package com.example.tasker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.R
import com.example.tasker.data.utils.fieldText
import com.example.tasker.domain.Priority
import com.example.tasker.domain.Task
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.theme.selectedBg
import com.example.tasker.ui.theme.selectedText
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSheet(
    initial: Task?,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: () -> Unit
) {
    val colors = TaskerTheme.colors
    var title by remember { mutableStateOf(initial?.title ?: "") }
    var note by remember { mutableStateOf(initial?.note ?: "") }
    var date by remember { mutableStateOf(initial?.date ?: LocalDate.now()) }
    var priority by remember { mutableStateOf(initial?.priority ?: Priority.MEDIUM) }
    var showPicker by remember { mutableStateOf(false) }
    val canSave = title.isNotBlank()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = colors.surface
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp)
                .padding(bottom = 16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth().padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(if (initial == null) R.string.new_task else R.string.edit_task),
                    color = colors.ink,
                    fontSize = 22.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Outlined.Close, stringResource(R.string.close), tint = colors.muted)
                }
            }

            FieldLabel(stringResource(R.string.field_title))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.hint_title)) },
                singleLine = true,
                colors = fieldColors()
            )

            Spacer(Modifier.height(18.dp))
            FieldLabel(stringResource(R.string.field_note))
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.hint_note)) },
                minLines = 2,
                colors = fieldColors()
            )

            Spacer(Modifier.height(18.dp))
            FieldLabel(stringResource(R.string.field_date))
            OutlinedButton(
                onClick = { showPicker = true },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(1.dp, colors.border),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colors.ink)
            ) {
                Text(date.fieldText(), modifier = Modifier.weight(1f))
                Icon(Icons.Outlined.CalendarToday, stringResource(R.string.pick_date))
            }

            Spacer(Modifier.height(18.dp))
            FieldLabel(stringResource(R.string.field_priority))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Priority.entries.forEach { p ->
                    FilterChip(
                        selected = p == priority,
                        onClick = { priority = p },
                        label = {
                            Text(
                                stringResource(p.labelRes),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = p.selectedBg(),
                            selectedLabelColor = p.selectedText()
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = p == priority,
                            borderColor = colors.border,
                            selectedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (initial != null) {
                    TextButton(
                        onClick = onDelete,
                        colors = ButtonDefaults.textButtonColors(contentColor = colors.redText)
                    ) {
                        Icon(Icons.Outlined.DeleteOutline, null)
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.delete))
                    }
                }
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.cancel), color = colors.muted)
                }
                Spacer(Modifier.width(8.dp))
                Button(
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        onSave(
                            Task(
                                id = initial?.id ?: 0,
                                title = title.trim(),
                                note = note.trim(),
                                date = date,
                                priority = priority,
                                done = initial?.done ?: false
                            )
                        )
                    },
                    enabled = canSave,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.teal,
                        disabledContainerColor = colors.tealDisabled,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    }

    if (showPicker) {
        val state = rememberDatePickerState(
            initialSelectedDateMillis = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    state.selectedDateMillis?.let {
                        date = Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDate()
                    }
                    showPicker = false
                }) { Text(stringResource(R.string.ok)) }
            },
            dismissButton = { TextButton(onClick = { showPicker = false }) { Text(stringResource(R.string.cancel)) } }
        ) { DatePicker(state = state) }
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text, color = TaskerTheme.colors.muted, fontSize = 13.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
