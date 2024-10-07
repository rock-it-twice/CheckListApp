package com.example.letscheck.screens.chooseUserActivityScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.chooseUserActivityScreen.composables.ChooseActivityColumn
import com.example.letscheck.screens.chooseUserActivityScreen.composables.ChooseActivityTopBar
import com.example.letscheck.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseUserActivityScreen(navController: NavController, vm: MainViewModel) {

    val lazyGridState = rememberLazyGridState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp),
        topBar    = { ChooseActivityTopBar(vm, navController, topBarScrollBehavior) },
        content   = { innerPadding ->
            ChooseActivityColumn(vm, navController, lazyGridState, innerPadding, topBarScrollBehavior)
        }
    )

}
