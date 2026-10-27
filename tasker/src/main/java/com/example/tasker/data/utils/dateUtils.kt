package com.example.tasker.data.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun currentLocale(): Locale = Locale.getDefault()

private fun LocalDate.formatPattern(pattern: String): String =
    format(DateTimeFormatter.ofPattern(pattern, currentLocale()))

private fun String.capitalizeFirst(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(currentLocale()) else it.toString() }

fun LocalDate.shortText(): String = formatPattern("d MMM")

fun LocalDate.capsMonth(): String =
    formatPattern("LLL").replace(".", "").take(3).uppercase(currentLocale())

fun LocalDate.fieldText(): String = "%02d.%02d.%d".format(dayOfMonth, monthValue, year)

fun LocalDate.weekdayShort(): String =
    formatPattern("EEE").replace(".", "").uppercase(currentLocale())

fun LocalDate.fullDayText(): String =
    formatPattern("EEEE, d MMMM").split(" ").joinToString(" ") { it.capitalizeFirst() }

fun LocalDate.monthYearCaps(): String {
    val text = formatPattern("LLLL yyyy").uppercase(currentLocale())
    return if (currentLocale().language == "ru") "$text Г." else text
}
