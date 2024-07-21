package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.ChecklistRepository
import com.example.letscheck.data.Dao
import com.example.letscheck.data.DataLoader
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.data.classes.JointUserActivity
import com.example.letscheck.data.classes.UserActivity
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow


class MainViewModel(application: Application) : ViewModel() {

    val vmScope: CoroutineScope
    val repository: ChecklistRepository
    var userActivities: LiveData<List<UserActivity>>

    var userActivityName: String by mutableStateOf("")
    var userActivityId: Int by mutableIntStateOf(0)
    var currentUserActivity: UserActivity? by mutableStateOf(null)
    var jointUserActivity: JointUserActivity? by mutableStateOf(null)

    var entities: List<UserEntity> by mutableStateOf(listOf())
    var entityName: String by mutableStateOf("")
    var entityId: Int by mutableIntStateOf(0)
    var currentEntity: UserEntity? by mutableStateOf(null)


    var jointCheckLists: List<JointCheckList> by mutableStateOf(listOf())
    var checkListId: Int by mutableIntStateOf(0)

    val checkBoxStateList: MutableList<MutableList<Boolean>> = mutableListOf()
    var checkBoxTitles: List<CheckBoxTitle> by mutableStateOf(listOf())
    var checkBoxTitle: CheckBoxTitle? by mutableStateOf(null)
    var checkBoxTitleId: Int by mutableIntStateOf(0)


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

    fun getUserActivityById() {
        vmScope.launch(Dispatchers.IO) {
            currentUserActivity = repository.getUserActivityById(userActivityId)
        }
    }

    fun getUserActivityByName() {
        vmScope.launch(Dispatchers.IO) {
            currentUserActivity = repository.getUserActivityByName(userActivityName)
        }
    }

    fun getUserActivityId(value: Int) {
        userActivityId = value
    }

    fun checkName(value: String): String {
        return if (value != "") value else "undefined user"
    }

    fun changeName(value: String) {
        userActivityName = value
    }

    fun changeUser(value: UserActivity?) {
        currentUserActivity = value
    }
    //_________________________________________________________________________

    // ЗАГОЛОВКИ
    fun getEntitiesByUserId() {
        vmScope.launch(Dispatchers.IO) {
            entities = repository.getUserEntitiesByUserId(userActivityId)
        }
    }
    //_________________________________________________________________________

    // ПОДЗАГОЛОВКИ
    fun getJointCheckList() {
        vmScope.launch(Dispatchers.IO) {
            jointCheckLists = repository.getJointCheckList(entityId)
        }
    }
    //_________________________________________________________________________

    // ЧЕКБОКСЫ
    fun getCheckBoxTitles() {
        vmScope.launch(Dispatchers.IO) {
            checkBoxTitles = repository.getCheckBoxTitles(checkListId)
        }
    }

    fun getCheckBoxTitleById() {
        vmScope.launch(Dispatchers.IO) {
            checkBoxTitle = repository.getCheckBoxTitleById(checkBoxTitleId)
        }
    }

    fun deleteUser(id: Int) {
        vmScope.launch(Dispatchers.IO) { repository.deleteUser(id) }
    }


    // Очистка заголовков
    fun clearStepByStep() {
        vmScope.launch(Dispatchers.IO) {
            if (currentEntity != null) {
                jointCheckLists = listOf()
                currentEntity = null
                checkBoxStateList.clear()
            } else {
                currentUserActivity = null
            }
        }
    }

}