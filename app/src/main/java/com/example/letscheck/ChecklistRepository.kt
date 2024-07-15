package com.example.letscheck

import androidx.lifecycle.LiveData
import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(val userDao: Dao) {

    val users = userDao.getAllUsers()
    val checkBoxTitles = userDao.getAllCheckBoxTitles()

    // INSERT INTO
    suspend fun addUser(User: User) {
        withContext(Dispatchers.IO) { userDao.addUser(User) }
    }
    suspend fun addUserEntity(userEntity: UserEntity) {
        withContext(Dispatchers.IO) { userDao.addUserEntity(userEntity) }
    }
    suspend fun addCheckList(checkList: CheckList) {
        withContext(Dispatchers.IO) { userDao.addCheckList(checkList) }
    }
    suspend fun addCheckBoxTitle(checkBoxTitle: CheckBoxTitle) {
        withContext(Dispatchers.IO) { userDao.addCheckBoxTitle(checkBoxTitle) }
    }
    suspend fun addCheckBoxTitles(checkListId: Int, list: List<String> ) {
        withContext(Dispatchers.IO) { list.forEach{
            val checkBoxTitle = CheckBoxTitle(checkListId = checkListId, str = it)
            addCheckBoxTitle(checkBoxTitle)
        }
        }
    }


    // USERS
    suspend fun getUserById(id: Int): User? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserById(id) }
    }

    suspend fun getUserByName(name: String): User? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserByName(name) }
    }

    suspend fun deleteUser(id: Int) {
        withContext(Dispatchers.IO) { userDao.deleteUser(id) }
    }


    // USER ENTITIES
    suspend fun getUserEntitiesByUserId(userId: Int): List<UserEntity> {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntities(userId = userId) }
    }

    suspend fun getUserEntityById(id: Int): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityById(id) }
    }

    suspend fun getUserEntityByName(name: String, userId: Int): UserEntity? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getUserEntityByName(name, userId) }
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
        {return@withContext userDao.getCheckBoxTitleById(id)}
    }

}





