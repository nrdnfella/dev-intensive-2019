package ru.skillbranch.devintensive.extentions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import java.util.Collections.replaceAll

fun User.toUserView() : UserView{

    val nickName = Utils.transliteration("$firstName $lastName", " ")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Last visit ${lastVisit.humanizeDiff()}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = "$nickName",
        initials = "$initials",
        avatar = "$avatar",
        status = "$status")
}

fun String.truncate(value: Int = 16): String  {
    val truncString = substring(0..if(value<0) 0 else value-1).trimEnd();
    return if (truncString.length == value) truncString + "..." else truncString
}


