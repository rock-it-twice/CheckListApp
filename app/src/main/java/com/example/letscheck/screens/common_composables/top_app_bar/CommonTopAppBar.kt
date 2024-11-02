package com.example.letscheck.screens.common_composables.top_app_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel
import com.example.letscheck.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    navController: NavController,
    mainVM: MainViewModel? = null,
    currentVM: CurrentEntityViewModel? = null,
    addNewVM: AddNewEntityViewModel? = null,
    settings: AppSettings,
    scrollBehavior: TopAppBarScrollBehavior
) {

    TopAppBar(
        title = { AppLogo() },
        navigationIcon = { GoBackIconButton(navController, mainVM, currentVM, addNewVM) },
        actions = { SettingsButton() },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )

}