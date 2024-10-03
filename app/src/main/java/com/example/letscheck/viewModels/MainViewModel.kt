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

    var entityId: Long by mutableLongStateOf(0)
        private set

    //______________________________________________________________________________________________

    // User activity
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

    //______________________________________________________________________________________________

    // Entities
    fun getEntityId(id: Long) { entityId = id }

    fun deleteEntityById(id: Long) {
        vmScope.launch(Dispatchers.IO) {
            repository.deleteUserEntityById(id)
        }
    }

    // CheckBoxes
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = repository.getCheckedList(id)

    fun resetCheckBoxes(entityId: Long){
        vmScope.launch(Dispatchers.IO) { repository.resetCheckBoxes(entityId) }
    }

    //______________________________________________________________________________________________

    // Clear operations
    fun deleteUserActivity(id: Long) {
        vmScope.launch(Dispatchers.IO) { repository.deleteUserActivity(id) }
    }

    fun isGridVisible(): Boolean { return  currentJointUserActivity != null }

    fun clear() = vmScope.launch {
        currentActivityId = 0
        currentJointUserActivity = null
        entityId = 0
    }



}