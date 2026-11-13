package com.example.tasker.domain

import androidx.annotation.StringRes
import com.example.tasker.R
import java.util.Locale

enum class AppLanguage(val tag: String, @StringRes val nameRes: Int) {
    RU("ru", R.string.language_name_ru),
    EN("en", R.string.language_name_en);

    companion object {
        fun current(): AppLanguage =
            entries.firstOrNull { it.tag == Locale.getDefault().language } ?: RU
    }
}
