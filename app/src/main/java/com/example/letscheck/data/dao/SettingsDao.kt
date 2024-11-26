package com.example.letscheck.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.letscheck.data.classes.main.AppSettings

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSettings(appSettings: AppSettings)

    @Update
    suspend fun updateTheme(appSettings: AppSettings)

    @Query("SELECT * FROM app_settings WHERE `key` LIKE 'settings' LIMIT 1")
    suspend fun getSettings(): AppSettings?

    @Query("SELECT COUNT(*) FROM app_settings")
    suspend fun count(): Int

    @Transaction
    suspend fun initializeSettings(){
        if (count() == 0) { insertSettings(appSettings = AppSettings()) }
    }

}