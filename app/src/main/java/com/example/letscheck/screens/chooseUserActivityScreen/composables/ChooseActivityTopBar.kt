package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.viewModels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseActivityTopBar(
    vm: MainViewModel,
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
){
    TopAppBar(
        title = { Header(navController, mainVM = vm) },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.fillMaxWidth()
    )
}