package com.example.letscheck.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CheckListScreen(
    navController: NavController
) {}
//
//
//    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 15.dp)
//                .padding(innerPadding)
//        ) {
//            // HEADER
//            Header()
//
//            Row(modifier = Modifier,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Вывод имени текущего списка (entity)
//                Text(
//                    text = entityName,
//                    modifier = Modifier,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 10.em
//                )
//                // Кнопка возврата к предыдущему экрану
//                Button(
//                    onClick = { navController.navigate(Screens.Home.route) },
//                ) {
//                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "go back")
//                }
//
//            }
//            // Вывод имени подсписка
//            checkLists.forEach { v ->
//                Row(modifier = Modifier) {
//                    Text(
//                        text = v.checkListName,
//                        modifier = Modifier,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 8.em
//                    )
//                }
//                // Вывод элементов подсписка
//                userDao.getCheckBoxTexts(v.checkListId)
//                .forEach {
//                    var isChecked by rememberSaveable { mutableStateOf(false) }
//                    Row(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 5.dp)
//                        .clickable {
//                            isChecked = !isChecked
//                        }) {
//
//                        Checkbox(
//                            modifier = Modifier,
//                            checked = isChecked,
//                            onCheckedChange = null,
//
//                            )
//                        Text(
//                            text = it.str,
//                            fontSize = 4.5.em
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}


//@Preview
//@Composable
//fun LoadCheckListPreview(){
//    user.entity.add(dataLoader.fitnessEntity)
//    CheckListScreen(user = user, currentEntity = 0, navController = rememberNavController())
//}