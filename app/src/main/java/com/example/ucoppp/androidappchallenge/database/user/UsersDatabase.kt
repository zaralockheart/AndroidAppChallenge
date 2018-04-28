package com.example.ucoppp.androidappchallenge.database.user

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dagger.Module

@Module
@Database(entities = [(User::class)], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {


        @Volatile private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) : UsersDatabase =
                Room.databaseBuilder(context.applicationContext,
                        UsersDatabase::class.java, "Users")
                        .fallbackToDestructiveMigration()
                        .build()
    }

}