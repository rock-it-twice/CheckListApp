package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.viewModels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseActivityColumn(
    vm: MainViewModel,
    navController: NavController,
    lazyGridState: LazyGridState,
    innerPadding: PaddingValues,
    topBarScrollBehavior: TopAppBarScrollBehavior
) {

    val modifier = Modifier
    var isVisible: Boolean by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val topPadding = innerPadding.calculateTopPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = topPadding, bottom = 40.dp, start = 10.dp, end = 10.dp)
    ) {
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
                AnimatedEntitiesGrid(vm, navController, lazyGridState, topBarScrollBehavior)
            })
    }
}