package ru.skillbranch.devintensive.extentions

fun String.truncate(value: Int = 16): String  {
    val truncString = substring(0..if(value<0) 0 else value-1).trimEnd();
    return if (truncString.length == value) truncString + "..." else truncString
}
