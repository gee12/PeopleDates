package com.gee12.peopledates

import android.text.TextUtils
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.data.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.*


object HtmlParser {

    private const val COL_DAY_MONTH_BIRTH = 0
    private const val COL_YEAR_BIRTH = 1
    private const val COL_NAME = 2
    private const val COL_PHOTO = 3
    private const val COL_PROF = 4
    private const val COL_INFO = 5
    private const val COL_RELIG = 6
    private const val COL_DAY_MONTH_DEATH = 7
    private const val COL_YEAR_DEATH = 8
    private const val DATE_FORMAT = "dd MMMM yyyy"

    suspend fun parsePersonsFromHtmlTable(html: String, group: Group/*, bitmapLoader: IBitmapLoader*/): List<Person>
        = withContext(Dispatchers.Default) {
        val res = arrayListOf<Person>()

        val doc: Document = Jsoup.parse(html)
        val rows: Elements = doc.select("tr")
        rows@for (row in rows) {
            var name: String = ""
            var dayMonthBirth: String = ""
            var yearBirth: String = ""
            var imageName: String? = ""
            var prof: String? = ""
            var info: String? = ""
            var relig: String? = ""
            var dayMonthDeath: String? = ""
            var yearDeath: String? = ""

            val columns: Elements = row.select("td")
            for ((index, column) in columns.withIndex()) {
                when (index) {
                    COL_DAY_MONTH_BIRTH -> {
                        dayMonthBirth = column.text()
                        if (TextUtils.isEmpty(dayMonthBirth)) continue@rows
                    }
                    COL_YEAR_BIRTH -> {
                        yearBirth = column.text()
                        if (TextUtils.isEmpty(yearBirth)) continue@rows
                    }
                    COL_NAME ->  {
                        name = column.text()
                        if (TextUtils.isEmpty(name)) continue@rows
                    }
                    COL_PHOTO -> {
                        val img = column.selectFirst("img")
                        if (img != null) {
                            imageName = img.attr("src")
                        }
                    }
                    COL_PROF -> {
                        prof = column.text()
                    }
                    COL_INFO -> {
                        info = column.text()
                    }
                    COL_RELIG -> {
                        relig = column.text()
                    }
                    COL_DAY_MONTH_DEATH -> {
                        dayMonthDeath = column.text()
                    }
                    COL_YEAR_DEATH -> {
                        yearDeath = column.text()
                    }
                }
            }

            val dateBirth: Date = Utils.parseDate(
                dayMonthBirth.plus( " ").plus(yearBirth) ?: "", DATE_FORMAT) ?: continue
            val dateDeath: Date? = Utils.parseDate(
                dayMonthDeath?.plus( " ")?.plus(yearDeath) ?: "", DATE_FORMAT)
            val imageUrl = imageName?.let { group.url.plus(imageName) }

            res.add(Person(null, group, name, dateBirth, dateDeath, prof, info, imageUrl).apply {
//                imageName?.let {
//                    bitmap = bitmapLoader.load(group.url.plus(imageName))
//                    imageUrl = group.url.plus(imageName)
//                }
            })
        }
        res
    }
}