package ru.skillbranch.devintensive.utils

object Utils {

    private val translitMap = mapOf("а" to "a", "б" to "b","в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i",
        "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f",
        "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts = fullName?.trim()?.split(" ")
        val firstName = parts?.getOrNull(0)?.ifBlank { null }
        val lastName = parts?.getOrNull(1)?.ifBlank { null }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val builder = StringBuilder()
        for (i in 0 until payload.length) {
            builder.append(translitMap[payload[i].toString()]?:(translitMap[payload[i].toLowerCase().toString()])?.capitalize()?:"${if (payload[i].isLetter()) payload[i] else divider}")
        }
        return builder.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials = "${firstName?.trim()?.take(1)?.capitalize() ?: ""}${lastName?.trim()?.take(1)?.capitalize() ?: ""}"
        return if (initials.isEmpty()) null else initials
    }

    fun division(n: Int) = n * 205 shr 11
}