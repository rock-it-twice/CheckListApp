package com.example.letscheck.viewModels

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.UserEntity
import com.example.letscheck.repository.ChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class AddNewEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    private val application: Application): ViewModel() {

    var entityId: Long by mutableLongStateOf(0L)
        private set

    // New Entity creation
    var newEntity: UserEntity? by mutableStateOf( null )
        private set

    // New Entity image
    var newImageUri: Uri? by mutableStateOf(null)
        private set

    private var newImageName: String? by mutableStateOf(null)

    var newChecklists: List<CheckList> by mutableStateOf( listOf() )
        private set

    var newCheckBoxes: List<List<CheckBoxTitle>> by mutableStateOf( listOf() )
        private set


    // New entity
    fun createNewEntity(activityId: Long, str: String = "") {
        newEntity = UserEntity( folderId = activityId, entityName = str )
        addNewImageUri(null)
        clearNewCheckLists()
        clearNewCheckBoxes()
    }

    fun clearNewEntity(){
        newEntity = null
        addNewImageUri(null)
        clearNewCheckLists()
        clearNewCheckBoxes()
    }

    fun renameNewEntity(str: String) {
        vmScope.launch(Dispatchers.Main) { newEntity = newEntity!!.copy(entityName = str) }
    }

    // new image
    fun addNewImageUri(uri: Uri?){
        vmScope.launch(Dispatchers.Main) {
            newImageUri = uri
            newImageName = when(newImageUri == null){
                true -> { null }
                false -> { newImageUri!!.path?.let { File(it).name } }
            }
        }
    }

    // Сохранение выбранного изображения во внутреннем хранилище
    fun saveImageToInternalStorage(){
        if (newImageUri != null) {
            val inputStream = application.contentResolver.openInputStream(newImageUri!!)
            val outputStream =
                application.openFileOutput(newImageName, Context.MODE_PRIVATE)

            inputStream?.use { input ->
                outputStream.use { output -> input.copyTo(output) }
            }
        }
    }
    // Получение нового uri после сохранения в internal storage
    fun getNewImageUriFromInternalStorage(){
        if (newImageName != null) {
            val directory = application.applicationContext.filesDir.path
            newImageUri = "$directory/$newImageName".toUri()
        }
    }

    // Запись uri в класс
    fun assignImageToNewEntity(){
        newEntity = newEntity!!.copy( image = newImageUri?.toString() ?: "" )

    }


    fun getEntityId(id: Long) { entityId = id }

    // Setting the current entity
    fun setCurrentEntityAsNew(){
        vmScope.launch {
            val entity = repository.getJointEntityById(entityId)
            if (entity != null) {
                newEntity = entity.entity
                newImageUri = entity.entity.image.toUri()
                newChecklists = entity.checkLists.map { it.checkList }
                newCheckBoxes = entity.checkLists.map { it.checkBoxTitles.toList() }
            }
        }
    }

    // New checklists
    fun addNewCheckList() {
        val newList = newChecklists.toMutableList()
        newList.add(CheckList())
        newChecklists = newList.toList()

        val newSubList = newCheckBoxes.toMutableList()
        newSubList.add(listOf())
        newCheckBoxes = newSubList.toList()
    }

    fun renameNewCheckList(index: Int, str: String) {
        val newList = newChecklists.toMutableList()
        newList[index].checkListName = str
        newChecklists = newList.toList()
    }

    fun deleteNewCheckList(index: Int) {
        val newList = newChecklists.toMutableList()
        val newSubList = newCheckBoxes.toMutableList()
        newSubList.removeAt(index)
        newList.removeAt(index)
        newCheckBoxes = newSubList
        newChecklists = newList
    }

    private fun clearNewCheckLists(){
        newChecklists = listOf()
    }

    // New CheckBoxTitles
    fun addNewCheckBox(index: Int) {
        val newSubLists = newCheckBoxes.toMutableList()
        val newSubList = newSubLists[index].toMutableList()
        newSubList.add(CheckBoxTitle())
        newSubLists[index] = newSubList
        newCheckBoxes = newSubLists.toList()
    }

    fun renameNewCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle, str: String) {
        val newSubLists = newCheckBoxes.toMutableList()
        newSubLists[index].find { it == checkBoxTitle }!!.text = str
        newCheckBoxes = newSubLists
    }

    fun deleteNewCheckBox(listIndex: Int, checkBoxTitle: CheckBoxTitle) {
        val newSubLists = newCheckBoxes.toMutableList()
        val newSubList = newSubLists[listIndex].toMutableList()
        newSubList.remove(checkBoxTitle)
        newSubLists[listIndex] = newSubList
        newCheckBoxes = newSubLists.toList()
    }

    private fun clearNewCheckBoxes(){
        newCheckBoxes = listOf()
    }

    fun saveNewDataToDB() {
        vmScope.launch(Dispatchers.IO) {
            repository.addNewDataToDB(
                entity = newEntity!!,
                checkList = newChecklists,
                checkBoxTitles = newCheckBoxes
            )
        }
    }

    fun checkNewEntityRelations(currentActivityId: Long): Boolean{
        return try {
            ( currentActivityId != newEntity!!.folderId )
        } catch (e: NullPointerException) { true }
    }

}