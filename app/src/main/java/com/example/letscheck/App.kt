package com.example.letscheck

import android.app.Application

import com.example.letscheck.data.MainDb

class App: Application() {

    // Инициализация базы данных
    val database by lazy {  MainDb.createDatabase(this)  }

}