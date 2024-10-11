package com.example.letscheck.screens.currentEntityScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.currentEntityScreen.composables.CurrentEntityColumn
import com.example.letscheck.screens.currentEntityScreen.composables.CurrentEntityTopBar
import com.example.letscheck.screens.currentEntityScreen.composables.PopUpBox
import com.example.letscheck.viewModels.CurrentEntityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentEntityScreen(
    vm: CurrentEntityViewModel,
    navController: NavController,
    entityId: Long
){
    vm.getEntityId(entityId)
    vm.getJointEntityById()
    vm.getCheckedList()

    val lazyListState = rememberLazyListState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val progress by vm.progressObserver.observeAsState(listOf(false))
    var showPopUp by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(progress.all{ it }) {
        vm.vibrate(progress.all { it }, "long")
        showPopUp = progress.all{ it }
    }


    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp)
            .blur( if(showPopUp) 10.dp else 0.dp ),
        topBar    = { CurrentEntityTopBar(vm, navController, topBarScrollBehavior) },
        content   = { innerPadding ->
            // Отображение выбранного чеклиста
            CurrentEntityColumn(vm, lazyListState, innerPadding, topBarScrollBehavior, progress)
        }
    )
    // Меню перехода на главный экран, после завершения списка
    PopUpBox( showPopUp, navController) { showPopUp = it }
}

