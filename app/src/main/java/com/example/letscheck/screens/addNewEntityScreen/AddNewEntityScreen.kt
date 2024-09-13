package com.example.letscheck.screens.addNewEntityScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.NewEntityRow
import com.example.letscheck.screens.addNewEntityScreen.composables.PhotoPicker
import com.example.letscheck.viewModels.AddNewEntityViewModel

@Composable
fun AddNewEntityScreen(navController: NavController, vm: AddNewEntityViewModel, activityId: Int) {

    // Проверяем, принадлежит ли новый список текущему разделу (UserActivity),
    // на случай, если экран создания открывается не в первый раз.

    if (vm.checkNewEntityRelations(activityId))
        vm.createNewEntity(activityId = activityId,"")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 50.dp)
    ) {
//        Header(navController = navController, vm = vm)
//        CurrentActivityName(vm = vm)
        PhotoPicker(vm.currentImageUri) { vm.addNewCurrentImageUri(uri = it) }
        NewEntityRow(vm = vm)
        NewCheckListLazyColumn(vm = vm, navController = navController)
        }
}