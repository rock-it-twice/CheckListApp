package com.example.letscheck.screens.currentEntityScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.currentEntityScreen.composables.CurrentEntityColumn
import com.example.letscheck.screens.currentEntityScreen.composables.CurrentEntityTopBar
import com.example.letscheck.viewModels.CurrentEntityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentEntityScreen(vm: CurrentEntityViewModel, navController: NavController, entityId: Long){

    vm.getJointEntityById(entityId)

    val lazyListState = rememberLazyListState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp),
        topBar    = { CurrentEntityTopBar(vm, navController, topBarScrollBehavior) },
        content   = { innerPadding ->
            // Отображение выбранного чеклиста
            CurrentEntityColumn(vm, navController, lazyListState, innerPadding, topBarScrollBehavior)
        }
    )
}

