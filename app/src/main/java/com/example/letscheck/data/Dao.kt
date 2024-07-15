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
import com.example.letscheck.data.classes.JointUserActivity
import com.example.letscheck.data.classes.UserActivity
import com.example.letscheck.data.classes.UserEntity

@Dao
interface Dao {

    // ДОБАВЛЕНИЕ ДАННЫХ
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserActivity(userActivity: UserActivity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserEntity(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckList(checkList: CheckList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckBoxTitle(checkBoxTitle: CheckBoxTitle)

    // ОБНОВЛЕНИЕ
    @Update
    suspend fun updateUserActivity(userActivity: UserActivity)

    @Update
    suspend fun updateUserEntity(userEntity: UserEntity)

    @Update
    suspend fun updateCheckList(checkList: CheckList)

    @Update
    suspend fun updateCheckBoxTitle(checkBoxTitle: CheckBoxTitle)

    // УДАЛЕНИЕ
    @Query("DELETE FROM user_activities WHERE id = :activityId")
    suspend fun deleteUserActivity(activityId: Int)

    @Query("DELETE FROM user_entities WHERE id = :entityId")
    suspend fun deleteEntity(entityId: Int)

    @Query("DELETE FROM check_lists WHERE id = :checkListId")
    suspend fun deleteCheckListTitle(checkListId: Int)

    @Query("DELETE FROM check_box_title WHERE checkListId LIKE :str")
    suspend fun deleteCheckBox(str: String)


    // ЗАПРОСЫ К БД --------------------------------------------

    // Получение списка всех пользователей
    @Query("SELECT * FROM user_activities")
    fun getAllUserActivities(): LiveData<List<UserActivity>>

    // Получение пользователя по id
    @Query("SELECT * FROM user_activities WHERE id LIKE :activityId LIMIT 1")
    suspend fun getUserActivityById(activityId: Int): UserActivity?

    // Получение пользователя по имени
    @Query("SELECT * FROM user_activities WHERE activity_name LIKE :activityName LIMIT 1")
    suspend fun getUserActivityByName(activityName: String): UserActivity?

    // ЗАГОЛОВКИ
    @Query("SELECT * FROM user_entities WHERE activityId LIKE :activityId")
    fun getUserEntities(activityId: Int): List<UserEntity>

    // Получение entity по ID
    @Query("SELECT * FROM user_entities WHERE id LIKE :entityId LIMIT 1")
    fun getUserEntityById(entityId: Int): UserEntity?

    // Получение entity по имени
    @Query(
        "SELECT * FROM user_entities WHERE " +
                "entity_name LIKE :entityName AND activityId LIKE :userId LIMIT 1"
    )
    fun getUserEntityByName(entityName: String, userId: Int): UserEntity?

    // JointUserActivity (UserActivity + Entity)

    @Transaction
    @Query("SELECT * FROM user_activities")
    fun getJointUserActivities(): LiveData<List<JointUserActivity>>

    @Transaction
    @Query("SELECT * FROM user_activities WHERE id LIKE :id LIMIT 1")
    suspend fun getJointUserActivityById(id: Int): JointUserActivity?

    @Transaction
    @Query("SELECT * FROM user_activities WHERE activity_name LIKE :name LIMIT 1")
    suspend fun getJointUserActivityByName(name: String): JointUserActivity?


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
    @Query("SELECT * FROM user_activities WHERE id LIKE :id")
    suspend fun getJointUser(id: Int): JointUserActivity?

    @Transaction
    @Query("SELECT * FROM check_lists " +
            "WHERE check_lists.entityId LIKE :entityId")
    suspend fun getJointCheckList(entityId: Int): List<JointCheckList>


    // ЧЕКБОКСЫ

    @Query("SELECT * FROM check_box_title WHERE checkListId LIKE :checkListId")
    suspend fun getCheckBoxTitles(checkListId: Int): List<CheckBoxTitle>

    @Query("SELECT * FROM check_box_title WHERE id LIKE :id LIMIT 1")
    suspend fun getCheckBoxTitleById(id: Int): CheckBoxTitle?

}

