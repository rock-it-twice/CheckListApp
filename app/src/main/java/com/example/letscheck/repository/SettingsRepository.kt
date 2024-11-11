package com.example.letscheck.repository

import com.example.letscheck.data.dao.SettingsDao
import com.example.letscheck.data.classes.main.AppSettings


class SettingsRepository(private val settingsDao: SettingsDao) {

    suspend fun insertSettings(appSettings: AppSettings){
        settingsDao.insertSettings(appSettings = appSettings)
    }

    suspend fun getSettings(): AppSettings = settingsDao.getSettings()

    suspend fun updateTheme(appSettings: AppSettings){
        appSettings.switchTheme()
        settingsDao.updateTheme(appSettings)
    }

    suspend fun isSettingsCreated(): Boolean{
        return ( settingsDao.count() > 0 )
    }

}