package com.example.letscheck.data.classes.input

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "check_lists",
    indices = [Index(value = ["entityId", "checkListName"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["entityId"]
    )]
)

data class CheckList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val entityId: Int,
    val checkListName: String,
    val weight: Int = 0
    )
