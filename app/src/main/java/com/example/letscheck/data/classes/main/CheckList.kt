package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.letscheck.data.classes.main.CheckBoxTitle.Companion

@Entity(tableName = "check_lists",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["entity_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class CheckList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "entity_id")
    var entityId: Long = 0,
    var checkListName: String = ""
    ){

    init { cnt ++ }
    companion object { private var cnt: Int = 0 }

    @Ignore
    val index = cnt

}
