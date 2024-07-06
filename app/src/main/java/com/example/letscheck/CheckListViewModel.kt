package com.example.letscheck

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.letscheck.data.Dao
import com.example.letscheck.data.DataLoader
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CheckListViewModel(application: Application): ViewModel() {

    val vmScope: CoroutineScope
    val repository: ChecklistRepository
    var users: LiveData<List<User>>

    var userName: String by mutableStateOf("")
    var userId: Int by mutableIntStateOf(0)
    var currentUser: User? by mutableStateOf(null)

    var entityName: String by mutableStateOf("")
    var entityId: Int by mutableIntStateOf(0)
    var entities: List<UserEntity> by mutableStateOf(listOf())
    var currentEntity: UserEntity? by mutableStateOf(null)

    init {
        val database = MainDb.createDatabase(application)
        val userDao: Dao = database.dao()
        DataLoader(userDao)
        repository = ChecklistRepository(userDao)
        users = repository.users
        vmScope = CoroutineScope(Dispatchers.Main)

    }

    fun addUser(){
        vmScope.launch(Dispatchers.IO) {
            repository.addUser(User(userName = checkName(userName))) }
    }
    fun getUserById() {
        vmScope.launch(Dispatchers.IO) { currentUser = repository.getUserById(userId) }
    }
    fun getUserId(value: Int){userId = value}

    fun checkName(value: String): String {return if (value != "") value else "undefined user"}
    fun changeName(value: String){ userName = value }

    fun deleteUser(id: Int) {
        vmScope.launch(Dispatchers.IO) {  repository.deleteUser(id) }
    }

}