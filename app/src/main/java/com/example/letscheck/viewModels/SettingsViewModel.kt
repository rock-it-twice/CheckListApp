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
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val vmScope: CoroutineScope,
    private val repository: SettingsRepository
) : ViewModel() {

    var settings by mutableStateOf( AppSettings() )

    fun updateTheme(){
        vmScope.launch(Dispatchers.Default) {
            repository.updateTheme(settings)
        }
    }

    fun getSettings(){
        vmScope.launch(Dispatchers.Default) {
            settings = repository.getSettings()
        }
    }

    init {
        vmScope.launch {
            withContext(Dispatchers.Default){
                repository.initializeSettings()
            }
            getSettings()
        }

    }

}
