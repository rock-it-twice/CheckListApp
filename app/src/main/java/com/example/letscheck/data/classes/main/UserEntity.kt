package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.letscheck.data.classes.main.CheckBoxTitle.Companion

@Entity(
    tableName = "user_entities",
    foreignKeys =
    [ForeignKey(
        entity = UserActivity::class,
        parentColumns = ["id"],
        childColumns = ["activity_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "activity_id")
    var activityId: Long = 0,
    @ColumnInfo(name = "entity_name")
    var entityName: String = "",
    var image: String = "",
    val weight: Int = 0
) {
    init { cnt++ }
    companion object{ private var cnt: Int = 0 }

    @Ignore
    val index = cnt
}
