package com.example.letscheck.screens.addNewEntityScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.screens.addNewEntityScreen.composables.CurrentActivityName
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.NewEntityRow
import com.example.letscheck.screens.addNewEntityScreen.composables.PhotoPicker
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun AddNewEntityScreen(navController: NavController, vm: MainViewModel) {

    val newName = stringResource(id = R.string.new_entity_name)
    // Проверяем, принадлежит ли новый список текущему разделу (UserActivity),
    // на случай, если экран создания открывается не в первый раз.
    if ( vm.checkNewEntityRelations() ) vm.createNewEntity(newName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        Header(navController = navController, vm = vm)
        CurrentActivityName(vm = vm)
        PhotoPicker(vm.currentImageUri) { vm.addNewUri(uri = it) }
        NewEntityRow(vm = vm)
        NewCheckListLazyColumn(vm = vm)
        }
}