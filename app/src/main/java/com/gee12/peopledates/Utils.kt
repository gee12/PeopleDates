package com.gee12.peopledates

import android.net.Uri
import androidx.constraintlayout.motion.widget.Debug
import androidx.core.util.PatternsCompat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {

        fun yearsBetweenDates(date1: Date, date2: Date) =
            ((date1.time - date2.time) / 1000 / 60 / 60 / 24 / 365).toInt()

        fun dateToSimpleString(date: Date?, format: String): String {
            return if (date == null) ""
                else SimpleDateFormat(format, Locale.getDefault()).format(date)
        }

        fun dateToString(date: Date?, format: String): String {
            return if (date == null) ""
                else String.format(Locale.getDefault(), format, date)
        }

        fun parseDate(dateString: String, format: String): Date? {
            try {
                return SimpleDateFormat(format, Locale.getDefault()).parse(dateString)
                //returnLocalDate.parse(dateString, DateTimeFormatter.ofPattern(format))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return null
        }

        fun parseUri(uriString: String?): Uri? {
            return try {
                Uri.parse(uriString)
            } catch (ex: Exception) {
                null
            }
        }

        fun isValidUrl(url: String): Boolean {
            return PatternsCompat.WEB_URL.matcher(url.toLowerCase(Locale.getDefault())).matches()
        }
    }
}