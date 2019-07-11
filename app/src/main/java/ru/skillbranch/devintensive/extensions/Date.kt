package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.mm.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time +=
        when (units){
            TimeUnits.SECOND -> value * SECOND
            TimeUnits.MINUTE -> value * MINUTE
            TimeUnits.HOUR -> value * HOUR
            TimeUnits.DAY -> value * DAY
        }

    this.time = time
    return this
}

interface ITimeUnits {
    fun plural(value: Int): String
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            SECOND -> "$value ${when (value.toString().take(value.toString().length)) {
                "1" -> "секунду"
                "2", "3", "4" -> "секунды"
                else -> "секунд"
            }
            }"
            MINUTE -> "$value ${when (value.toString().take(value.toString().length)) {
                "1" -> "минуту"
                "2", "3", "4" -> "минуты"
                else -> "минут"
            }
            }"
            HOUR -> "$value ${when (value.toString().take(value.toString().length)) {
                "1" -> "час"
                "2", "3", "4" -> "часа"
                else -> "часов"
            }
            }"
            DAY -> "$value ${when (value.toString().take(value.toString().length)) {
                "1" -> "день"
                "2", "3", "4" -> "дня"
                else -> "дней"
            }
            }"
        }
    }
}

fun Date.humanizeDiff(): String {
    val date: Long = this.time
    val now: Long = Date().time

    val diff = (date - now) / 1000
    val diffMinutes = (diff / 60).toInt()
    val diffHours = (diff / (60 * 60)).toInt()
    val diffDays = (diff / (60 * 60 * 24)).toInt()

    return when (abs(diff)) {
        in 0..1 -> "только что"
        in 2..45 -> "несколько секунд назад"
        in 46..75 -> "минуту назад"
        in 75..2700 -> "${if (diffMinutes < 0) "" else "через "}${abs(diffMinutes)} " +
                "${when (abs(diffMinutes).toString().take(diffMinutes.toString().length)) {
                    "1" -> "минуту"
                    "2", "3", "4" -> "минуты"
                    else -> "минут"
                }}${if (diffMinutes < 0) " назад" else ""}"
        in 2700..4500 -> "час назад"
        in 4500..79200 -> "${if (diffHours < 0) "" else "через "}${abs(diffHours)} " +
                "${when (abs(diffHours).toString().take(diffHours.toString().length)) {
                    "1" -> "час"
                    "2", "3", "4" -> "часа"
                    else -> "часов"
                }}${if (diffHours < 0) " назад" else ""}"
        in 79200..31104000 -> "${if (diffDays < 0) "" else "через "}${abs(diffDays)} " +
                "${when (abs(diffDays).toString().take(diffDays.toString().length)) {
                    "1" -> "день"
                    "2", "3", "4" -> "дня"
                    else -> "дней"
                }}${if (diffDays < 0) " назад" else ""}"
        else -> if (diff < 0) "более года назад" else "более чем через год"
    }

}