package com.gee12.peopledates.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [PersonEntity::class, GroupEntity::class],
    version = 8,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class PersonsDatabase : RoomDatabase() {

    abstract val groupsDao: GroupsDao
    abstract val personsDao: PersonsDao

    companion object {
        private const val DATABASE_NAME = "Persons.db"

        fun create(applicationContext: Context): PersonsDatabase = Room.databaseBuilder(
                applicationContext,
                PersonsDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}
