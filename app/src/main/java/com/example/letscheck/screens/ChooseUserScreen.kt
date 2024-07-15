package com.example.letscheck.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.screens.composables.ChooseUserDropDownMenu
import com.example.letscheck.screens.composables.Header
import com.example.letscheck.screens.composables.AnimatedEntitiesRow
import com.example.letscheck.screens.composables.CurrentEntityColumn
import com.example.letscheck.screens.composables.isEntityListVisible


@Composable
fun ChooseUserScreen(navController: NavController, vm: CheckListViewModel = viewModel()) {

    val modifier = Modifier
    var isEntityListVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Логотип
        Header(vm = vm)
        // Выбор Пользователя
        ChooseUserDropDownMenu(vm = vm, navController = navController)
        // Проверка, можно ли показывать строку со списком чеклистов
        isEntityListVisible = isEntityListVisible(vm)
        // Строка со списком чеклистов
        AnimatedEntitiesRow(isVisible = isEntityListVisible, vm = vm)
        CurrentEntityColumn(vm = vm)


    }

}