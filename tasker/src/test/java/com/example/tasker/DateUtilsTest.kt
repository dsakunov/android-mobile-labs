package com.example.tasker

import com.example.tasker.data.utils.capsMonth
import com.example.tasker.data.utils.fieldText
import com.example.tasker.data.utils.longText
import com.example.tasker.data.utils.shortText
import com.example.tasker.data.utils.styledText
import com.example.tasker.data.utils.weekdayShort
import com.example.tasker.domain.DateStyle
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

    @Test
    fun longTextShowsFullMonthName() {
        assertEquals("5 January", date.longText())
    }

    @Test
    fun styledTextFollowsSelectedStyle() {
        assertEquals("5 Jan", date.styledText(DateStyle.SHORT))
        assertEquals("05.01.2026", date.styledText(DateStyle.NUMERIC))
        assertEquals("5 January", date.styledText(DateStyle.LONG))
    }
}
