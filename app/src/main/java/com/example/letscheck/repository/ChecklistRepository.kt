package com.example.letscheck.repository

import androidx.lifecycle.LiveData
import com.example.letscheck.data.dao.Dao
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.output.JointFolder
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.data.classes.main.UserEntity
import com.example.letscheck.data.classes.output.JointEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(private val userDao: Dao) {

    val folders = userDao.getAllFolders()

    // INSERT INTO
    suspend fun addFolder(folder: Folder) {
        userDao.addFolder(folder)
    }

    suspend fun addNewDataToDB(entity: UserEntity,
                               checkList: List<CheckList>,
                               checkBoxTitles: List<List<CheckBoxTitle>>) {
        userDao.addAll(entity, checkList, checkBoxTitles)
    }


    // FOLDERS
    suspend fun getJointFolderById(id: Long?): JointFolder? {
        return withContext(Dispatchers.IO)
        { return@withContext userDao.getJointFolderById(id) }
    }

    suspend fun updateFolder(folder: Folder){
        userDao.updateFolder(folder)
    }

    suspend fun deleteFolder(id: Long) {
        userDao.deleteFolder(id)
    }

    // USER ENTITIES
    suspend fun getJointEntityById(entityId: Long): JointEntity? {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getJointEntity(entityId)
        }
    }

    suspend fun deleteUserEntityById(id: Long){
        withContext(Dispatchers.IO) {
            userDao.deleteEntity(id)
        }
    }

    suspend fun resetCheckBoxes(entityId: Long){
        withContext(Dispatchers.IO) {
            userDao.resetCheckBoxes(entityId)
        }
    }

    // CHECKBOXES
    suspend fun updateCheckBoxTitle(title: CheckBoxTitle) { userDao.updateCheckBoxTitle(title) }

    fun getCheckedList(entityId: Long): LiveData<List<Boolean>> = userDao.getCheckedList(entityId)
    fun getCheckedSubList(id: Long): LiveData<List<Boolean>> = userDao.getCheckedSubList(id)
    fun isChecked(id: Long): LiveData<Boolean> = userDao.isChecked(id)

}





