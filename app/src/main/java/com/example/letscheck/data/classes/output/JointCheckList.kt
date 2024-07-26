package com.example.letscheck.data.classes.output

import androidx.room.Embedded
import androidx.room.Relation
import com.example.letscheck.data.classes.input.CheckBoxTitle
import com.example.letscheck.data.classes.input.CheckList


// Объединенный класс
data class JointCheckList(

    @Embedded
    val checkList: CheckList,

    @Relation(parentColumn = "id", entityColumn = "checkListId")
    val checkBoxTitles: List<CheckBoxTitle>

)