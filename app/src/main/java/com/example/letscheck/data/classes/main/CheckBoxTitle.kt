package com.example.letscheck.data.classes.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "check_box_title",
    foreignKeys = [
        ForeignKey(
        entity = CheckList::class,
        parentColumns = ["id"],
        childColumns = ["checklist_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CheckBoxTitle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "checklist_id")
    var checkListId: Long = 0,
    var text: String = "",
    var description: String = "",
    var checked: Boolean = false
) {

    init { cnt++ }
    companion object{ var cnt: Int = 0 }

    @Ignore
    val index = cnt

}

