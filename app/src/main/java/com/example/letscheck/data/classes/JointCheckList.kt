package com.example.letscheck.data.classes

import androidx.room.Embedded
import androidx.room.Relation


data class JointCheckList(
                            @Embedded
                            var checkList: CheckList?,

                            @Relation(
                                parentColumn = "id",
                                entityColumn = "checkListId"
                            )
                            var checkBoxTitles: List<CheckBoxTitle>
)