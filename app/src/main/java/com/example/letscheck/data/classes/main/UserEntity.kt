package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_entities",
    indices = [Index(value = ["activityId", "entity_name"], unique = true)],
    foreignKeys =
    [ForeignKey(
        entity = UserActivity::class,
        parentColumns = ["id"],
        childColumns = ["activityId"] )]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val activityId: Int,
    @ColumnInfo(name = "entity_name")
    var entityName: String = "",
    var image: Int = 0,
    val weight: Int = 0
){

}
