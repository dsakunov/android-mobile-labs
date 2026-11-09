package com.example.tasker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun AppSwitch(checked: Boolean, modifier: Modifier = Modifier) {
    val colors = TaskerTheme.colors
    Switch(
        checked = checked,
        onCheckedChange = null,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = colors.accent,
            checkedThumbColor = Color.White,
            checkedBorderColor = colors.accent,
            uncheckedTrackColor = colors.switchOff,
            uncheckedThumbColor = Color.White,
            uncheckedBorderColor = colors.switchOff
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AppSwitchPreview() {
    PreviewWrapper {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AppSwitch(checked = false)
            AppSwitch(checked = true)
        }
    }
}
