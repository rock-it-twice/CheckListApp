package com.example.letscheck.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.data.classes.main.AppTheme

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSettings(appSettings: AppSettings)

    @Update
    suspend fun switchTheme(appSettings: AppSettings)

    @Query("SELECT * FROM app_settings WHERE `key` LIKE 'settings' LIMIT 1")
    fun getSettings(): LiveData<AppSettings>

    @Query("SELECT COUNT(*) FROM app_settings")
    suspend fun count(): Int

}