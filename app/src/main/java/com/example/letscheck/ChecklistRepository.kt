package com.example.letscheck

import androidx.lifecycle.LiveData
import com.example.letscheck.data.Dao
import com.example.letscheck.data.classes.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChecklistRepository(val userDao: Dao){

    val users = userDao.getAllUsers()

    suspend fun addUser(User: User) {
        withContext(Dispatchers.IO) { userDao.addUser(User) }
    }

    suspend fun getUserById(id: Int): User? {
        return withContext(Dispatchers.IO) { return@withContext userDao.getUserById(id) }
    }

    suspend fun deleteUser(id:Int){
        withContext(Dispatchers.IO) {userDao.deleteUser(id)}
    }
}
