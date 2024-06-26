package com.example.letscheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUser(user: User)
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUserEntity(userEntity: UserEntity)
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertCheckList(checkList: CheckList)

    @Delete
    suspend fun deleteUser(user: User)
    @Delete
    suspend fun deleteUserEntity(userEntity: UserEntity)
    @Delete
    suspend fun deleteCheckList(checkList: CheckList)

    @Update
    suspend fun updateUser(user: User)
    @Update
    suspend fun updateUserEntity(userEntity: UserEntity)
    @Update
    suspend fun updateCheckList(checkList: CheckList)

    // Получение списка всех пользователей
    @Query( "SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
    // Получение пользователя по имени
    @Query( "SELECT *  FROM users WHERE userName = :userId" )
    fun getUser(userId: Int): Flow<User>
    // Получение всех списков пользователя
    @Query( "SELECT * FROM user_entities WHERE userId = :userId" )
    fun getUserEntity(userId: Int): Flow<List<UserEntity>>
    // Получение чеклиста
    @Query( "SELECT * FROM check_lists WHERE entityId = :entityId" )
    fun getCheckList(entityId: Int): Flow<List<CheckList>>

}