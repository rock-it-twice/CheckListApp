package com.example.letscheck.data.classes.output

import androidx.room.Embedded
import androidx.room.Relation
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.data.classes.main.UserEntity


// Объединенный класс
data class JointFolder(

    @Embedded
    val folder: Folder,

    @Relation(
        entity = UserEntity::class,
        parentColumn = "id",
        entityColumn = "activity_id"
    )
    val entities: List<JointEntity>

)