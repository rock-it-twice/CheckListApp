package com.example.letscheck.data

import com.example.letscheck.ChecklistRepository
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.CheckList
import com.example.letscheck.data.classes.User
import com.example.letscheck.data.classes.UserEntity

class DataLoader(val repository: ChecklistRepository) {

    // Загрузка данных по умолчанию
    suspend fun loadData() {

        println("DATA LOADING HAS STARTED")
        // создание пользователя (пока он не добавлен в базу, id = 0)
        val userName = "Фитнес"
        // загрузка пользователя в базу (если он уже есть, запись будет проигнорирована)
        repository.addUser(User(userName = userName))
        // получаем id пользователя из бд
        val userId = repository.getUserByName(userName)!!.id

        // создание имени первого заголовка
        val userEntityName = "Сумка для фитнеса"
        // загружаем entity в БД
        repository.addUserEntity(UserEntity(entityName = userEntityName, userId = userId))

        // определение id списка
        val uEId = repository.getUserEntityByName(userEntityName, userId)!!.id

        // создание имен подсписков
        val checkListNames = listOf("Нижнее белье", "Одежда", "Гигиена", "Аксессуары")

        // добавляем в БД и присваиваем id
        checkListNames.forEach {
            repository.addCheckList(CheckList(entityId = uEId, checkListName = it))
        }
        // возвращаем из БД
        val checkLists = repository.getCheckListsByEntityId(uEId)

        val titles = listOf(
            listOf("Трусы", "Лиф", "Носки"),
            listOf("Кроссовки", "Лосины", "Майка", "Топ"),
            listOf("Сланцы", "Полотенце", "Гель для душа"),
            listOf("Браслет", "Часы", "Наушники", "Вода")
        )

        checkLists.forEach {
            val name = it.checkListName
            when (name) {
                "Нижнее белье" -> {
                    repository.addCheckBoxTitles(it.id, titles[0])
                }
                "Одежда" -> {
                    repository.addCheckBoxTitles(it.id, titles[1])
                }
                "Гигиена" -> {
                    repository.addCheckBoxTitles(it.id, titles[2])
                }
                "Аксессуары" -> {
                    repository.addCheckBoxTitles(it.id, titles[3])
                }
            }
        }


        println("DATA LOADING IS COMPLETED")
    }
}

