package com.khtn.androidcamp.ROOM

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.khtn.androidcamp.DATABASE_NAME

/**
 * Created by Huu Hoang on 5/7/19.
 */
@Database(entities = arrayOf(Student::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDAO(): StudentDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }
}