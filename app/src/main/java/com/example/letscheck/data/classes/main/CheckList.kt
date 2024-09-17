package com.example.letscheck.data.classes.main

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "check_lists",
    indices = [Index(value = ["entityId", "id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["entityId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class CheckList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = cnt,
    var entityId: Long,
    var checkListName: String = ""
    ){

    init { cnt ++ }
    companion object { private var cnt = 0L }

}
