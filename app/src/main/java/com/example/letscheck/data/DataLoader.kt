package com.example.letscheck.data

import com.example.letscheck.repositories.ChecklistRepository
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.data.classes.main.UserActivity
import com.example.letscheck.data.classes.main.UserEntity

// не актуально________________________
class DataLoader(val repository: ChecklistRepository) {

    // Загрузка данных по умолчанию
    suspend fun loadData() {

        println("DATA LOADING HAS STARTED")
        // создание пользователя (пока он не добавлен в базу, id = 0)
        val activityName = "Фитнес"
        // загрузка пользователя в базу (если он уже есть, запись будет проигнорирована)
        repository.addUserActivity(UserActivity(activityName = activityName))
        // получаем id пользователя из бд
        val userId = repository.getUserActivityByName(activityName)!!.id

        // создание имени первого заголовка
        val userEntityName = "Сумка для фитнеса"
        // загружаем entity в БД
        repository.addUserEntity(
            UserEntity(
            entityName = userEntityName,
            activityId = userId,
            image = R.drawable.fitness_bag_1
        )
        )

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

