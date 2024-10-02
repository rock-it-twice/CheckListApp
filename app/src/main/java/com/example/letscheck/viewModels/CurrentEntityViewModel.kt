package com.example.letscheck.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.letscheck.repository.ChecklistRepository
import kotlinx.coroutines.CoroutineScope

class CurrentEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    private val application: Application): ViewModel() {

}