package com.example.letscheck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.letscheck.data.classes.input.NewEntity
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.output.JointCheckList
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity

@Dao
interface Dao {

    // ДОБАВЛЕНИЕ ДАННЫХ
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserActivity(userActivity: UserActivity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserEntity(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckList(checkList: CheckList): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckLists(list: List<CheckList>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckBoxTitle(checkBoxTitle: CheckBoxTitle)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCheckBoxTitles(list: List<CheckBoxTitle>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(entity: NewEntity){

        val entityId = addUserEntity(entity.entity)
        entity.checkLists.forEachIndexed{ i, checkList ->

            val newCheckList = checkList.copy(entityId = entityId)
            val checkListId = addCheckList(newCheckList)
            entity.checkBoxTitles[i].forEach {
                addCheckBoxTitle(it.copy(checkListId = checkListId))
            }
        }
    }

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
    @Query("DELETE FROM user_activities WHERE id = :id")
    suspend fun deleteUserActivity(id: Long)

    @Query("DELETE FROM user_entities WHERE id = :entityId")
    suspend fun deleteEntity(entityId: Long)

    @Query("DELETE FROM check_lists WHERE id = :checkListId")
    suspend fun deleteCheckListTitle(checkListId: Long)

    @Query("DELETE FROM check_box_title WHERE text LIKE :str")
    suspend fun deleteCheckBoxByName(str: String)

    @Query("DELETE FROM check_box_title WHERE id LIKE :id")
    suspend fun deleteCheckBoxById(id: Long)

    @Query("DELETE FROM check_box_title WHERE checklist_id LIKE :checkListId")
    suspend fun deleteCheckBoxesByCheckListId(checkListId: Long)


    // ЗАПРОСЫ К БД --------------------------------------------

    // Получение списка всех пользователей
    @Query("SELECT * FROM user_activities")
    fun getAllUserActivities(): LiveData<List<UserActivity>>

    // Получение пользователя по id
    @Query("SELECT * FROM user_activities WHERE id LIKE :activityId LIMIT 1")
    suspend fun getUserActivityById(activityId: Long): UserActivity?


    // Получение пользователя по имени
    @Query("SELECT * FROM user_activities WHERE activity_name LIKE :activityName LIMIT 1")
    suspend fun getUserActivityByName(activityName: String): UserActivity?

    // ЗАГОЛОВКИ
    @Query("SELECT * FROM user_entities WHERE activity_id LIKE :activityId")
    fun getUserEntities(activityId: Long): List<UserEntity>

    // Получение entity по ID
    @Query("SELECT * FROM user_entities WHERE id LIKE :entityId LIMIT 1")
    fun getUserEntityById(entityId: Long): UserEntity?

    // Получение entity по имени
    @Query(
        "SELECT * FROM user_entities WHERE " +
                "entity_name LIKE :entityName AND activity_id LIKE :userId LIMIT 1"
    )
    fun getUserEntityByName(entityName: String, userId: Long): UserEntity?

    // JointUserActivity (JointUserActivity + JointEntities + JointChecklists)

    @Transaction
    @Query("SELECT * FROM user_activities")
    fun getJointUserActivities(): LiveData<List<JointUserActivity>>

    @Transaction
    @Query("SELECT * FROM user_activities WHERE user_activities.id LIKE :id LIMIT 1")
    suspend fun getJointUserActivityById(id: Long): JointUserActivity?

    @Transaction
    @Query("SELECT * FROM user_activities WHERE activity_name LIKE :name LIMIT 1")
    suspend fun getJointUserActivityByName(name: String): JointUserActivity?

    // ПОДЗАГОЛОВКИ
    @Query("SELECT * FROM check_lists WHERE entity_id LIKE :entityId")
    fun getCheckLists(entityId: Long): List<CheckList>

    @Query(
        "SELECT * FROM check_lists " +
                "WHERE id LIKE :checkListId AND entity_id LIKE :entityId LIMIT 1"
    )
    fun getCheckListById(checkListId: Long, entityId: Long): CheckList?

    @Query(
        "SELECT * FROM check_lists " +
                "WHERE checkListName LIKE :checkListName AND entity_id LIKE :entityId LIMIT 1"
    )
    fun getCheckListByName(checkListName: String, entityId: Long): CheckList?

    @Transaction
    @Query("SELECT * FROM user_activities WHERE id LIKE :id")
    suspend fun getJointUser(id: Long): JointUserActivity?

    @Transaction
    @Query("SELECT * FROM check_lists " +
            "WHERE check_lists.entity_id LIKE :entityId")
    suspend fun getJointCheckList(entityId: Long): List<JointCheckList>


    // ЧЕКБОКСЫ
    @Query("SELECT * FROM check_box_title WHERE checklist_id LIKE :checkListId")
    suspend fun getCheckBoxTitles(checkListId: Long): List<CheckBoxTitle>

    @Query("SELECT * FROM check_box_title WHERE id LIKE :id LIMIT 1")
    suspend fun getCheckBoxTitleById(id: Long): CheckBoxTitle?

    @Query("SELECT MAX(id) FROM check_box_title")
    suspend fun getLastCheckBoxId(): Long

}

