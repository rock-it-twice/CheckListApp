package com.example.letscheck.data.classes.output

import androidx.room.ColumnInfo
import androidx.room.Embedded



data class FolderWithCount(
    @Embedded
    val folderId: Long?,
    @ColumnInfo("count")
    val count: Int
)
