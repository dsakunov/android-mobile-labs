package com.example.tasker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.R
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun WeekNavigator(
    range: String,
    showToday: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onToday: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TaskerTheme.colors
    Row(
        modifier.fillMaxWidth().padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrevious) {
            Icon(Icons.Outlined.ChevronLeft, stringResource(R.string.prev_week), tint = colors.ink)
        }
        Text(
            range,
            modifier = Modifier.weight(1f),
            color = colors.ink,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        if (showToday) {
            TextButton(onClick = onToday) {
                Text(stringResource(R.string.filter_today), color = colors.accentDark)
            }
        }
        IconButton(onClick = onNext) {
            Icon(Icons.Outlined.ChevronRight, stringResource(R.string.next_week), tint = colors.ink)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeekNavigatorPreview() {
    PreviewWrapper {
        WeekNavigator(
            range = "2 ноя – 8 ноя",
            showToday = true,
            onPrevious = {},
            onNext = {},
            onToday = {}
        )
    }
}
