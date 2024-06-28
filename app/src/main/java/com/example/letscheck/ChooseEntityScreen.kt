package com.example.letscheck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.data.Dao
import com.example.letscheck.navigation.Screens


@Composable
fun ChooseEntityScreen(userDao: Dao, navController: NavController) {
    val user = userDao.getUserById(0)
    val userId: Int = user.userId
    val userEntity = userDao.getUserEntities(userId)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Header()

        Spacer( modifier = Modifier.size(20.dp) )

        LazyRow {
            items(userEntity.size) { item ->
                userEntity.forEach { v ->
                    Button(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        onClick = {
                            navController.navigate(Screens.CheckListScreen.route)
                        },
                        enabled = true
                    ) {
                        Text(text = v.entityName)
                    }

                }
            }
        }

        Spacer( modifier = Modifier.size(10.dp) )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onClick = {
                /* TODO заменить на navController.navigate.(Screens.Имя_экрана.route) */
            },
            enabled = true
        ) {
            Text(text = "+ create new checklist")
        }

    }
}