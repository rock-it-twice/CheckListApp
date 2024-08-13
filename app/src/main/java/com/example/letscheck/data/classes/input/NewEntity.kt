package com.example.letscheck.data.classes.input

import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.UserEntity

data class NewEntity(
    val entity: UserEntity,
    val checkLists: MutableList<CheckList> = mutableListOf(),
    val checkBoxTitles: MutableList<List<CheckBoxTitle>> = mutableListOf()
) {

    fun renameEntity(str: String){
        entity.entityName = str
    }

    fun addCheckList() {
        checkLists.add(CheckList(entityId = entity.id))
        checkBoxTitles.add(listOf())
    }
    
    fun renameCheckList(index: Int, str: String) {
        checkLists[index] = CheckList(entityId = entity.id, checkListName = str)
    }
    fun renameCheckList(checkList: CheckList, str: String) {
        checkLists.find { checkList == it }!!.checkListName = str
    }

    fun deleteCheckList(index: Int) {
        checkLists.removeAt(index)
        checkBoxTitles.removeAt(index)
    }
    fun deleteCheckList(checkList: CheckList) {
        val index: Int = checkLists.indexOf(checkList)
        checkLists.remove(checkList)
        checkBoxTitles.removeAt(index)
    }

    fun addCheckBoxTitle(index: Int, str: String) {
        val checkListId = checkLists[index].id
        val mutableList = checkBoxTitles[index].toMutableList()
        mutableList.add(CheckBoxTitle(checkListId= checkListId, text = str))
        checkBoxTitles[index] = mutableList.toList()
    }
    
    fun renameCheckBoxTitle(index: Int, checkBoxId: Int, str: String) {
        val checkListId = checkLists[index].id
        checkBoxTitles[index].find { it.id == checkBoxId }!!.text = str
    }
    fun renameCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle, str: String) {
        val checkListId = checkLists[index].id
        checkBoxTitles[index].find { it == checkBoxTitle }!!.text = str
    }

    fun deleteCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle ) {
        val mutableList = checkBoxTitles[index].toMutableList()
        mutableList.remove(checkBoxTitle)
        checkBoxTitles[index] = mutableList.toList()
    }

}