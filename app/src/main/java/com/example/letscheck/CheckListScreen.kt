package com.example.letscheck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.example.letscheck.data.Dao
import com.example.letscheck.navigation.Screens

@Composable
fun CheckListScreen(
    userDao: Dao, currentEntity: Int,
    navController: NavController) {
    val entityName = userDao.getUserEntity(currentEntity)
    val checkLists = userDao.getCheckList(currentEntity)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
                .padding(innerPadding)
        ) {
            // HEADER
            Header()

            Row(modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Вывод имени текущего списка (entity)
                Text(
                    text = entityName,
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.em
                )
                // Кнопка возврата к предыдущему экрану
                Button(
                    onClick = { navController.navigate(Screens.Home.route) },
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "go back")
                }

            }
            // Вывод имени подсписка
            checkLists.forEach { v ->
                Row(modifier = Modifier) {
                    Text(
                        text = v.checkListName,
                        modifier = Modifier,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 8.em
                    )
                }
                // Вывод элементов подсписка
                userDao.getCheckBoxTexts(v.checkListId)
                .forEach {
                    var isChecked by rememberSaveable { mutableStateOf(false) }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .clickable {
                            isChecked = !isChecked
                        }) {

                        Checkbox(
                            modifier = Modifier,
                            checked = isChecked,
                            onCheckedChange = null,

                            )
                        Text(
                            text = it.str,
                            fontSize = 4.5.em
                        )
                    }

                }
            }
        }
    }
}


//@Preview
//@Composable
//fun LoadCheckListPreview(){
//    user.entity.add(dataLoader.fitnessEntity)
//    CheckListScreen(user = user, currentEntity = 0, navController = rememberNavController())
//}