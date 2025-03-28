package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.MapColumn
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
    val folderIDsWithCounts: LiveData<Map<Long?, @MapColumn("count") Int>> =
        repository.folderIDsWithCounts

    var currentFolderId: Long? by mutableStateOf(0L)
        private set

    var currentJointFolder: JointFolder? by mutableStateOf( null )
        private set

    var entityId: Long by mutableLongStateOf(0L)
        private set

    //______________________________________________________________________________________________

    // Folder

    private fun getFolderId(id: Long?) { currentFolderId = id }

    fun getJointFolderById() {
        vmScope.launch(Dispatchers.Default) {
            currentJointFolder = when (currentFolderId) {
                0L   -> repository.getAllJointEntities()
                null -> repository.getNullJointFolder()
                else -> repository.getJointFolderById(currentFolderId)
            }
        }
    }

    fun changeFolder(id: Long?){
        getFolderId(id)
        getJointFolderById()
    }

    //______________________________________________________________________________________________

    // Entities

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
        currentFolderId = 0L
        getJointFolderById()
        entityId = 0L
    }

}