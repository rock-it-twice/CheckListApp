package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_entities",
    foreignKeys =
    [ForeignKey(
        entity = Folder::class,
        parentColumns = ["id"],
        childColumns = ["folder_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "folder_id")
    var folderId: Long? = null,
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
