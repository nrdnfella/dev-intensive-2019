package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
){

    private constructor(builder: Builder) : this(builder.id, builder.firstName, builder.lastName, builder.avatar, builder.rating, builder.respect, builder.lastVisit, builder.isOnline)

    private constructor(id: String, firstName: String?, lastName: String?): this (
        id,
        firstName,
        lastName,
        avatar = null
    )

    constructor(id: String):this (
        id,
        firstName = "John",
        lastName = "Ivanovich"
    )


    init {
        println("hello, it's me")
    }

    fun printMe() {
        println("""
            id:$id
            firstName:$firstName
            lastName:$lastName
            avatar:$avatar
            rating:$rating
            respect:$respect
            lastVisit:$lastVisit
            isOnline:$isOnline
        """.trimIndent())
    }

    companion object Factory{
        private var lastId = -1
        fun makeUser(fullName:String?):User{
            lastId++
            val lastId1 = lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id="$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder(
        var id: String = "0",
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = null,
        var isOnline: Boolean = false
    ){
        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String?) = apply { this.firstName = firstName }
        fun lastName(lastName: String?) = apply { this.lastName = lastName }
        fun avatar(avatar: String?) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date?) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline}

        fun build() = User(this)
    }
}
