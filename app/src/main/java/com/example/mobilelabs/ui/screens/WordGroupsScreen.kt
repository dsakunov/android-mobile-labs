package com.example.mobilelabs.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilelabs.domain.WordGroups
import com.example.mobilelabs.ui.theme.MobileLabsTheme

@Composable
fun WordGroupsScreen(modifier: Modifier = Modifier) {
    var number by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = WordGroups.inputText,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Исходный текст") },
            readOnly = true,
            minLines = 3
        )

        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Число") },
        )

        OutlinedTextField(
            value = output,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Результат") },
            readOnly = true,
            minLines = 5
        )

        Button(
            onClick = {
                output = WordGroups.run(number)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Разделить слова")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordGroupsScreenPreview() {
    MobileLabsTheme {
        WordGroupsScreen()
    }
}