package ru.skillbranch.devintensive.extentions

fun String.truncate(value: Int = 16): String  {
    val truncString = this.trimEnd()
    return if (truncString.length > value) "${truncString.substring(0 until value).trimEnd()}..." else truncString
}

fun String.stripHtml(): String = this.replace(Regex("(<(.|\\n)+?>)|(&quot;)|(&amp;)|(&gt;)|(&lt;)"), "").replace(Regex("\\s+"), " ")