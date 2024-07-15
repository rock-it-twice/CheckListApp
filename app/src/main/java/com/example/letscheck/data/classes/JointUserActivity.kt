package com.example.letscheck.data.classes

import androidx.room.Embedded
import androidx.room.Relation


// Объединенный класс
data class JointUserActivity(
                        @Embedded
                        var userActivity: UserActivity?,

                        @Relation(
                            parentColumn = "id",
                            entityColumn = "activityId"
                        )
                        var entities: List<UserEntity>
)