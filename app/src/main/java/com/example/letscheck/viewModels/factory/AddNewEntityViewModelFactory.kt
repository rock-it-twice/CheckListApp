package com.example.letscheck.viewModels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letscheck.repository.MainRepository
import com.example.letscheck.viewModels.AddNewEntityViewModel
import kotlinx.coroutines.CoroutineScope

class AddNewEntityViewModelFactory(private val vmScope: CoroutineScope,
                                   private val repository: MainRepository,
                                   private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddNewEntityViewModel(vmScope, repository, application) as T
    }
}