package com.example.tasker.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.ui.theme.RingMint

@Composable
fun ProgressRing(percent: Int, modifier: Modifier = Modifier) {
    val progress by animateFloatAsState(percent / 100f, label = "ring")
    Box(modifier.size(66.dp), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            color = RingMint,
            strokeWidth = 7.dp,
            trackColor = Color.White.copy(alpha = 0.2f),
            strokeCap = StrokeCap.Round,
            gapSize = 0.dp
        )
        Text("$percent%", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressRingPreview() {
    PreviewWrapper {
        ProgressRing(percent = 50)
    }
}
