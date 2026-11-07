package com.example.tasker.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.tasker.data.utils.fullDayText
import com.example.tasker.data.utils.monthYearCaps
import com.example.tasker.data.utils.rememberToday
import com.example.tasker.domain.Task
import com.example.tasker.ui.components.BottomNavBar
import com.example.tasker.ui.components.DayPill
import com.example.tasker.ui.components.PreviewWrapper
import com.example.tasker.ui.components.ProgressRing
import com.example.tasker.ui.components.ScreenHeader
import com.example.tasker.ui.components.TaskCard
import com.example.tasker.ui.components.TaskSheet
import com.example.tasker.ui.components.sampleTasks
import com.example.tasker.ui.theme.Bg
import com.example.tasker.ui.theme.CardGreen
import com.example.tasker.ui.theme.Ink
import com.example.tasker.ui.theme.Muted
import java.time.LocalDate

@Composable
fun PlanScreen(modifier: Modifier = Modifier) {
    var editingId by rememberSaveable { mutableStateOf<Long?>(null) }
    val editing = TaskRepository.tasks.firstOrNull { it.id == editingId }

    Scaffold(
        modifier = modifier,
        containerColor = Bg,
        bottomBar = { BottomNavBar(selected = 1) }
    ) { innerPadding ->
        PlanContent(
            tasks = TaskRepository.tasks,
            loaded = TaskRepository.loaded,
            onToggle = TaskRepository::toggle,
            onEdit = { editingId = it.id },
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (editing != null) {
        TaskSheet(
            initial = editing,
            onDismiss = { editingId = null },
            onSave = { TaskRepository.upsert(it); editingId = null },
            onDelete = { TaskRepository.delete(editing.id); editingId = null }
        )
    }
}

@Composable
private fun PlanContent(
    tasks: List<Task>,
    loaded: Boolean,
    onToggle: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = rememberToday()
    val week = remember(today) { (0L..6L).map { today.plusDays(it) } }
    var selectedEpoch by rememberSaveable { mutableStateOf(today.toEpochDay()) }
    val selected = LocalDate.ofEpochDay(selectedEpoch)

    val dayTasks = tasks.filter { it.date == selected }
    val doneCount = dayTasks.count { it.done }
    val percent = if (dayTasks.isEmpty()) 0 else doneCount * 100 / dayTasks.size

    Column(modifier.fillMaxSize()) {
        ScreenHeader(
            label = selected.monthYearCaps(),
            title = stringResource(R.string.planner_title),
            today = today
        )

        Spacer(Modifier.height(22.dp))

        Row(Modifier.fillMaxWidth().padding(horizontal = 14.dp)) {
            week.forEach { day ->
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    DayPill(
                        day = day,
                        selected = day == selected,
                        hasTasks = tasks.any { it.date == day },
                        onClick = { selectedEpoch = day.toEpochDay() }
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Card(
            Modifier.padding(horizontal = 22.dp).fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardGreen)
        ) {
            Row(
                Modifier.padding(horizontal = 22.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        selected.fullDayText(),
                        color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(stringResource(R.string.tasks_count, dayTasks.size), color = Color.White, fontSize = 24.sp)
                }
                ProgressRing(percent = percent)
            }
        }

        Spacer(Modifier.height(26.dp))

        Row(
            Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.scheduled), color = Ink, fontSize = 18.sp, modifier = Modifier.weight(1f))
            Text(stringResource(R.string.done_count, doneCount, dayTasks.size), color = Muted, fontSize = 12.sp)
        }

        Spacer(Modifier.height(14.dp))

        if (dayTasks.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(start = 22.dp, end = 22.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dayTasks, key = { it.id }) { task ->
                    TaskCard(task, onToggle = { onToggle(task) }, onClick = { onEdit(task) })
                }
            }
        } else if (loaded) {
            Box(Modifier.fillMaxSize().padding(top = 30.dp), contentAlignment = Alignment.TopCenter) {
                Text(stringResource(R.string.no_tasks_day), color = Muted, fontSize = 15.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanContentPreview() {
    PreviewWrapper {
        PlanContent(
            tasks = sampleTasks,
            loaded = true,
            onToggle = {},
            onEdit = {}
        )
    }
}
