package com.example.letscheck.repositories

import androidx.lifecycle.LiveData
import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.input.NewEntity
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

    suspend fun addCheckLists(checkLists: List<CheckList>) {
        withContext(Dispatchers.IO) {
            checkLists.forEach { userDao.addCheckList(it) }
        }
    }

    suspend fun addCheckBoxTitle(checkListId: Long, text: String) {
        withContext(Dispatchers.IO) {
            userDao.addCheckBoxTitle(
                CheckBoxTitle(checkListId = checkListId, text = text)
            )
        }
    }

    suspend fun addCheckBoxTitles(checkListId: Long, list: List<String>) {
        withContext(Dispatchers.IO) {
            list.forEach {
                addCheckBoxTitle(checkListId = checkListId, text = it)
            }
        }
    }

    suspend fun addCheckBoxTitle(checkBoxTitle: CheckBoxTitle){
        withContext(Dispatchers.IO) {
            userDao.addCheckBoxTitle(checkBoxTitle)
        }
    }

    suspend fun addCheckBoxTitles(list: List<CheckBoxTitle>) {
        withContext(Dispatchers.IO) {
            list.forEach { addCheckBoxTitle(it) }
        }
    }

    suspend fun addAll(entity: NewEntity) = withContext(Dispatchers.IO) { userDao.addAll(entity) }


    // USER ACTIVITIES
    suspend fun getUserActivityById(id: Long): UserActivity? {
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

    suspend fun getJointUserActivityById(id: Long): JointUserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointUserActivityById(id) }
    }

    suspend fun deleteUserActivity(id: Long) {
        withContext(Dispatchers.IO) { userDao.deleteUserActivity(id) }
    }

    // USER ENTITIES
    suspend fun getUserEntitiesByUserId(activityId: Long): List<UserEntity> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntities(activityId = activityId) }
    }

    suspend fun getUserEntityById(id: Long): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityById(id) }
    }

    suspend fun getUserEntityByName(name: String, userId: Long): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityByName(name, userId) }
    }

    suspend fun deleteUserEntityById(id: Long){
        withContext(Dispatchers.IO) {
            userDao.deleteEntity(id)
            userDao.deleteCheckBoxesByCheckListId(id)
        }
    }


    // CHECK LISTS
    suspend fun getCheckListsByEntityId(entityId: Long): List<CheckList> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckLists(entityId) }
    }

    suspend fun getChecklistByName(name: String, entityId: Long): CheckList? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckListByName(name, entityId) }
    }

    suspend fun getCheckListById(id: Long, entityId: Long): CheckList? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckListById(id, entityId) }
    }

    suspend fun getJointCheckList(entityId: Long): List<JointCheckList> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointCheckList(entityId) }
    }


    // CHECKBOX
    suspend fun getCheckBoxTitles(checkListId: Long): List<CheckBoxTitle> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckBoxTitles(checkListId) }
    }

    suspend fun getCheckBoxTitleById(id: Long): CheckBoxTitle? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getCheckBoxTitleById(id) }
    }

}





