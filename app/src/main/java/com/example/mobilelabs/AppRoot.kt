package com.example.mobilelabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mobilelabs.ui.screens.WordGroupsScreen

@Composable
fun AppRoot(modifier: Modifier = Modifier) {
    WordGroupsScreen(modifier = modifier)
}