package com.example.letscheck.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, UserEntity::class, CheckList::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract val dao: Dao

    companion object{
        fun createDb(context: Context): MainDb {
            return Room.databaseBuilder(context = context, MainDb::class.java, "").build()
        }
    }
}