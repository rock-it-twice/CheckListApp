package com.example.letscheck.repository

import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.input.NewEntity
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(val userDao: Dao) {

    val userActivities = userDao.getAllUserActivities()

    // INSERT INTO
    suspend fun addUserActivity(UserActivity: UserActivity) {
        withContext(Dispatchers.IO) { userDao.addUserActivity(UserActivity) }
    }

    suspend fun addEntityWithChecklists(entity: NewEntity) = userDao.addAll(entity)


    // USER ACTIVITIES

    suspend fun getJointUserActivityById(id: Long): JointUserActivity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointUserActivityById(id) }
    }

    suspend fun deleteUserActivity(id: Long) {
        withContext(Dispatchers.IO) { userDao.deleteUserActivity(id) }
    }

    // USER ENTITIES

    suspend fun deleteUserEntityById(id: Long){
        withContext(Dispatchers.IO) {
            userDao.deleteEntity(id)
        }
    }

}





