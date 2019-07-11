package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
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

fun caseTime(value: Int, timeUnit: TimeUnits): String{
    val howMach = value.rem(100)
    return    when (timeUnit) {
        TimeUnits.SECOND -> when (howMach){
            1 -> "секунду"
            in 2..4 -> "секунды"
            0,in 5..20 -> "секунд"
            else -> caseTime(howMach %10, TimeUnits.SECOND)
        }
        TimeUnits.MINUTE -> when (howMach){
            1 -> "минуту"
            in 2..4 -> "минуты"
            0,in 5..20 -> "минут"
            else -> caseTime(howMach %10, TimeUnits.MINUTE)
        }
        TimeUnits.HOUR -> when (howMach){
            1 -> "час"
            in 2..4 -> "часа"
            0,in 5..20 -> "часов"
            else -> caseTime(howMach %10, TimeUnits.HOUR)
        }
        TimeUnits.DAY -> when (howMach){
            1 -> "день"
            in 2..4 -> "дня"
            0,in 5..20 -> "дней"
            else -> caseTime(howMach %10, TimeUnits.DAY)
        }
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            SECOND -> "$value ${caseTime(abs(value),SECOND)}"
            MINUTE -> "$value ${caseTime(abs(value),MINUTE)}"
            HOUR -> "$value ${caseTime(abs(value),HOUR)}"
            DAY -> "$value ${caseTime(abs(value),DAY)}"
        }
    }
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = abs(this.time -  date.time) / 1000
    val inPast  = this.time < date.time
    val diffMinutes = (diff / 60).toInt()
    val diffHours = (diff / (60 * 60)).toInt()
    val diffDays = (diff / (60 * 60 * 24)).toInt()

    return when (abs(diff)) {
        in 0..1 -> "только что"
        in 1..45 -> "${if (inPast) "" else "через "}несколько секунд${if (inPast) " назад" else ""}"
        in 45..75 -> "${if (inPast) "" else "через "}минуту${if (inPast) " назад" else ""}"
        in 75..2700 -> "${if (inPast) "" else "через "}${abs(diffMinutes)} " +
                "${caseTime(abs(diffMinutes),TimeUnits.MINUTE)}${if (inPast) " назад" else ""}"
        in 2700..4500 -> "${if (inPast) "" else "через "}час${if (inPast) " назад" else ""}"
        in 4500..79200 -> "${if (inPast) "" else "через "}${abs(diffHours)} " +
                "${caseTime(abs(diffHours),TimeUnits.HOUR)}${if (inPast) " назад" else ""}"
        in 79200..93600 -> "${if (inPast) "" else "через "}день${if (inPast) " назад" else ""}"
        in 79200..31104000 -> "${if (inPast) "" else "через "}${abs(diffDays)} " +
                "${caseTime(abs(diffDays),TimeUnits.DAY)}${if (inPast) " назад" else ""}"
        else -> if (inPast) "более года назад" else "более чем через год"
    }

}