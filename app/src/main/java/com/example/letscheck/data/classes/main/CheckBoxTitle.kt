package com.example.letscheck.data.classes.main

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "check_box_title",
    indices = [Index(value = ["checkListId", "id"], unique = true)],
    foreignKeys = [
        ForeignKey(
        entity = CheckList::class,
        parentColumns = ["id"],
        childColumns = ["checkListId"] )
    ]
)
data class CheckBoxTitle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = cnt,
    val checkListId: Int,
    var text: String,
    var description: String = ""
) {

    init { cnt++ }
    companion object{ var cnt = 0 }

}
