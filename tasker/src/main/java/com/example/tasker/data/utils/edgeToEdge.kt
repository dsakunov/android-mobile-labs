package com.example.tasker.data.utils

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import android.graphics.Color as AndroidColor

fun ComponentActivity.setupEdgeToEdge(darkTheme: Boolean) {
    val barStyle = if (darkTheme) {
        SystemBarStyle.dark(AndroidColor.TRANSPARENT)
    } else {
        SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT)
    }
    enableEdgeToEdge(statusBarStyle = barStyle, navigationBarStyle = barStyle)
}
