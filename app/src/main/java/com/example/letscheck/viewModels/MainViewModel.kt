package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.repository.ChecklistRepository
import com.example.letscheck.data.classes.output.JointFolder
import com.example.letscheck.data.classes.main.Folder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class MainViewModel( private val vmScope: CoroutineScope,
                          private val repository: ChecklistRepository,
                          private val application: Application ) : ViewModel() {

    val folders: LiveData<List<Folder>> = repository.folders

    var folderName: String by mutableStateOf( "" )

    var currentFolderId: Long by mutableLongStateOf(0)
        private set

    var currentJointFolder: JointFolder? by mutableStateOf( null )
        private set

    var entityId: Long by mutableLongStateOf(0L)
        private set

    //______________________________________________________________________________________________

    // Folder
    fun addFolder() {
        vmScope.launch(Dispatchers.Default) {
            repository.addUserActivity(Folder(folderName = checkName(folderName)))
        }
    }

    private fun checkName(value: String): String {
        return if (value != "") value else "undefined activity"
    }

    fun getFolderId(id:Long) { currentFolderId = id }

    fun getJointFolderById() {
        vmScope.launch(Dispatchers.Default) {
            currentJointFolder = repository.getJointUserActivityById(currentFolderId)
        }
    }

    fun changeFolderName(value: String) { folderName = value }

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

    // Clear operations
    // Not implemented yet...
    fun deleteFolder(id: Long) {
        vmScope.launch(Dispatchers.Default) { repository.deleteUserActivity(id) }
    }

    fun isGridVisible(): Boolean { return  currentJointFolder != null }

    fun clear() = vmScope.launch {
        currentFolderId = 0
        currentJointFolder = null
        entityId = 0
    }

}