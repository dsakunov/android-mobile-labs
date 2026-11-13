package com.example.tasker.data.utils

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList

fun Context.setAppLanguage(languageTag: String) {
    getSystemService(LocaleManager::class.java).applicationLocales =
        LocaleList.forLanguageTags(languageTag)
}
