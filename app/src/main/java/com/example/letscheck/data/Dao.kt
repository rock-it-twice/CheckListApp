package com.example.letscheck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.letscheck.data.classes.CheckBoxText
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert( onConflict = OnConflictStrategy.IGNORE )
    suspend fun addUser(user: User)
    @Insert( onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertUserEntity(userEntity: UserEntity)
    @Insert( onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertCheckList(checkList: CheckList)
    @Insert( onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertMultipleCheckLists(checkLists: List<CheckList>)
    @Insert ( onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertCheckBoxText(checkBoxText: CheckBoxText)
    @Insert ( onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertMultipleCheckBoxTexts(texts: List<CheckBoxText>)


//    @Delete(UserEntity::class)
//    suspend fun deleteUserEntity(userEntityId: Int)
//    @Delete(CheckList::class)
//    suspend fun deleteCheckList(checkListId: Int)
//    @Delete(CheckBoxText::class)
//    suspend fun deleteCheckBoxText(str: String)

    @Update
    suspend fun updateUser(user: User)
    @Update
    suspend fun updateUserEntity(userEntity: UserEntity)
    @Update
    suspend fun updateCheckList(checkList: CheckList)

    // Получение списка всех пользователей
    @Query( "SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
    // Получение пользователя по id
    @Query( "SELECT * FROM users WHERE userId LIKE :userId LIMIT 1" )
    fun getUserById(userId: Int): User?
    // Получение пользователя по имени
    @Query( "SELECT * FROM users WHERE user_name LIKE :userName" )
    fun getUserByName(userName: String): LiveData<User>
    // Получение всех списков пользователя
    @Query( "SELECT * FROM user_entities WHERE userId LIKE :userId" )
    fun getUserEntities(userId: Int): Flow<List<UserEntity>>
    // Получение конкретной entity по ID
    @Query( "SELECT * FROM user_entities WHERE entityId LIKE :entityId" )
    fun getUserEntity(entityId: Int): Flow<UserEntity>
    // Получение чеклиста
    @Query( "SELECT * FROM check_lists WHERE entityId LIKE :entityId" )
    fun getCheckList(entityId: Int): Flow<List<CheckList>>

    @Query ("SELECT * FROM check_box_text WHERE checkListId LIKE :checkListId")
    fun getCheckBoxTexts(checkListId: Int): Flow<List<CheckBoxText>>

    @Query("DELETE FROM users WHERE userId = :userId")
    fun deleteUser(userId: Int)


//    @Query("SELECT user_name AS userName, " +
//            "entity_name AS entityName, " +
//            "(checkList + checkListName) AS checkList FROM users " +
//            "INNER JOIN user_entities ON users.userId = user_entities.userId " +
//            "INNER JOIN check_lists ON user_entities.entityId = check_lists.entityId " +
//            "WHERE userId LIKE :userId AND entity_name LIKE :entityName")
//    fun getUserEntityChecklist(userId: Int, entityName: String): UserEntityChecklist

}