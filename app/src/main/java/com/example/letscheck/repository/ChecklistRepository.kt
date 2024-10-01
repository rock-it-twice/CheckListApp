package com.example.letscheck.repository

import androidx.lifecycle.LiveData
import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(private val userDao: Dao) {

    val userActivities = userDao.getAllUserActivities()

    // INSERT INTO
    suspend fun addUserActivity(userActivity: UserActivity) {
        userDao.addUserActivity(userActivity)
    }

    suspend fun addNewDataToDB(entity: UserEntity,
                               checkList: List<CheckList>,
                               checkBoxTitles: List<List<CheckBoxTitle>>) {
        userDao.addAll(entity, checkList, checkBoxTitles)
    }


    // USER ACTIVITIES
    suspend fun getJointUserActivityById(id: Long): JointUserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointUserActivityById(id) }
    }

    suspend fun deleteUserActivity(id: Long) {
        userDao.deleteUserActivity(id)
    }

    // USER ENTITIES
    suspend fun deleteUserEntityById(id: Long){
        withContext(Dispatchers.IO) {
            userDao.deleteEntity(id)
        }
    }

    // CHECKBOXES

    suspend fun updateCheckBoxTitle(title: CheckBoxTitle) {
        userDao.updateCheckBoxTitle(title)
    }
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = userDao.getCheckedList(id)
    fun isChecked(id: Long): LiveData<Boolean> = userDao.isChecked(id)

}





