package com.example.letscheck.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letscheck.data.classes.CheckBoxText
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity

@Database(entities = [
        User::class,
        UserEntity::class,
        CheckList::class,
        CheckBoxText::class], version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract fun dao(): Dao
    companion object {
        fun createDatabase(context: Context): MainDb{
            return Room.databaseBuilder(
                context,
                MainDb::class.java, "Main_database"
            ).build()
        }


    }
}