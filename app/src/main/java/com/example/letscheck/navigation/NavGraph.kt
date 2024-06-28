package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.CheckListScreen
import com.example.letscheck.ChooseEntityScreen
import com.example.letscheck.data.Dao

@Composable
fun NavGraph(userDao: Dao, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route){
            ChooseEntityScreen(userDao = userDao, navController = navController)
        }
        composable(route = Screens.CheckListScreen.route){
            CheckListScreen(userDao = userDao, currentEntity = 0, navController = navController)
        }

    }
}