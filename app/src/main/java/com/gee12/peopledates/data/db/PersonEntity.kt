package com.gee12.peopledates.data.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "groupId")
    val groupId: Int,

    @ColumnInfo(name = "name")
    val name: String,

//    @ColumnInfo(name = "dateBirth", typeAffinity = TEXT)
    @ColumnInfo(name = "dateBirth")
    val dateBirth: Long,

//    @ColumnInfo(name = "dateDeath", typeAffinity = TEXT)
    @ColumnInfo(name = "dateDeath")
    val dateDeath: Long?,

    @ColumnInfo(name = "prof")
    val prof: String?,

    @ColumnInfo(name = "info")
    val info: String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?

//    @ColumnInfo(name = "bitmap")
//    val bitmap: Bitmap
)