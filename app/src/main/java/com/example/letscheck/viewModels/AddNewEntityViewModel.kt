package com.example.letscheck.viewModels

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.input.NewEntity
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.UserEntity
import com.example.letscheck.repositories.ChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class AddNewEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    private val application: Application): ViewModel() {

    // New Entity creation
    var newEntity: NewEntity? by mutableStateOf( null )
        private set

    // New Entity image
    var currentImageUri: Uri? by mutableStateOf(null)
        private set

    var currentImageName: String? by mutableStateOf(null)
        private set

    var newChecklists: List<CheckList> by mutableStateOf( listOf() )
        private set

    var newCheckBoxes: List<List<CheckBoxTitle>> by mutableStateOf( listOf() )
        private set


    // New entity
    fun createNewEntity(activityId: Int, str: String = "") {

        newEntity = NewEntity(
            entity = UserEntity(
                activityId = activityId,
                entityName = str
            )
        )
        addNewCurrentImageUri(null)
        currentImageName = null
        clearNewCheckLists()
        clearNewCheckBoxes()

    }

    fun clearAddNewEntityScreenData(){
        newEntity = null
        addNewCurrentImageUri(null)
        currentImageName = null
        clearNewCheckLists()
        clearNewCheckBoxes()
    }

    fun renameNewEntity(str: String) {
        vmScope.launch(Dispatchers.Main) { newEntity!!.renameEntity(str) }
    }

    // new image
    fun addNewCurrentImageUri(uri: Uri?){
        vmScope.launch(Dispatchers.Main) {
            currentImageUri = uri
            if (uri != null) { currentImageName = uri.path?.let { File(it).name } }
        }
    }

    // Сохранение выбранного изображения во внутреннем хранилище
    fun saveImageToInternalStorage(){
        if (currentImageUri != null) {
            vmScope.launch(Dispatchers.IO) {
                val inputStream = application.contentResolver.openInputStream(currentImageUri!!)
                val outputStream =
                    application.openFileOutput(currentImageName, Context.MODE_PRIVATE)

                inputStream?.use { input ->
                    outputStream.use { output -> input.copyTo(output) }
                }
            }
        }
    }

    fun getNewImageUriFromInternalStorage(){
        vmScope.launch(Dispatchers.IO) {
            if (currentImageName != null) {
                val directory = application.applicationContext.filesDir.path
                currentImageUri = "$directory/$currentImageName".toUri()
            }
        }
    }

    fun assignImageToNewEntity(){
        vmScope.launch(Dispatchers.IO) {
            newEntity!!.entity.image = currentImageUri.toString()
        }
    }

    // New checklists

    fun addNewCheckList(str: String = "") {
        vmScope.launch (Dispatchers.Main) {
            newEntity!!.addCheckList(str)
            newChecklists = newEntity!!.checkLists.toList()
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun renameNewCheckList(index: Int, str: String) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.renameCheckList(index, str)
            newChecklists = newEntity!!.checkLists.toList()
        }
    }
    fun renameNewCheckList(checkList: CheckList, str: String) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.renameCheckList(checkList, str)
            newChecklists = newEntity!!.checkLists.toList()
        }
    }

    fun deleteNewCheckList(checkList: CheckList) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.deleteCheckList(checkList)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
            newChecklists = newEntity!!.checkLists.toList()
        }
    }

    private fun clearNewCheckLists(){
        newChecklists = listOf()
    }

    // New CheckBoxTitles

    fun addNewCheckBox(index: Int, str: String = "") {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.addCheckBoxTitle(index, str)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun renameNewCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle, str: String) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.renameCheckBoxTitle(index, checkBoxTitle, str)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun renameNewCheckBoxTitle(checkBoxTitle: CheckBoxTitle, str: String) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.renameCheckBoxTitle(checkBoxTitle = checkBoxTitle, str)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun deleteNewCheckBox(listIndex: Int, checkBoxTitle: CheckBoxTitle) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.deleteCheckBoxTitle(listIndex, checkBoxTitle)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun clearNewCheckBoxes(){
        newCheckBoxes = listOf()
    }

    fun deleteNewCheckBoxByIndex(listIndex: Int, index: Int) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.deleteCheckBoxTitleByIndex(listIndex, index)
            newCheckBoxes = newEntity!!.checkBoxTitles.toList()
        }
    }

    fun saveNewEntityToDataBase() {
        vmScope.launch(Dispatchers.IO) {
            repository.addUserEntity(newEntity!!.entity)
            newEntity!!.checkLists.forEach{ repository.addCheckList(it) }
            newEntity!!.checkBoxTitles.forEach{ repository.addCheckBoxTitles(it) }
        }
    }

    fun checkNewEntityRelations(currentActivityId: Int): Boolean{
        return try {
            ( currentActivityId != newEntity!!.entity.activityId )
        } catch (e: NullPointerException) { true }
    }

}