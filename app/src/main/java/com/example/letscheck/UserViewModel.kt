package com.example.letscheck

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.Dao

class UserViewModel(app: App): ViewModel() {
    init {
        val userDao = app.database.dao()
    }
}