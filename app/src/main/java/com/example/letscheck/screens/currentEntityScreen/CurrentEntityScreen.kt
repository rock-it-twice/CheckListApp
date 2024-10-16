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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.common_composables.top_app_bar.CommonTopAppBar
import com.example.letscheck.screens.currentEntityScreen.composables.CurrentEntityColumn
import com.example.letscheck.screens.common_composables.GoToMainScreenBox
import com.example.letscheck.screens.common_composables.PopUpBox
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

    // Когда все чекбоксы отмечены, вызывает срабатывание вибрации и открытия PopUp окна.
    LaunchedEffect(progress.all{ it }) {
        vm.vibrate(progress.all { it }, "long")
        showPopUp = progress.all{ it }
    }


    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp)
            .blur(if (showPopUp) 10.dp else 0.dp),
        topBar    = {
            CommonTopAppBar(
                currentVM = vm,
                navController = navController,
                scrollBehavior = topBarScrollBehavior)
        },
        content   = { innerPadding ->
            // Отображение выбранного чеклиста
            CurrentEntityColumn(vm, lazyListState, innerPadding, topBarScrollBehavior, progress)
        }
    )
    // PopUp окно перехода на главный экран, после завершения списка
    PopUpBox(
        size = DpSize(360.dp, 240.dp),
        showPopUp = showPopUp,
        onDismiss = { showPopUp = it },
        content = { GoToMainScreenBox(navController) { showPopUp = it } }
    )
}

