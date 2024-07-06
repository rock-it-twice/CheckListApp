package com.example.letscheck.data.classes

import androidx.room.Embedded
import androidx.room.Relation


// Объединенный класс
class JointUser {

    @Embedded
    var user: User? = null

    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    var entities: List<UserEntity> = listOf()

}