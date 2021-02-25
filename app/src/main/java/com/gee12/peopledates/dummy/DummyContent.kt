package com.gee12.peopledates.dummy

import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.data.model.Person
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    val random = Random()

    val GROUP = Group(22222, "Test",
        "https://bitbucket.org/gee12/mytetradata/src/master/base/1555666975q3x79jvh82/")

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Person> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, Person> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: Person) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id ?: 0, item)
    }

    private fun createDummyItem(position: Int): Person {
        val age = random.nextInt(100)
        val dateBirth = Calendar.getInstance().apply { add(Calendar.YEAR, -age) }.time
        val isDead = random.nextBoolean()
        val dateDeath = if (isDead) Date() else null
        return Person(position, GROUP, "Person full name " + position, dateBirth, dateDeath,
            "Professional", null, makeInfo(position))
    }

    private fun makeInfo(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about person: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}