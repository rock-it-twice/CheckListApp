package com.example.letscheck.data.classes.output

import android.net.Uri
import androidx.core.net.toUri
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
        entityColumn = "entity_id"
    )
    val checkLists: List<JointCheckList>
)