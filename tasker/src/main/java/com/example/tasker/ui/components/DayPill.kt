package com.example.tasker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.data.utils.weekdayShort
import com.example.tasker.ui.theme.Ink
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.Teal
import com.example.tasker.ui.theme.TealDark
import com.example.tasker.ui.theme.TealSoft
import java.time.LocalDate

@Composable
fun DayPill(
    day: LocalDate,
    selected: Boolean,
    hasTasks: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.width(48.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) TealSoft else Color.Transparent
        )
    ) {
        Column(
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                day.weekdayShort(),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) TealDark else Muted
            )
            Text(
                day.dayOfMonth.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Ink
            )
            Badge(
                modifier = Modifier.padding(top = 4.dp),
                containerColor = if (hasTasks) Teal else Color.Transparent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DayPillPreview() {
    PreviewWrapper {
        Row {
            DayPill(day = LocalDate.now(), selected = true, hasTasks = true, onClick = {})
            DayPill(day = LocalDate.now().plusDays(1), selected = false, hasTasks = false, onClick = {})
        }
    }
}
