package com.example.letscheck.data.classes.output

import androidx.room.Embedded
import androidx.room.Relation
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList


// Объединенный класс
data class JointCheckList(

    @Embedded
    val checkList: CheckList,

    @Relation(parentColumn = "id", entityColumn = "checklist_id")
    val checkBoxTitles: MutableList<CheckBoxTitle>

)