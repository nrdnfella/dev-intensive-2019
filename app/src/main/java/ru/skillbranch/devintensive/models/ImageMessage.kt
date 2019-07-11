package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extentions.*
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        return "id:$id ${from?.firstName} " +
                "${if(isIncoming) "получил" else "отправил"} сообщение \"$image\" ${date.format()}"
    }

}