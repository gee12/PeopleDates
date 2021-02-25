package com.gee12.peopledates.data.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import com.gee12.peopledates.Utils
import java.util.*

data class Person(
    val id: Int?,
    val group: Group,
    val name: String,
    val dateBirth: Date,
    val dateDeath: Date?,
    val prof: String?,
    val info: String?,
    val imageUrl: String?
) {
    val age: Int = if (dateDeath != null && dateDeath > dateBirth)
        Utils.yearsBetweenDates(dateDeath, dateBirth)
        else Utils.yearsBetweenDates(Date(), dateBirth)

//    var bitmap: Bitmap? = null
}
