package com.example.letscheck.screens.addNewEntityScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.addNewEntityScreen.composables.CurrentActivity
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.NewEntityRow
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun AddNewEntityScreen(navController: NavController, vm: MainViewModel) {

    // Проверяем, принадлежит ли новый список текущему разделу (UserActivity),
    // на случай, если экран открывается не в первый раз
    if (vm.checkNewEntityRelations()) vm.createNewEntity()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        Header(navController = navController, vm = vm)
        CurrentActivity(vm = vm)
        NewEntityRow(vm = vm)
        NewCheckListLazyColumn(vm = vm)
        }
}








