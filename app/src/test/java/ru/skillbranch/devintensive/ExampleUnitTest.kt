package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extentions.TimeUnits
import ru.skillbranch.devintensive.extentions.add
import ru.skillbranch.devintensive.extentions.format
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User(id = "1")
        val user2 = User(id = "2")
        val user3 = User(id = "3")

        //println("$user $user2 $user3")

        //user.printMe()
    }

    @Test
    fun test_Factory() {
//        println("""$User.Factory.makeUser("John Petrovich")""")
//        User.Factory.makeUser("John Semjonovich").printMe()
//        User.Factory.makeUser("John Govnov")
//        User.Factory.makeUser("")

        val usr = User.makeUser("John Sidorov")
        val usr2 = usr.copy(id="2", lastName="Cena")
        print("$usr \n $usr2")
    }


    @Test
    fun test_decomposition() {
        val usr = User.makeUser("John Sidorov")

        fun getUserInfo() = usr

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${usr.component1()}, ${usr.component2()}, ${usr.component3()}")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Sidorov")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastName = "Govrilov",lastVisit = Date().add(-2,TimeUnits.MINUTE))
        var user4 = user.copy(lastName = "Cena",lastVisit = Date().add(2,TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }
}
