package com.example.tasker.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.R
import com.example.tasker.data.repository.TaskRepository
import com.example.tasker.data.utils.rememberToday
import com.example.tasker.domain.Task
import com.example.tasker.ui.components.BottomNavBar
import com.example.tasker.ui.components.PreviewWrapper
import com.example.tasker.ui.components.ScreenHeader
import com.example.tasker.ui.components.TaskCard
import com.example.tasker.ui.components.TaskSheet
import com.example.tasker.ui.components.fieldColors
import com.example.tasker.ui.components.sampleTasks
import com.example.tasker.ui.theme.Bg
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.Teal
import com.example.tasker.ui.theme.TealDark
import com.example.tasker.ui.theme.TealSoft

private enum class Filter(@StringRes val labelRes: Int) {
    ALL(R.string.filter_all), TODAY(R.string.filter_today), DONE(R.string.filter_done)
}

@Composable
fun TasksScreen(modifier: Modifier = Modifier) {
    var editingId by rememberSaveable { mutableStateOf<Long?>(null) }
    var creating by rememberSaveable { mutableStateOf(false) }

    val editing = TaskRepository.tasks.firstOrNull { it.id == editingId }
    val sheetOpen = creating || editing != null

    Scaffold(
        modifier = modifier,
        containerColor = Bg,
        bottomBar = { BottomNavBar(selected = 0) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { creating = true },
                containerColor = Teal,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.add_task))
            }
        }
    ) { innerPadding ->
        TasksContent(
            tasks = TaskRepository.tasks,
            loaded = TaskRepository.loaded,
            onToggle = TaskRepository::toggle,
            onEdit = { editingId = it.id },
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (sheetOpen) {
        TaskSheet(
            initial = editing,
            onDismiss = { creating = false; editingId = null },
            onSave = {
                TaskRepository.upsert(it)
                creating = false; editingId = null
            },
            onDelete = {
                editing?.let { task -> TaskRepository.delete(task.id) }
                creating = false; editingId = null
            }
        )
    }
}

@Composable
private fun TasksContent(
    tasks: List<Task>,
    loaded: Boolean,
    onToggle: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = rememberToday()
    var filter by rememberSaveable { mutableStateOf(Filter.ALL) }
    var query by rememberSaveable { mutableStateOf("") }

    fun matches(t: Task, f: Filter) = when (f) {
        Filter.ALL -> true
        Filter.TODAY -> t.date == today
        Filter.DONE -> t.done
    }

    val visible = tasks.filter {
        matches(it, filter) && (query.isBlank() ||
                it.title.contains(query.trim(), ignoreCase = true) ||
                it.note.contains(query.trim(), ignoreCase = true))
    }

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            label = stringResource(R.string.panel_label),
            title = stringResource(R.string.my_tasks),
            today = today
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.padding(horizontal = 22.dp).fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.search_hint)) },
            leadingIcon = { Icon(Icons.Outlined.Search, null) },
            singleLine = true,
            shape = RoundedCornerShape(18.dp),
            colors = fieldColors(containerColor = Color.White)
        )

        Spacer(Modifier.height(18.dp))

        Row(
            Modifier.padding(horizontal = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Filter.entries.forEach { f ->
                FilterChip(
                    selected = filter == f,
                    onClick = { filter = f },
                    label = { Text("${stringResource(f.labelRes)}  ${tasks.count { matches(it, f) }}") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TealSoft,
                        selectedLabelColor = TealDark
                    )
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        if (visible.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(start = 22.dp, end = 22.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(visible, key = { it.id }) { task ->
                    TaskCard(task, onToggle = { onToggle(task) }, onClick = { onEdit(task) })
                }
            }
        } else if (loaded) {
            Box(Modifier.fillMaxSize().padding(top = 40.dp), contentAlignment = Alignment.TopCenter) {
                Text(stringResource(R.string.no_tasks), color = Muted, fontSize = 15.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TasksContentPreview() {
    PreviewWrapper {
        TasksContent(
            tasks = sampleTasks,
            loaded = true,
            onToggle = {},
            onEdit = {}
        )
    }
}
