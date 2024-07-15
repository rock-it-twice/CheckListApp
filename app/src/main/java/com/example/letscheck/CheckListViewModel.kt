package com.example.letscheck

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.Dao
import com.example.letscheck.data.DataLoader
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CheckListViewModel(application: Application): ViewModel() {

    val vmScope: CoroutineScope
    val repository: ChecklistRepository
    var users: LiveData<List<User>>
    var allCheckBoxTitles: LiveData<List<CheckBoxTitle>>

    var userName: String by mutableStateOf("")
    var userId: Int by mutableIntStateOf(0)
    var currentUser: User? by mutableStateOf(null)

    var entities: List<UserEntity> by mutableStateOf(listOf())
    var entityName: String by mutableStateOf("")
    var entityId: Int by mutableIntStateOf(0)
    var currentEntity: UserEntity? by mutableStateOf(null)


    var jointCheckList: List<JointCheckList> by mutableStateOf(listOf())
    var checkLists: List<CheckList> by mutableStateOf(listOf())
    var currentCheckList: CheckList? by mutableStateOf(null)
    var checkListId: Int by mutableIntStateOf(0)


    var checkBoxTitles: List<CheckBoxTitle> by mutableStateOf(listOf())
    var checkBoxTitle: CheckBoxTitle? by mutableStateOf(null)
    var checkBoxTitleId: Int by mutableIntStateOf(0)

    init {
        val database = MainDb.createDatabase(application)
        val userDao: Dao = database.dao()
        repository = ChecklistRepository(userDao)
        users = repository.users
        allCheckBoxTitles = repository.checkBoxTitles
        vmScope = CoroutineScope(Dispatchers.Main)
        populateData()
    }

    // Загрузка начальных данных
    private fun populateData(){
        vmScope.launch(Dispatchers.IO) {
            DataLoader(repository).loadData()
        }
    }

    fun addUser(){
        vmScope.launch(Dispatchers.IO) {
            repository.addUser(User(userName = checkName(userName))) }
    }

    fun getUserById() {
        vmScope.launch(Dispatchers.IO) { currentUser = repository.getUserById(userId) }
    }

    fun getUserByName() {
        vmScope.launch (Dispatchers.IO) { currentUser = repository.getUserByName(userName) }
    }

    fun getUserId(value: Int){userId = value}

    fun checkName(value: String): String {return if (value != "") value else "undefined user"}

    fun changeName(value: String){ userName = value }
    fun changeUser(value: User?){ currentUser = value }
    //_________________________________________________________________________

    // ЗАГОЛОВКИ
    fun getEntitiesByUserId(){
        vmScope.launch (Dispatchers.IO) { entities = repository.getUserEntitiesByUserId(userId) }
    }
    //_________________________________________________________________________

    // ПОДЗАГОЛОВКИ
    fun getCheckListsByEntityId(){
        vmScope.launch(Dispatchers.IO) {
            checkLists = repository.getCheckListsByEntityId(entityId)
        }
    }

    fun getJointCheckList(){
        vmScope.launch(Dispatchers.IO) {
            jointCheckList = repository.getJointCheckList(entityId)
        }
    }
    //_________________________________________________________________________

    // ЧЕКБОКСЫ

    fun getCheckBoxTitles(){
        vmScope.launch(Dispatchers.IO) {
            checkBoxTitles = repository.getCheckBoxTitles(checkListId)
        }
    }
    fun getCheckBoxTitleById(){
        vmScope.launch(Dispatchers.IO) {
            checkBoxTitle = repository.getCheckBoxTitleById(checkBoxTitleId)
        }
    }

    fun deleteUser(id: Int) {
        vmScope.launch(Dispatchers.IO) {  repository.deleteUser(id) }
    }


    // Очистка заголовков
    fun clearStepByStep() = if (currentEntity != null) {
        jointCheckList = listOf()
        currentEntity = null
    } else currentUser = null

    fun clearCheckBoxTitles(){
        checkBoxTitles = listOf()
    }


}