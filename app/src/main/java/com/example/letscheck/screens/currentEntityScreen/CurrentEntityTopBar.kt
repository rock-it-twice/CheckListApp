package com.example.letscheck.screens.currentEntityScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentEntityTopBar(
    vm: CurrentEntityViewModel,
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior){
    TopAppBar(
        title = { Header(navController, currentVM = vm) },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.fillMaxWidth()
    )
}