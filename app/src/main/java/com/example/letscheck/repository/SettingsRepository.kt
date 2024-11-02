package com.example.letscheck.repository

import androidx.lifecycle.LiveData
import com.example.letscheck.data.dao.SettingsDao
import com.example.letscheck.data.classes.main.AppSettings


class SettingsRepository(private val settingsDao: SettingsDao) {

    val settings: LiveData<AppSettings> = settingsDao.getSettings()

    suspend fun insertSettings(appSettings: AppSettings){
        settingsDao.insertSettings(appSettings = appSettings)
    }

    suspend fun switchTheme(appSettings: AppSettings){
        appSettings.switchTheme()
        settingsDao.switchTheme(appSettings)
    }

    suspend fun isSettingsCreated(): Boolean{
        return ( settingsDao.count() > 0 )
    }

}