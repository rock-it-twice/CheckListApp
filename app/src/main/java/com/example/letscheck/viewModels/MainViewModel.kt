package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.repository.MainRepository
import com.example.letscheck.data.classes.output.JointFolder
import com.example.letscheck.data.classes.main.Folder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val vmScope: CoroutineScope,
                    private val repository: MainRepository,
                    private val application: Application ) : ViewModel() {

    val folders: LiveData<List<Folder>> = repository.folders

    var currentFolderId: Long? by mutableStateOf(null)
        private set

    var currentJointFolder: JointFolder? by mutableStateOf( null )
        private set

    var counter: Int by mutableIntStateOf(0)

    var entityId: Long by mutableLongStateOf(0L)
        private set

    //______________________________________________________________________________________________

    // Folder
    fun addFolder(folderName: String) {
        vmScope.launch(Dispatchers.Default) {
            repository.addFolder(Folder(folderName = folderName))
        }
    }

    private fun getFolderId(id: Long?) { currentFolderId = id }

    fun getJointFolderById() {
        vmScope.launch(Dispatchers.Default) {
            when (currentFolderId) {
                0L   -> currentJointFolder = repository.getNullFolderWithAllEntities()
                else -> currentJointFolder = repository.getJointFolderById(currentFolderId)
            }
        }
    }

    fun changeFolder(id: Long?){
        getFolderId(id)
        getJointFolderById()
    }

    //______________________________________________________________________________________________

    // Entities

    fun countEntities() {
        vmScope.launch(Dispatchers.Default) {
            counter = when(currentFolderId)  {
                0L   -> { repository.countEntities() }
                else -> { repository.countEntitiesByFolderId(currentFolderId) }
            }
        }
    }

    fun getEntityId(id: Long) { entityId = id }

    fun deleteEntityById(id: Long) {
        vmScope.launch(Dispatchers.Default) {
            repository.deleteUserEntityById(id)
        }
    }

    // CheckBoxes
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = repository.getCheckedList(id)

    fun resetCheckBoxes(entityId: Long){
        vmScope.launch(Dispatchers.Default) { repository.resetCheckBoxes(entityId) }
    }

    //______________________________________________________________________________________________


    fun isGridVisible(): Boolean { return  currentJointFolder != null }

    fun clear() = vmScope.launch {
        currentJointFolder = null
        currentFolderId = null
        entityId = 0L
    }

}