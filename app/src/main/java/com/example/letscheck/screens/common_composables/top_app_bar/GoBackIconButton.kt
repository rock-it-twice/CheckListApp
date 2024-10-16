package com.example.letscheck.screens.common_composables.top_app_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun GoBackIconButton(
    navController: NavController,
    mainVM: MainViewModel? = null,
    currentVM: CurrentEntityViewModel? = null,
    addNewVM: AddNewEntityViewModel? = null
){
    when(navController.currentDestination?.route){
        Routes.Home.route                -> { GoBackIconButton(vm = mainVM) }
        Routes.CurrentEntityScreen.route -> { GoBackIconButton(vm = currentVM, navController) }
        Routes.AddNewEntityScreen.route  -> { GoBackIconButton(vm = addNewVM, navController) }
        else -> {}
    }
}


@Composable
private fun GoBackIconButton(vm: MainViewModel?){

    val visible = vm?.currentJointUserActivity != null

    AnimatedVisibility(
        visible = visible
    ) {
        IconButton(
            onClick = { vm?.clear() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "go back"
            )
        }
    }
}

@Composable
private fun GoBackIconButton(vm: CurrentEntityViewModel?, navController: NavController){

    IconButton(
        onClick = {
            navController.navigate(route = Routes.Home.route)
            vm?.clear()
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "go back"
        )
    }

}

@Composable
private fun GoBackIconButton(vm: AddNewEntityViewModel?, navController: NavController){

    IconButton(
        onClick = {
            navController.navigate(route = Routes.Home.route)
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "go back"
        )
    }
}