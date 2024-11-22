package com.example.letscheck.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letscheck.data.dao.Dao
import com.example.letscheck.data.dao.SettingsDao
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.data.classes.main.UserEntity

@Database(entities = [
        AppSettings::class,
        Folder::class,
        UserEntity::class,
        CheckList::class,
        CheckBoxTitle::class], version = 5
)
abstract class MainDb : RoomDatabase() {

    abstract fun dao(): Dao
    abstract fun settingsDao(): SettingsDao

    companion object {
        fun createDatabase(context: Context): MainDb{
            return Room
                .databaseBuilder(context, MainDb::class.java, "Main_database")
//                .createFromAsset("database/Main_database.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}