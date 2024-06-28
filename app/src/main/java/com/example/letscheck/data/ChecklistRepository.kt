package com.example.letscheck.data

import com.example.letscheck.data.classes.CheckBoxText
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.flow.map


class ChecklistRepository(val userDao: Dao){

    var user: User = User(userName = "")
    var entities: List<UserEntity> = listOf()
    var checkList: List<CheckList> = listOf()
    var checkBoxTexts: MutableList<List<CheckBoxText>> = mutableListOf()



    suspend fun setUserName(id: Int){
        userDao.getUserById(id).collect { user = it }
    }
    suspend fun setEntities(userId: Int){
        userDao.getUserEntities(userId).collect { entities = it }
    }
    suspend fun setChecklist(entityId: Int){
        userDao.getCheckList(entityId).collect { checkList = it }
    }
    suspend fun addCheckBoxTexts(ids: List<Int>) {
        checkBoxTexts.clear()
        ids.forEach{ userDao.getCheckBoxTexts(it).map {
                value -> checkBoxTexts.add(value)
            }
        }
    }



}
