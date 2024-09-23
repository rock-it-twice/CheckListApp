package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.letscheck.viewModels.AddNewEntityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCheckListTopBar(vm: AddNewEntityViewModel,
                       navController: NavController,
                       scrollBehavior: TopAppBarScrollBehavior) {

    TopAppBar(
        title = { Header(navController, vm) },
        scrollBehavior = scrollBehavior
    )

}