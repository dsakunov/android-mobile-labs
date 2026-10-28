package com.example.tasker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.data.utils.capsMonth
import com.example.tasker.ui.theme.Border
import com.example.tasker.ui.theme.Ink
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.Teal
import java.time.LocalDate

@Composable
fun ScreenHeader(
    label: String,
    title: String,
    today: LocalDate,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth().padding(start = 22.dp, end = 18.dp, top = 22.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                label,
                color = Teal, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(title, color = Ink, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
        }
        OutlinedCard(
            modifier = Modifier.size(60.dp),
            colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Border)
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(today.dayOfMonth.toString(), color = Ink, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                Text(today.capsMonth(), color = Muted, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenHeaderPreview() {
    PreviewWrapper {
        ScreenHeader(label = "TASKER", title = "Настройки", today = LocalDate.now())
    }
}
