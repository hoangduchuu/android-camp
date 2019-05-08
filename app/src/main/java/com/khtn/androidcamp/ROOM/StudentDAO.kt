package com.khtn.androidcamp.ROOM

import android.arch.persistence.room.*
import com.khtn.androidcamp.Student
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.arch.persistence.room.OnConflictStrategy



/**
 * Created by Huu Hoang on 5/7/19.
 */
@Dao
interface StudentDAO {
    @Query("SELECT * FROM student")
    fun getAll(): List<Student>


    @Query("SELECT * FROM student WHERE name LIKE :name")
    fun findByName(name: String): Student

    @Query("SELECT * FROM student WHERE id =:id")
    fun findById(id: Int): Student

    @Insert
    fun insertAll(vararg todo: Student) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Student): Long

    @Delete
    fun delete(todo: Student)

    @Update
    fun update( student: Student)

    @Query("DELETE FROM student")
    fun deleteAllStudent()
}