package com.example.letscheck.repository

import com.example.letscheck.data.dao.SettingsDao
import com.example.letscheck.data.classes.main.AppSettings

class SettingsRepository(private val settingsDao: SettingsDao) {

    val theme = settingsDao.getThemeSettings()

    suspend fun insertSettings(appSettings: AppSettings){
        settingsDao.insertSettings(appSettings = appSettings)
    }

    suspend fun switchTheme(appSettings: AppSettings){
        appSettings.switchTheme()
        settingsDao.switchTheme(appSettings)
    }

}