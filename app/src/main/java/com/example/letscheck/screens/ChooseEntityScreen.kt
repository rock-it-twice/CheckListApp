package com.example.letscheck.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun ChooseEntityScreen(navController: NavController) {}
//    val user = userDao.getUserById(0)
//    val userId: Int = user.userId
//    val userEntity = userDao.getUserEntities(userId)
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Header()
//
//        Spacer( modifier = Modifier.size(20.dp) )
//
//        LazyRow {
//            items(userEntity.size) { item ->
//                userEntity.forEach { v ->
//                    Button(
//                        modifier = Modifier.padding(horizontal = 10.dp),
//                        onClick = {
//                            navController.navigate(Screens.CheckListScreen.route)
//                        },
//                        enabled = true
//                    ) {
//                        Text(text = v.entityName)
//                    }
//
//                }
//            }
//        }
//
//        Spacer( modifier = Modifier.size(10.dp) )
//
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 10.dp),
//            onClick = {
//                /* TODO заменить на navController.navigate.(Screens.Имя_экрана.route) */
//            },
//            enabled = true
//        ) {
//            Text(text = "+ create new checklist")
//        }
//
//    }
//}