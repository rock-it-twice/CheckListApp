package com.example.letscheck.data.classes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "check_lists",
indices = [Index(value = ["entityId", "checkListName"], unique = true)],
foreignKeys =
[ForeignKey(
entity = UserEntity::class,
parentColumns = ["entityId"],
childColumns = ["entityId"] )]
)
data class CheckList(
    @PrimaryKey(autoGenerate = true)
    val checkListId: Int = 0,
    val entityId: Int,
    val checkListName: String = "",

    )
