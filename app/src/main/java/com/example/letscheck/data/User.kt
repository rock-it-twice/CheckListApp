package com.example.letscheck.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "users",
    indices = [Index("userId")]
    )
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    val userName: String = "userName"
)


@Entity(
    tableName = "user_entities",
    indices = [Index("entityId")],
    foreignKeys =
    [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"])]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val entityId: Int? = null,
    val userId: Int? = null,
    var entityName: String = ""
)


@Entity(tableName = "check_lists",
    indices = [Index("id")],
    foreignKeys =
    [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["entityId"],
        childColumns = ["entityId"])]
)
data class CheckList(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val entityId: Int? = null,
    val checkListName: String = "",
    val checkList: List<String>
)