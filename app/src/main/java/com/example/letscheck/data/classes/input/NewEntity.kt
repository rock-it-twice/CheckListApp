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

    fun addCheckList(str: String) {
        checkLists.add(CheckList(entityId = entity.id, checkListName = str))
        checkBoxTitles.add(listOf())
    }
    
    fun renameCheckList(index: Int, str: String) {
        checkLists[index] = CheckList(entityId = entity.id, checkListName = str)
    }

    fun renameCheckList(checkList: CheckList, str: String) {
        checkLists.find { checkList == it }!!.checkListName = str
    }

    fun deleteCheckList(checkList: CheckList) {
        val index: Int = checkLists.indexOf(checkList)
        checkBoxTitles.removeAt(index)
        checkLists.remove(checkList)

    }


    fun addCheckBoxTitle(index: Int, str: String) {
        val checkListId = checkLists[index].id
        val mutableList = checkBoxTitles[index].toMutableList()
        mutableList.add(CheckBoxTitle(checkListId= checkListId, text = str))
        checkBoxTitles[index] = mutableList.toList()
    }
    
    fun renameCheckBoxTitle(index: Int, checkBoxId: Long, str: String) {
        checkBoxTitles[index].find { it.id == checkBoxId }!!.text = str
    }

    fun renameCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle, str: String) {
        val mutableList = checkBoxTitles[index]
        mutableList.find { it == checkBoxTitle }!!.text = str
        checkBoxTitles[index] = mutableList.toList()
    }

    fun renameCheckBoxTitle(checkBoxTitle: CheckBoxTitle, str: String) {
        checkBoxTitles.flatten().find { it == checkBoxTitle }!!.text = str
    }

    fun deleteCheckBoxTitle(index: Int, checkBoxTitle: CheckBoxTitle){
        val mutableList = checkBoxTitles[index].toMutableList()
        mutableList.remove(checkBoxTitle)
        checkBoxTitles[index] = mutableList.toList()
    }

    fun deleteCheckBoxTitleByIndex(listIndex: Int, index: Int){
        val mutableList = checkBoxTitles[listIndex].toMutableList()
        mutableList.removeAt(index)
        checkBoxTitles[listIndex] = mutableList.toList()
    }

    fun deleteCheckBoxTitle(checkBoxTitle: CheckBoxTitle) {
        checkBoxTitles.flatten().toMutableList().remove(checkBoxTitle)
    }

}