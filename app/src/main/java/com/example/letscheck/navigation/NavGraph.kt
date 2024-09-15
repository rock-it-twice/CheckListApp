package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.screens.chooseUserActivityScreen.ChooseUserActivityScreen
import com.example.letscheck.screens.addNewEntityScreen.AddNewEntityScreen
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NavGraph( mainVM: MainViewModel,
              addNewVM: AddNewEntityViewModel,
              navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route){
            ChooseUserActivityScreen(vm = mainVM, navController = navController)
        }
        composable(route = Routes.AddNewEntityScreen.route){
            AddNewEntityScreen(vm = addNewVM,
                navController = navController,
                activityId = mainVM.currentJointUserActivity!!.userActivity.id
            )
        }
    }
}