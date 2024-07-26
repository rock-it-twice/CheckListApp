package com.example.letscheck.data.classes.input

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "check_box_title",
    indices = [Index(value = ["checkListId", "text"], unique = true)],
    foreignKeys = [
        ForeignKey(
        entity = CheckList::class,
        parentColumns = ["id"],
        childColumns = ["checkListId"] )
    ]
)
data class CheckBoxTitle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val checkListId: Int,
    val text: String,
    var description: String = ""
)