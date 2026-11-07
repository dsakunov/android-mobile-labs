package com.example.tasker

import com.example.tasker.data.utils.capsMonth
import com.example.tasker.data.utils.fieldText
import com.example.tasker.data.utils.shortText
import com.example.tasker.data.utils.weekdayShort
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.util.Locale

class DateUtilsTest {
    private val date = LocalDate.of(2026, 1, 5)

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun fieldTextHasTwoDigitDayAndMonth() {
        assertEquals("05.01.2026", date.fieldText())
    }

    @Test
    fun shortTextShowsDayAndMonth() {
        assertEquals("5 Jan", date.shortText())
    }

    @Test
    fun capsMonthIsThreeUpperCaseLetters() {
        assertEquals("JAN", date.capsMonth())
    }

    @Test
    fun weekdayShortIsUpperCase() {
        assertEquals("MON", date.weekdayShort())
    }
}
