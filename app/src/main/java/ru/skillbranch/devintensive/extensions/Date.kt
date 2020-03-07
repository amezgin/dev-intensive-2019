package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val isFuture: Boolean

    // Получаем разницу в мс
    var diff = date.time - this.time
    isFuture = diff <= 0
    diff = Math.abs(diff)

    if (isFuture) { // будущее
        return when(diff) {
            (0 * SECOND), (1 * SECOND) -> "через секунду"
            in (1 * SECOND)..(45 * SECOND) -> "через несколько секунд"
            in (45 * SECOND)..(75 * SECOND) -> "через минуту"
            in (75 * SECOND)..(45 * MINUTE) -> "через ${Utils.plurals(diff / MINUTE, TimeUnits.MINUTE)}"
            in (45 * MINUTE)..(75 * MINUTE) -> "через час"
            in (75 * MINUTE)..(22 * HOUR) -> "через ${Utils.plurals(diff / HOUR, TimeUnits.HOUR)}"
            in (22 * HOUR)..(26 * HOUR) -> "через день"
            in (26 * HOUR)..(360 * DAY) -> "через ${Utils.plurals(diff / DAY, TimeUnits.DAY)}"
            else -> "более чем через год"
        }

    } else { // прошлое
        return when(diff) {
            (0 * SECOND), (1 * SECOND) -> "только что"
            in (1 * SECOND)..(45 * SECOND) -> "несколько секунд назад"
            in (45 * SECOND)..(75 * SECOND) -> "минуту назад"
            in (75 * SECOND)..(45 * MINUTE) -> "${Utils.plurals(diff / MINUTE, TimeUnits.MINUTE)} назад"
            in (45 * MINUTE)..(75 * MINUTE) -> "час назад"
            in (75 * MINUTE)..(22 * HOUR) -> "${Utils.plurals(diff / HOUR, TimeUnits.HOUR)} назад"
            in (22 * HOUR)..(26 * HOUR) -> "день назад"
            in (26 * HOUR)..(360 * DAY) -> "${Utils.plurals(diff / DAY, TimeUnits.DAY)} назад"
            else -> "более года назад"
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return Utils.plurals(value.toLong(), unit = this)
    }
}