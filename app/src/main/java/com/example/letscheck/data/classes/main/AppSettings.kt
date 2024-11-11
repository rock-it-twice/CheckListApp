package com.example.letscheck.data.classes.main

import androidx.room.Entity
import androidx.room.PrimaryKey


enum class AppTheme{ DARK, LIGHT }

@Entity(
    tableName = "app_settings"
)
data class AppSettings(
    @PrimaryKey
    val key: String = "settings",
    var appTheme: AppTheme = AppTheme.LIGHT

){

    fun switchTheme(){
        appTheme = when (appTheme) {
            AppTheme.LIGHT -> AppTheme.DARK
            AppTheme.DARK  -> AppTheme.LIGHT
        }
    }

}


