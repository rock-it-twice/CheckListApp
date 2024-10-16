package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.screens.chooseUserActivityScreen.MainScreen
import com.example.letscheck.screens.addNewEntityScreen.AddNewEntityScreen
import com.example.letscheck.screens.currentEntityScreen.CurrentEntityScreen
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NavGraph( mainVM: MainViewModel,
              currentVM: CurrentEntityViewModel,
              addNewVM: AddNewEntityViewModel,
              navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route){
            MainScreen(vm = mainVM, navController = navController)
        }
        composable(route = Routes.CurrentEntityScreen.route){
            CurrentEntityScreen(vm = currentVM, navController = navController, entityId = mainVM.entityId)
        }
        composable(route = Routes.AddNewEntityScreen.route){
            AddNewEntityScreen(vm = addNewVM,
                navController = navController,
                activityId = mainVM.currentJointUserActivity!!.userActivity.id
            )
        }
    }
}