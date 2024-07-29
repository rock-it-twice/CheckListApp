package com.example.letscheck.screens.ChooseUserScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.letscheck.screens.ChooseUserScreen.composables.ActivityDropDownMenu
import com.example.letscheck.screens.ChooseUserScreen.composables.ActivityModalMenu
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.screens.ChooseUserScreen.composables.Header
import com.example.letscheck.screens.ChooseUserScreen.composables.AnimatedEntitiesGrid
import com.example.letscheck.screens.ChooseUserScreen.composables.CurrentEntityColumn
import com.example.letscheck.screens.ChooseUserScreen.composables.MenuButton


@Composable
fun ChooseUserScreen(navController: NavController, vm: MainViewModel = viewModel()) {

    val modifier = Modifier
    val activities by vm.userActivities.observeAsState(listOf())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isVisible: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        // Логотип
        Header(vm = vm)
        // Выбор Activity
        MenuButton(vm = vm, scope = scope, drawerState = drawerState)
        // Выбор Пользователя
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ActivityModalMenu(
                    vm = vm,
                    scope = scope,
                    drawerState = drawerState,
                    isVisible = isVisible,
                    onValueChange = { isVisible = it })
                            },
            content = {
                // Строка со списком названий чеклистов
                AnimatedEntitiesGrid(vm = vm, navController = navController)
                // Отображение выбранного чеклиста
                CurrentEntityColumn(vm = vm)
            })
//        ActivityDropDownMenu(vm = vm, navController = navController)
    }
}
