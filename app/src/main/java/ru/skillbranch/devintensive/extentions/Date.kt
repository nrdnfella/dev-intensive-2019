package ru.skillbranch.devintensive.extentions

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


enum class TimeUnits  : ITimeUnits {
    SECOND { override fun plural(value: Int): String {
            return "${value} ${when (value.toString().take(value.toString().length)) {
                "1" -> "секунда"
                "2", "3", "4" -> "секунды"
                else -> "секунд"
                }
                }"
    }}
    ,
    MINUTE{
        override fun plural(value: Int): String {
            return "${value} ${when (value.toString().take(value.toString().length)) {
                "1" -> "минуту"
                "2", "3", "4" -> "минуты"
                else -> "минут"
            }
            }"
        }},
    HOUR{
        override fun plural(value: Int): String {
            return "${value} ${when (value.toString().take(value.toString().length)) {
                "1" -> "час"
                "2", "3", "4" -> "часа"
                else -> "часов"
            }
            }"
        }},
    DAY{
        override fun plural(value: Int): String {
            return "${value} ${when (value.toString().take(value.toString().length)) {
                "1" -> "день"
                "2", "3", "4" -> "дня"
                else -> "дней"
            }
            }"
        }}

}
    fun Date.humanizeDiff(): String {
        var date: Long = this.getTime()
        var now: Long = Date().time

        var diff = (date - now) / 1000
        val diffMinutes = (diff / 60).toInt()
        val diffHours = (diff / (60 * 60)).toInt()
        val diffDays = (diff / (60 * 60 * 24)).toInt()

        return when (abs(diff)) {
            in 0..1 -> "только что"
            in 2..45 -> "несколько секунд назад"
            in 46..75 -> "минуту назад"
            in 76..2700 -> "${if (diffMinutes < 0) "" else "через "}$diffMinutes " +
                    "${when (diffMinutes.toString().take(diffMinutes.toString().length)) {
                        "1" -> "минуту"
                        "2", "3", "4" -> "минуты"
                        else -> "минут"
                    }}${if (diffMinutes < 0) " назад" else ""}"
            in 4559..79200 -> "час назад"
            in 82800..93600 -> "${if (diffHours < 0) "" else "через "}$diffHours " +
                    "${when (diffHours.toString().take(diffHours.toString().length)) {
                        "1" -> "час"
                        "2", "3", "4" -> "часа"
                        else -> "часов"
                    }}${if (diffHours < 0) " назад" else ""}"
            in 97200..31104000 -> "${if (diffDays < 0) "" else "через "}$diffDays " +
                    "${when (diffDays.toString().take(diffDays.toString().length)) {
                        "1" -> "день"
                        "2", "3", "4" -> "дня"
                        else -> "дней"
                    }}${if (diffDays < 0) " назад" else ""}"
            else -> "${if (diff < 0) "более года назад" else "более чем через год"}"
        }

    }