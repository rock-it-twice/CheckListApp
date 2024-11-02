package com.example.letscheck.viewModels

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

    val settings: LiveData<AppSettings> = repository.settings

    fun switchTheme(appSettings: AppSettings){
        vmScope.launch(Dispatchers.IO) {
            repository.switchTheme(appSettings)
        }
    }

    init {
        vmScope.launch {
            if (repository.isSettingsNotCreated())
            repository.insertSettings(appSettings = AppSettings())
        }
    }

}