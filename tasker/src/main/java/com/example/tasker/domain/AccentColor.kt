package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R

enum class AccentColor(@StringRes val labelRes: Int) {
    TEAL(R.string.accent_teal),
    BLUE(R.string.accent_blue),
    ORANGE(R.string.accent_orange)
}
