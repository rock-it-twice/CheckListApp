package com.example.letscheck.screens.chooseUserActivityScreen

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.letscheck.screens.chooseUserActivityScreen.composables.ActivityModalMenu
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.screens.chooseUserActivityScreen.composables.AnimatedEntitiesGrid
import com.example.letscheck.screens.chooseUserActivityScreen.composables.CurrentEntityColumn
import com.example.letscheck.screens.chooseUserActivityScreen.composables.MenuButton
import com.example.letscheck.screens.common_composables.Header
import kotlinx.coroutines.launch


@Composable
fun ChooseUserActivityScreen(navController: NavController, vm: MainViewModel) {

    val modifier = Modifier
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isVisible: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        Header(navController, mainVM = vm)
        // Выбор Activity
        MenuButton(vm = vm, scope = scope, drawerState = drawerState)
        // Боковое меню выбора
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
                // Сетка со списком чеклистов
                AnimatedEntitiesGrid(vm = vm, navController = navController)
                // Отображение выбранного чеклиста
                CurrentEntityColumn(vm = vm)
            })
    }
}
