package com.example.tasker

import com.example.tasker.domain.AppLanguage
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class AppLanguageTest {
    private val defaultLocale = Locale.getDefault()

    @After
    fun tearDown() {
        Locale.setDefault(defaultLocale)
    }

    @Test
    fun englishLocaleGivesEnglish() {
        Locale.setDefault(Locale.ENGLISH)
        assertEquals(AppLanguage.EN, AppLanguage.current())
    }

    @Test
    fun russianLocaleGivesRussian() {
        Locale.setDefault(Locale.forLanguageTag("ru"))
        assertEquals(AppLanguage.RU, AppLanguage.current())
    }

    @Test
    fun unknownLocaleFallsBackToRussian() {
        Locale.setDefault(Locale.GERMANY)
        assertEquals(AppLanguage.RU, AppLanguage.current())
    }
}
