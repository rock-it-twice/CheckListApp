package com.example.letscheck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity

@Dao
interface Dao {

    //ВСТАВКА В БД
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserEntity(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckList(checkList: CheckList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckBoxTitle(checkBoxTitle: CheckBoxTitle)

    //ОБНОВЛЕНИЕ
    @Update
    suspend fun updateUser(user: User)

    @Update
    suspend fun updateUserEntity(userEntity: UserEntity)

    @Update
    suspend fun updateCheckList(checkList: CheckList)

    // УДАЛЕНИЕ
    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUser(userId: Int)

    @Query("DELETE FROM user_entities WHERE id = :entityId")
    fun deleteEntity(entityId: Int)

    @Query("DELETE FROM check_lists WHERE id = :checkListId")
    fun deleteCheckListTitle(checkListId: Int)

    @Query("DELETE FROM check_box_title WHERE checkListId LIKE :str")
    fun deleteCheckBox(str: String)


    // Получение списка всех пользователей
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    // Получение пользователя по id
    @Query("SELECT * FROM users WHERE id LIKE :userId LIMIT 1")
    fun getUserById(userId: Int): User?

    // Получение пользователя по имени
    @Query("SELECT * FROM users WHERE user_name LIKE :userName LIMIT 1")
    fun getUserByName(userName: String): User?


    // ЗАГОЛОВКИ
    @Query("SELECT * FROM user_entities WHERE userId LIKE :userId")
    fun getUserEntities(userId: Int): List<UserEntity>

    // Получение entity по ID
    @Query("SELECT * FROM user_entities WHERE id LIKE :entityId LIMIT 1")
    fun getUserEntityById(entityId: Int): UserEntity?

    // Получение entity по имени
    @Query(
        "SELECT * FROM user_entities WHERE " +
                "entity_name LIKE :entityName AND userId LIKE :userId LIMIT 1"
    )
    fun getUserEntityByName(entityName: String, userId: Int): UserEntity?


    // ПОДЗАГОЛОВКИ
    @Query("SELECT * FROM check_lists WHERE entityId LIKE :entityId")
    fun getCheckLists(entityId: Int): List<CheckList>

    @Query(
        "SELECT * FROM check_lists " +
                "WHERE id LIKE :checkListId AND entityId LIKE :entityId LIMIT 1"
    )
    fun getCheckListById(checkListId: Int, entityId: Int): CheckList?

    @Query(
        "SELECT * FROM check_lists " +
                "WHERE checkListName LIKE :checkListName AND entityId LIKE :entityId LIMIT 1"
    )
    fun getCheckListByName(checkListName: String, entityId: Int): CheckList?

    @Transaction
    @Query("SELECT * FROM check_lists " +
            "WHERE check_lists.entityId LIKE :entityId")
    fun getJointCheckList(entityId: Int): List<JointCheckList>


    // ЧЕКБОКСЫ
    @Query("SELECT * FROM check_box_title")
    fun getAllCheckBoxTitles(): LiveData<List<CheckBoxTitle>>


    @Query("SELECT * FROM check_box_title WHERE checkListId LIKE :checkListId")
    fun getCheckBoxTitles(checkListId: Int): List<CheckBoxTitle>

    @Query("SELECT * FROM check_box_title WHERE id LIKE :id LIMIT 1")
    fun getCheckBoxTitleById(id: Int): CheckBoxTitle?

}

