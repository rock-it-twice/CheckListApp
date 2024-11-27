package com.example.letscheck.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.screens.FolderDispatcherScreen.FolderDispatcherScreen
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
            // При переходе с экрана AddNewEntityScreen на экран Home, очищаем addNewVM
            LaunchedEffect(key1 = Unit) { addNewVM.clearNewEntity() }
            MainScreen(vm = mainVM, navController = navController, settings = settings, onSettingsChange = onSettingsChange)
        }
        composable(route = Routes.CurrentEntityScreen.route){
            CurrentEntityScreen(vm = currentVM, navController = navController, entityId = mainVM.entityId, settings = settings, onSettingsChange = onSettingsChange)
        }
        composable( route = Routes.AddNewEntityScreen.route ){
            val folderId = mainVM.currentJointFolder?.folder?.id ?: 0L
            AddNewEntityScreen(
                folderId = folderId,
                entityId = mainVM.entityId,
                vm = addNewVM,
                navController = navController,
                settings = settings,
                onSettingsChange = onSettingsChange
            )
        }
        composable(
            route = "${Routes.FolderDispatcherScreen.route}/{previousRoute}",
            arguments = listOf(navArgument("previousRoute") { type = NavType.StringType })
        ){  backStackEntry ->

            // получаем путь к предыдущему экрану, чтобы возвращаться именно к нему
            val previousScreenRoute = backStackEntry.arguments?.getString("previousRoute")

            FolderDispatcherScreen(
                previousScreenRoute = previousScreenRoute,
                navController = navController,
                vm = addNewVM
            )

        }
    }
}