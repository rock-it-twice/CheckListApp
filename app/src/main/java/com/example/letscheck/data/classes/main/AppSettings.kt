package com.example.letscheck.data.classes.main

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "app_settings"
)
data class AppSettings(
    @PrimaryKey
    val key: String = "settings",
    private var _appTheme: AppTheme = AppTheme.LIGHT,

){
    val appTheme = _appTheme
    fun switchTheme(){
        when (_appTheme) {
            AppTheme.LIGHT -> this._appTheme = AppTheme.DARK
            AppTheme.DARK  -> this._appTheme = AppTheme.LIGHT
        }
    }

}

enum class AppTheme{ DARK, LIGHT }
