package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_entities",
    indices = [Index(value = ["activityId", "id"], unique = true)],
    foreignKeys =
    [ForeignKey(
        entity = UserActivity::class,
        parentColumns = ["id"],
        childColumns = ["activityId"],
        onDelete = ForeignKey.CASCADE
    )
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = cnt,
    val activityId: Long,
    @ColumnInfo(name = "entity_name")
    var entityName: String = "",
    var image: String = "",
    val weight: Int = 0
) {
    init { cnt++ }
    companion object{ var cnt = 0L }
}
