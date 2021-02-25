package com.gee12.peopledates.data.db

import androidx.room.*
import com.gee12.peopledates.data.model.Person

@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM persons WHERE id = :id")
    fun getById(id: Int): PersonEntity?

    @Query("SELECT * FROM persons WHERE groupId = :groupId")
    fun getByGroupId(groupId: Int?): List<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<PersonEntity>)

    @Transaction
    fun replaceAllByGroup(persons: List<PersonEntity>, groupId: Int?): List<PersonEntity> {
        deleteByGroup(groupId) // нужно ли, если используется REPLACE ?
        insertAll(persons)
        return getByGroupId(groupId)
    }

    @Update
    fun update(person: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)

    @Query("DELETE FROM persons WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM persons WHERE groupId = :groupId")
    fun deleteByGroup(groupId: Int?)
}