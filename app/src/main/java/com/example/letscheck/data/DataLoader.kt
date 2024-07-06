package com.example.letscheck.data

import com.example.letscheck.data.classes.CheckBoxText
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity


// Загрузка данных по-умолчанию
class DataLoader(userDao: Dao) {

    init {
        suspend fun loadData(){
            // создание пользователя
            val user = User(userName = "firstUser")
            // создание имени первого заголовка
            val userEntity = UserEntity(userId = user.userId, entityName = "Bag for fitness")
            // определение id списка
            val uEId = userEntity.entityId
            // создание имен подсписков
            val checklist1 = CheckList(entityId = uEId, checkListName = "Нижнее белье")
            val checklist2 = CheckList(entityId = uEId, checkListName = "Одежда")
            val checklist3 = CheckList(entityId = uEId, checkListName = "Гигиена")
            val checklist4 = CheckList(entityId = uEId, checkListName = "Аксессуары")

            val checkLists = listOf(checklist1, checklist2, checklist3, checklist4)

            // создание пустого списка названий чекбоксов
            val checkBoxes: MutableList<CheckBoxText> = mutableListOf()

            //
            fun addToCheckBoxList(pair: Pair<Int, List<String>>){
                val id = pair.first
                val text = pair.second
                text.forEach{
                    checkBoxes.add(CheckBoxText(checkListId = id, str = it))
                }
            }

            // заполняем список
            addToCheckBoxList (
                checklist1.checkListId to listOf("Трусы", "Лиф", "Носки")
            )
            addToCheckBoxList (
                checklist2.checkListId to listOf("Кроссовки", "Лосины", "Майка", "Топ")
            )
            addToCheckBoxList (
                    checklist3.checkListId to listOf("Сланцы", "Полотенце", "Гель для душа")
            )
            addToCheckBoxList (
                    checklist4.checkListId to listOf("Браслет", "Часы", "Наушники", "Вода")
            )


            // загрузка всего этого добра в БД
            userDao.addUser(user)
            userDao.insertUserEntity(userEntity)
            userDao.insertMultipleCheckLists(checkLists)
            userDao.insertMultipleCheckBoxTexts(checkBoxes)
        }
    }
}