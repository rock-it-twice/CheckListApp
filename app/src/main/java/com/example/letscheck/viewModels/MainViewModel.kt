package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.letscheck.repositories.ChecklistRepository
import com.example.letscheck.data.Dao
import com.example.letscheck.data.DataLoader
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.input.NewEntity
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity
import com.example.letscheck.data.classes.output.JointEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : ViewModel() {

    val vmScope: CoroutineScope
    val repository: ChecklistRepository

    val userActivities: LiveData<List<UserActivity>>
    var userActivityName: String by mutableStateOf("")
    var currentJointUserActivity: JointUserActivity? by mutableStateOf(null)
        private set

    // Пробуем изменить подход
    var newEntity: NewEntity? by mutableStateOf( null )
        private set

    var newChecklists: List<CheckList> by mutableStateOf(listOf())
        private set

    var currentJointEntity: JointEntity? by mutableStateOf(null)
        private set

    val checkBoxStateList: MutableList<MutableList<Boolean>> = mutableListOf()

    init {
        val database = MainDb.createDatabase(application)
        val userDao: Dao = database.dao()
        repository = ChecklistRepository(userDao)
        userActivities = repository.userActivities
        vmScope = CoroutineScope(Dispatchers.Main)
        populateData()
    }

    // Загрузка начальных данных
    private fun populateData() {
        vmScope.launch(Dispatchers.IO) {
            DataLoader(repository).loadData()
        }
    }

    fun addUserActivity() {
        vmScope.launch(Dispatchers.IO) {
            repository.addUserActivity(UserActivity(activityName = checkName(userActivityName)))
        }
    }

    fun checkName(value: String): String {
        return if (value != "") value else "undefined activity"
    }

    fun getJointUserActivityById(id: Int) {
        vmScope.launch(Dispatchers.IO) {
            currentJointUserActivity = repository.getJointUserActivityById(id)
        }
    }

    fun getJointActivityByIdAndClearPrevious(id: Int) {
        clearStepByStep()
        getJointUserActivityById(id)
    }


    fun changeActivityName(value: String) { userActivityName = value }

    //_________________________________________________________________________

    // ENTITIES

    fun getJointEntity(entity: JointEntity) {
        vmScope.launch(Dispatchers.Main) { currentJointEntity = entity }
    }
    private fun clearEntity() { currentJointEntity = null }


    // New entity

    fun createNewEntity() {
        newEntity = NewEntity(
            entity = UserEntity(activityId = currentJointUserActivity!!.userActivity.id)
        )
        println("new entity is: $newEntity")

    }

    fun renameNewEntity(str: String) {
        vmScope.launch(Dispatchers.Main) { newEntity!!.renameEntity(str) }
    }

    // New checklists

    fun addNewCheckList() {
        vmScope.launch (Dispatchers.Main) {
            newEntity!!.addCheckList()
            newChecklists = newEntity!!.checkLists.toList()
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

    fun deleteNewCheckList(index: Int) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.deleteCheckList(index)
            newChecklists = newEntity!!.checkLists.toList()
        }
    }
    fun deleteNewCheckList(checkList: CheckList) {
        vmScope.launch(Dispatchers.Main) {
            newEntity!!.deleteCheckList(checkList)
            newChecklists = newEntity!!.checkLists.toList()
        }
    }

    fun addNewCheckBox(index: Int, str: String) {
        vmScope.launch(Dispatchers.Main) { newEntity!!.addCheckBoxTitle(index, str) }
    }

    fun renameNewCheckBoxTitle(index: Int, checkBoxId: Int, str: String) {
        vmScope.launch(Dispatchers.Main) { newEntity!!.renameCheckBoxTitle(index, checkBoxId, str) }
    }

    fun deleteNewCheckBox(index: Int, checkBoxTitle: CheckBoxTitle) {
        vmScope.launch(Dispatchers.Main) { newEntity!!.deleteCheckBoxTitle(index, checkBoxTitle) }
    }


    //_________________________________________________________________________


    // ОПЕРАЦИИ УДАЛЕНИЯ
    fun deleteUser(id: Int) {
        vmScope.launch(Dispatchers.IO) { repository.deleteUser(id) }
    }

    fun isGridVisible(): Boolean {
        return  currentJointUserActivity != null && currentJointEntity == null
    }

    // Очистка констант
    fun clearStepByStep() {
        vmScope.launch(Dispatchers.Default) {
            when(true){
                (currentJointEntity != null) -> { clearEntity() ; checkBoxStateList.clear() }
                else                         -> { currentJointUserActivity = null }
            }
        }
    }



    fun checkNewEntityRelations(): Boolean{
        return try {
            currentJointUserActivity!!.userActivity.id != newEntity!!.entity.activityId
        } catch (e: NullPointerException) { true }
    }



}