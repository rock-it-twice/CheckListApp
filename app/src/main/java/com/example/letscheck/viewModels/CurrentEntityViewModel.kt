package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.repository.ChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    private val application: Application): ViewModel() {



    var currentJointEntity: JointEntity? by mutableStateOf( null )


    //______________________________________________________________________________________________

    // Entity
    fun getJointEntityById(entityId: Long) {
        vmScope.launch(Dispatchers.IO){
            currentJointEntity = repository.getJointEntityById(entityId)
        }
    }

    fun clearJointEntity() { currentJointEntity = null }

    // CheckBoxes
    fun updateCheckBoxTitle(title: CheckBoxTitle) = vmScope.launch(Dispatchers.IO) {
        repository.updateCheckBoxTitle(title)
    }
    fun isChecked(id: Long): LiveData<Boolean> = repository.isChecked(id)
    fun getCheckedSubList(id: Long): LiveData<List<Boolean>> = repository.getCheckedSubList(id)
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = repository.getCheckedList(id)

    // Очистка переменных
    fun clear() = vmScope.launch { clearJointEntity() }

}