package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R

enum class Priority(@StringRes val labelRes: Int) {
    LOW(R.string.priority_low),
    MEDIUM(R.string.priority_medium),
    HIGH(R.string.priority_high)
}
