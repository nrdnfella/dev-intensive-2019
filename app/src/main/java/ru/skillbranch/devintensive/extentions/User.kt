package ru.skillbranch.devintensive.extentions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

fun User.toUserView() : UserView{

    val nickName = Units.transliteration("$firstName $lastName")
    val initials = ""
    val status = if(lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Last visit ${lastVisit.humanizeDiff()}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status)
}

private fun Date.humanizeDiff() : String {

}