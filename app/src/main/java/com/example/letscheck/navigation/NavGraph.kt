package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letscheck.data.classes.main.AppSettings
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
              navController: NavHostController,
              settings: AppSettings,
              onSettingsChange: ()-> Unit) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        composable(route = Routes.Home.route){
            MainScreen(vm = mainVM, navController = navController, settings = settings, onSettingsChange = onSettingsChange)
        }
        composable(route = Routes.CurrentEntityScreen.route){
            CurrentEntityScreen(vm = currentVM, navController = navController, entityId = mainVM.entityId, settings = settings, onSettingsChange = onSettingsChange)
        }
        composable(route = Routes.AddNewEntityScreen.route){

            println("mainVM.entityId = ${ mainVM.entityId }")
            // передаем id списка в addNewVM
            addNewVM.getEntityId(mainVM.entityId)

            // Проверяем, создаётся новый список или редактируется уже существующий

            val folderId = mainVM.currentJointFolder?.folder?.id ?: 0L

            when(addNewVM.entityId > 0L) {
                true  -> { addNewVM.setCurrentEntityAsNew() }
                false -> { addNewVM.createNewEntity(folderId = folderId, str = "") }
            }

            AddNewEntityScreen(vm = addNewVM, navController = navController, settings = settings, onSettingsChange = onSettingsChange)
        }
    }
}