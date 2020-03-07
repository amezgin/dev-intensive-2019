package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint
import ru.skillbranch.devintensive.extensions.TimeUnits

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replace("\\s+".toRegex(), " ")?.split(" ")
        return when(parts?.size) {
            null, 0 -> null to null
            1 -> (if (parts.get(0).isEmpty()) null else parts.get(0)) to null
            2 -> parts.get(0) to parts.get(1)
            else -> (if (parts.get(0).isEmpty()) null else parts.get(0)) to (if (parts.get(1).isEmpty()) null else parts.get(1))
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return when {
            firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> null
            !firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> firstName?.substring(0, 1)?.toUpperCase()
            firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && !lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> lastName?.substring(0, 1)?.toUpperCase()
            else -> firstName?.trim()?.replace("\\s+".toRegex(), " ")?.substring(0, 1)?.toUpperCase() + lastName?.trim()?.replace("\\s+".toRegex(), " ")?.substring(0, 1)?.toUpperCase()
        }
    }

    @SuppressLint("DefaultLocale")
    fun transliteration(payload: String, divider: String = " "): String? {
        val chars = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider)

        var result = ""
        payload.replace(" ", divider)
            .forEach {
                val symbol = if(!chars[(it.toString().toLowerCase())].isNullOrEmpty()) chars[(it.toString().toLowerCase())] else it.toString()
                result += if (it.isUpperCase()) symbol?.capitalize() else symbol
            }
        return result
    }

    fun plurals(count: Long, unit: TimeUnits): String {
        val lastDigit: String = lastDigit(count)

        return when(lastDigit) {
            "1", "01" -> {
                if (TimeUnits.SECOND.equals(unit)) "$count секунду"
                else if (TimeUnits.MINUTE.equals(unit)) "$count минуту"
                else if (TimeUnits.HOUR.equals(unit)) "$count час"
                else "$count день"
            }
            "2", "02", "3", "03", "4", "04"-> {
                if (TimeUnits.SECOND.equals(unit)) "$count секунды"
                else if (TimeUnits.MINUTE.equals(unit)) "$count минуты"
                else if (TimeUnits.HOUR.equals(unit)) "$count часа"
                else "$count дня"
            }
            else -> {
                if (TimeUnits.SECOND.equals(unit)) "$count секунд"
                else if (TimeUnits.MINUTE.equals(unit)) "$count минут"
                else if (TimeUnits.HOUR.equals(unit)) "$count часов"
                else "$count дней"
            }
        }
    }

    private fun lastDigit(count: Long): String {
        return if (count in 20..99) {
            count.toString().substring(count.toString().length - 1)
        } else if (count >= 100) {
            this.lastDigit(count.toString().substring(count.toString().length - 2).toLong())
        } else {
            count.toString()
        }
    }
}