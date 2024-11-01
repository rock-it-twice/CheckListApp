package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.screens.mainScreen.MainScreen
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

            addNewVM.getEntityId(mainVM.entityId)
            mainVM.getEntityId(0L)

            // 1. Проверяем, принадлежит ли новый список текущему разделу (UserActivity),
            //    на случай, если экран создания открывается не в первый раз;
            // 2. Проверяем, создаётся ли новый список или редактируется уже существующий

            val activityId = mainVM.currentJointUserActivity!!.folder.id
            when {
                (addNewVM.entityId > 0L) -> {
                    addNewVM.setCurrentEntityAsNew()
                }

                (addNewVM.checkNewEntityRelations(activityId) && (addNewVM.entityId == 0L)) -> {
                    addNewVM.createNewEntity(activityId = activityId, str = "")
                }
            }

            AddNewEntityScreen(vm = addNewVM, navController = navController)
        }
    }
}