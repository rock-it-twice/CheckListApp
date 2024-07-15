package com.example.letscheck.data.classes

import androidx.room.Embedded
import androidx.room.Relation


// Объединенный класс
data class JointUser(
    @Embedded
                        var userActivity: UserActivity?,

    @Relation(
                            parentColumn = "id",
                            entityColumn = "userId"
                        )
                        var entities: List<UserEntity>
)