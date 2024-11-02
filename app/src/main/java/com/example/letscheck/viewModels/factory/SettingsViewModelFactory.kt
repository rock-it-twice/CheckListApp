package com.example.letscheck.viewModels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.letscheck.repository.SettingsRepository

import com.example.letscheck.viewModels.SettingsViewModel
import kotlinx.coroutines.CoroutineScope

class SettingsViewModelFactory(private val vmScope: CoroutineScope,
                               private val repository: SettingsRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(vmScope, repository) as T

    }
}