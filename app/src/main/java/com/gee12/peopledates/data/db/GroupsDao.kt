package com.gee12.peopledates.data.db

import androidx.room.*

@Dao
interface GroupsDao {

    @Query("SELECT * FROM groups")
    fun getAll(): List<GroupEntity>

    @Query("SELECT * FROM groups WHERE id = :id")
    fun getById(id: Int?): GroupEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(group: GroupEntity): Long

    @Update
    fun update(group: GroupEntity)

    @Delete
    fun delete(group: GroupEntity)

    @Query("DELETE FROM groups WHERE id = :id")
    fun deleteById(id: Int?)
}