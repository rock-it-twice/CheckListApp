package com.example.letscheck.repositories

import androidx.lifecycle.LiveData
import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.output.JointCheckList
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(val userDao: Dao) {

    val userActivities = userDao.getAllUserActivities()
    val jointUserActivities = userDao.getJointUserActivities()

    // INSERT INTO
    suspend fun addUserActivity(UserActivity: UserActivity) {
        withContext(Dispatchers.IO) { userDao.addUserActivity(UserActivity) }
    }

    suspend fun addUserEntity(userEntity: UserEntity) {
        withContext(Dispatchers.IO) { userDao.addUserEntity(userEntity) }
    }

    suspend fun addCheckList(checkList: CheckList) {
        withContext(Dispatchers.IO) { userDao.addCheckList(checkList) }
    }

    suspend fun addCheckBoxTitle(checkListId: Int, text: String) {
        withContext(Dispatchers.IO) {
            userDao.addCheckBoxTitle(
                CheckBoxTitle(checkListId = checkListId, text = text)
            )
        }
    }

    suspend fun addCheckBoxTitles(checkListId: Int, list: List<String>) {
        withContext(Dispatchers.IO) {
            list.forEach {
                addCheckBoxTitle(checkListId = checkListId, text = it)
            }
        }
    }


    // USER ACTIVITIES
    suspend fun getUserActivityById(id: Int): UserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserActivityById(id) }
    }

    suspend fun getUserActivityByName(name: String): UserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserActivityByName(name) }
    }

    suspend fun getJointUserActivities(): LiveData<List<JointUserActivity>> {
        return withContext(Dispatchers.IO) { return@withContext userDao.getJointUserActivities() }
    }

    suspend fun getJointUserActivityById(id: Int): JointUserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointUserActivityById(id) }
    }

    suspend fun deleteUser(id: Int) {
        withContext(Dispatchers.IO) { userDao.deleteUserActivity(id) }
    }


    // USER ENTITIES
    suspend fun getUserEntitiesByUserId(activityId: Int): List<UserEntity> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntities(activityId = activityId) }
    }

    suspend fun getUserEntityById(id: Int): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityById(id) }
    }

    suspend fun getUserEntityByName(name: String, userId: Int): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityByName(name, userId) }
    }

    suspend fun deleteUserEntityById(id: Int){
        withContext(Dispatchers.IO) {
            userDao.deleteEntity(id)
            userDao.deleteCheckBoxesByCheckListId(id)
        }
    }


    // CHECK LISTS
    suspend fun getCheckListsByEntityId(entityId: Int): List<CheckList> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckLists(entityId) }
    }

    suspend fun getChecklistByName(name: String, entityId: Int): CheckList? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckListByName(name, entityId) }
    }

    suspend fun getCheckListById(id: Int, entityId: Int): CheckList? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckListById(id, entityId) }
    }

    suspend fun getJointCheckList(entityId: Int): List<JointCheckList> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointCheckList(entityId) }
    }


    // CHECKBOX
    suspend fun getCheckBoxTitles(checkListId: Int): List<CheckBoxTitle> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckBoxTitles(checkListId) }
    }

    suspend fun getCheckBoxTitleById(id: Int): CheckBoxTitle? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckBoxTitleById(id) }
    }

}





