package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_activities",
    indices = [Index(value = ["activity_name"], unique = true)]
    )
data class UserActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("activity_name")
    val activityName: String
)