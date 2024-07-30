package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.R
import com.example.letscheck.repositories.ChecklistRepository
import com.example.letscheck.data.Dao
import com.example.letscheck.data.DataLoader
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.input.UserActivity
import com.example.letscheck.data.classes.output.JointEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : ViewModel() {

    val vmScope: CoroutineScope
    val repository: ChecklistRepository

    val userActivities: LiveData<List<UserActivity>>
    var userActivityName: String by mutableStateOf("")
    var currentJointUserActivity: JointUserActivity? by mutableStateOf(null)
        private set

    var currentJointEntity: JointEntity? by mutableStateOf(null)
        private set

    var checkListState by mutableStateOf( false )
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
        vmScope.launch(Dispatchers.IO) { currentJointEntity = entity }
    }

    private fun clearEntity() { currentJointEntity = null }

    //_________________________________________________________________________


    // ОПЕРАЦИИ УДАЛЕНИЯ
    fun deleteUser(id: Int) {
        vmScope.launch(Dispatchers.IO) { repository.deleteUser(id) }
    }

    fun isGridVisible(): Boolean {
        return  currentJointUserActivity != null && currentJointEntity == null
    }

    // Очистка переменных
    fun clearStepByStep() {
        vmScope.launch(Dispatchers.IO) {
            when(true){
                (currentJointEntity != null) -> { clearEntity() ; checkBoxStateList.clear() }
                else                         -> { currentJointUserActivity = null }
            }
        }
    }
}