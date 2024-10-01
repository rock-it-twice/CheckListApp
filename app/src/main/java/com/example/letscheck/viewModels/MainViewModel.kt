package com.example.letscheck.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.repository.ChecklistRepository
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.output.JointEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class MainViewModel( private val vmScope: CoroutineScope,
                          private val repository: ChecklistRepository,
                          private val application: Application ) : ViewModel() {

    val userActivities: LiveData<List<UserActivity>> = repository.userActivities

    var userActivityName: String by mutableStateOf( "" )

    var currentActivityId: Long by mutableLongStateOf(0)
        private set

    var currentJointUserActivity: JointUserActivity? by mutableStateOf( null )
        private set

    var currentJointEntity: JointEntity? by mutableStateOf( null )
        private set

    val checkBoxStateList: MutableList<MutableList<Boolean>> = mutableListOf()


    fun addUserActivity() {
        vmScope.launch(Dispatchers.IO) {
            repository.addUserActivity(UserActivity(activityName = checkName(userActivityName)))
        }
    }

    private fun checkName(value: String): String {
        return if (value != "") value else "undefined activity"
    }

    fun getActivityId(id:Long) { currentActivityId = id }

    fun getJointUserActivityById() {
        vmScope.launch(Dispatchers.IO) {
            currentJointUserActivity = repository.getJointUserActivityById(currentActivityId)
        }
    }

    fun changeActivityName(value: String) { userActivityName = value }

    //_________________________________________________________________________

    // ENTITIES

    fun getJointEntity(entity: JointEntity) {
        vmScope.launch{
            checkBoxStateList.clear()
            currentJointEntity = entity
        }
    }

    fun deleteEntityById(id: Long) {
        vmScope.launch(Dispatchers.IO) {
            repository.deleteUserEntityById(id)
        }
    }

    fun clearJointEntity() { currentJointEntity = null }

    // CheckBoxes
    fun updateCheckBoxTitle(title: CheckBoxTitle){
        vmScope.launch(Dispatchers.IO) { repository.updateCheckBoxTitle(title) }
    }
    fun isChecked(id: Long): LiveData<Boolean> = repository.isChecked(id)
    fun getCheckedSubList(id: Long): LiveData<List<Boolean>> = repository.getCheckedSubList(id)
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = repository.getCheckedList(id)

    //_________________________________________________________________________

    // ОПЕРАЦИИ УДАЛЕНИЯ
    fun deleteUserActivity(id: Long) {
        vmScope.launch(Dispatchers.IO) { repository.deleteUserActivity(id) }
    }

    fun isGridVisible(): Boolean {
        return  currentJointUserActivity != null && currentJointEntity == null
    }

    // Очистка переменных
    fun clearStepByStep() {
        vmScope.launch(Dispatchers.Default) {
            when(true){
                (currentJointEntity != null) -> { clearJointEntity() ; checkBoxStateList.clear() }
                else                         -> { currentActivityId = 0
                                                  currentJointUserActivity = null
                                                }
            }
        }
    }



}