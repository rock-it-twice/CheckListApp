package com.example.letscheck.data.classes.output

import androidx.room.Embedded
import androidx.room.Relation
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.UserEntity


// Объединенный класс
data class JointEntity(

    @Embedded
    val entity: UserEntity,

    @Relation(
        entity = CheckList::class,
        parentColumn = "id",
        entityColumn = "entityId"
    )
    val checkLists: List<JointCheckList>

)