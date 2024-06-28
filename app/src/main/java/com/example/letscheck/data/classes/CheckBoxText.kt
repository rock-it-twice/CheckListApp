package com.example.letscheck.data.classes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "check_box_text",
    indices = [Index(value = ["checkListId", "str"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = CheckList::class,
        parentColumns = ["checkListId"],
        childColumns = ["checkListId"] )]
)
data class CheckBoxText(
    val checkListId: Int,
    @PrimaryKey
    val str: String
)
