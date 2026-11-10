package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R

enum class DateStyle(@StringRes val labelRes: Int) {
    SHORT(R.string.date_style_short),
    NUMERIC(R.string.date_style_numeric),
    LONG(R.string.date_style_long)
}
