package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.screens.chooseUserActivityScreen.ChooseUserActivityScreen
import com.example.letscheck.screens.addNewEntityScreen.AddNewEntityScreen
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NavGraph( vm: MainViewModel, navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route){
            ChooseUserActivityScreen(vm = vm, navController = navController)
        }
        composable(route = Routes.AddNewEntityScreen.route){
            AddNewEntityScreen(vm = vm, navController = navController)
        }
    }
}