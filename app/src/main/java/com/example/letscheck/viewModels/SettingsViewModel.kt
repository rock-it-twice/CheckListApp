package com.example.letscheck.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val vmScope: CoroutineScope,
    val repository: SettingsRepository
) : ViewModel() {

    var settings by mutableStateOf(AppSettings())
        private set

    fun updateTheme(){
        vmScope.launch(Dispatchers.IO) {
            repository.updateTheme(settings)
            settings = repository.getSettings()
        }
    }

    init {
        vmScope.launch(Dispatchers.IO) {
            if (!repository.isSettingsCreated()) repository.insertSettings(appSettings = AppSettings())
        settings = repository.getSettings()
        }
    }

}
