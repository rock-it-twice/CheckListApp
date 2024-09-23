package com.example.letscheck.screens.addNewEntityScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.addNewEntityScreen.composables.AddNewChecklistButton
import com.example.letscheck.screens.addNewEntityScreen.composables.Header
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListTopBar
import com.example.letscheck.screens.addNewEntityScreen.composables.NewEntityRow
import com.example.letscheck.screens.addNewEntityScreen.composables.PhotoPicker
import com.example.letscheck.screens.addNewEntityScreen.composables.SaveResultToDataBaseButton
import com.example.letscheck.viewModels.AddNewEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNewEntityScreen(navController: NavController, vm: AddNewEntityViewModel, activityId: Long) {

    // Проверяем, принадлежит ли новый список текущему разделу (UserActivity),
    // на случай, если экран создания открывается не в первый раз.

    if (vm.checkNewEntityRelations(activityId))
        vm.createNewEntity( activityId = activityId, str = "" )

    val lazyListState = rememberLazyListState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val fabVisibility by remember {
        derivedStateOf {
            val lastVisibleIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleIndex == lazyListState.layoutInfo.totalItemsCount-1
        } }

    Scaffold(
        modifier  = Modifier.nestedScroll(topBarScrollBehavior.nestedScrollConnection),
        topBar    = { NewCheckListTopBar(vm, navController, topBarScrollBehavior) },
        content   = { innerPadding ->
            NewCheckListLazyColumn(vm = vm, lazyListState, innerPadding, topBarScrollBehavior)
                    },
        floatingActionButton = { SaveResultToDataBaseButton(vm, navController, fabVisibility) }
    )
}