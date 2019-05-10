//package com.khtn.androidcamp.ROOM
//
//import android.arch.persistence.room.Database
//import android.arch.persistence.room.Room
//import android.arch.persistence.room.RoomDatabase
//import android.content.Context
//import com.khtn.androidcamp.DATABASE_NAME
//
///**
// * Created by Huu Hoang on 5/7/19.
// */
////@Database(entities = arrayOf(Student::class), version = 3)
////abstract class AppDatabase : RoomDatabase() {
////    abstract fun studentDAO(): StudentDAO
////
////    companion object {
////        private var instance: AppDatabase? = null
////
////        fun getRomInstance(context: Context): AppDatabase {
////            // TODO implement Singleton Pattern
////        }
////
////        private fun buildDatabase(context: Context) = Room.databaseBuilder(
////            context,
////            AppDatabase::class.java, "APP-NAME.db"
////        ).allowMainThreadQueries()
////            .build()
////    }
////}