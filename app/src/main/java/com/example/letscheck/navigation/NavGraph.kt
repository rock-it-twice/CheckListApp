package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.screens.CheckListScreen
import com.example.letscheck.screens.ChooseEntityScreen
import com.example.letscheck.screens.ChooseUserScreen
import com.example.letscheck.screens.CreateUserScreen

@Composable
fun NavGraph(
    navController: NavHostController, vm: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ChooseUserScreen.route
    ) {
        composable(route = Screens.Home.route){
            ChooseEntityScreen(navController = navController)
        }
        composable(route = Screens.CheckListScreen.route){
            CheckListScreen(navController = navController)
        }
        composable(route = Screens.CreateUserScreen.route){
            CreateUserScreen(navController = navController, vm = vm)
        }
        composable(route = Screens.ChooseUserScreen.route){
            ChooseUserScreen(navController = navController, vm = vm)
        }

    }
}