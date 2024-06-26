package com.example.letscheck

import android.app.Application
import com.example.letscheck.data.MainDb

class App: Application() {
    val database by lazy { MainDb.createDb(this) }
}