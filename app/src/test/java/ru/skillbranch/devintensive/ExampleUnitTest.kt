package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extentions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
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

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("John Sichev")
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("John Sichev")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any text message",type="text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image message",type="image")

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }

    @Test
    fun test_to_initials() {
        println(Utils.toInitials("john" ,"doe"))
        println(Utils.toInitials("John", null))
        println(Utils.toInitials(null, null))
        println(Utils.toInitials(" ", ""))
    }

    @Test
    fun test_to_humanize() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты

        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год

    }

    @Test
    fun test_builder() {
        val user = User.Builder()
            .id("2")
            .firstName("Ivan")
            .lastName("Ivanov")
            .avatar("avatar")
            .rating(1)
            .respect(1)
            .lastVisit(Date())
            .isOnline(true)
            .build()

        user.printMe()

    }

    @Test
    fun test_transliterator() = println(Utils.transliteration("Amazing Петр","_"))

    @Test
    fun test_plural(){
        println(TimeUnits.SECOND.plural(4)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19)) //19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }

    @Test
    fun test_truncate(){
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }

    @Test
    fun test_stripHtml(){
        println("<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
    }

}

