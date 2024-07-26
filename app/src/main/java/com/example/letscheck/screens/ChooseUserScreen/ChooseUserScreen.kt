package com.example.letscheck.screens.ChooseUserScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.letscheck.screens.ChooseUserScreen.composables.ActivityDropDownMenu
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.screens.ChooseUserScreen.composables.Header
import com.example.letscheck.screens.ChooseUserScreen.composables.AnimatedEntitiesGrid
import com.example.letscheck.screens.ChooseUserScreen.composables.CurrentEntityColumn



@Composable
fun ChooseUserScreen(navController: NavController, vm: MainViewModel = viewModel()) {

    val modifier = Modifier

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        // Логотип
        Header(vm = vm)
        // Выбор Пользователя
        ActivityDropDownMenu(vm = vm, navController = navController)
        // Строка со списком названий чеклистов
        AnimatedEntitiesGrid(vm = vm)
        // Отображение выбранного чеклиста
        CurrentEntityColumn(vm = vm)
    }
}
