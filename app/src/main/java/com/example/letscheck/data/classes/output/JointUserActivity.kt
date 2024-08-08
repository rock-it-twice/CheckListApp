package com.example.letscheck.data.classes.output

import androidx.room.Embedded
import androidx.room.Relation
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity


// Объединенный класс
data class JointUserActivity(

    @Embedded
    val userActivity: UserActivity,

    @Relation(
        entity = UserEntity::class,
        parentColumn = "id",
        entityColumn = "activityId"
    )
    val entities: List<JointEntity>

)