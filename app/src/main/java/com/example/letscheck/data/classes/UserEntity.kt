package com.example.letscheck.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_entities",
    indices = [Index(value = ["userId", "entity_name"], unique = true)],
    foreignKeys =
    [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"] )]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    @ColumnInfo(name = "entity_name") var entityName: String = ""
)
