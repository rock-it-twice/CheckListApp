package com.example.letscheck.viewModels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letscheck.repositories.ChecklistRepository
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope

class AddNewEntityViewModelFactory(private val vmScope: CoroutineScope,
                                   private val repository: ChecklistRepository,
                                   private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddNewEntityViewModel(vmScope, repository, application) as T
    }
}