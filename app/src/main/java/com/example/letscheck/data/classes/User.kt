package com.example.letscheck.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["user_name"], unique = true)]
    )
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    @ColumnInfo("user_name")
    val userName: String = "user"
)