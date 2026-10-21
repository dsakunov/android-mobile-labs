package com.example.mobilelabs.domain

object WordGroups {
    val inputText =
        "Для заданного текста создайте две группы слов: первая - слова, длина которых больше" +
                "заданного числа, вторая - меньше или равна. Выведите слова каждой из групп," +
                "отсортировав их по длине"

    fun groupWords(number: Int): Pair<List<String>, List<String>> {
        val words = inputText
            .split(Regex("[^\\p{L}]+"))
            .filter { it.isNotBlank() }

        val groups = words.partition { it.length > number }

        val firstGroup = groups.first.sortedBy { it.length }
        val secondGroup = groups.second.sortedBy { it.length }

        return Pair(firstGroup, secondGroup)
    }

    fun run(numberText: String): String {
        val number = numberText.toIntOrNull()
            ?: return "Ошибка: введите целое число"

        if (number < 0) {
            return "Ошибка: число должно быть неотрицательным"
        }

        val (firstGroup, secondGroup) = groupWords(number)

        val firstLine = firstGroup
            .joinToString(", ")
            .ifEmpty { "Нет слов" }

        val secondLine = secondGroup
            .joinToString(", ")
            .ifEmpty { "Нет слов" }

        return "Больше $number:\n$firstLine\n\n" +
                "Меньше или равно $number:\n$secondLine"
    }
}