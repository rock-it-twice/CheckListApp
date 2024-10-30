package com.example.letscheck.screens.mainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.screens.mainScreen.composables.ChooseActivityColumn
import com.example.letscheck.screens.common_composables.top_app_bar.CommonTopAppBar
import com.example.letscheck.screens.common_composables.DeleteWarning
import com.example.letscheck.screens.common_composables.PopUpBox
import com.example.letscheck.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, vm: MainViewModel) {

    val lazyGridState = rememberLazyGridState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var showPopUp by rememberSaveable { mutableStateOf(false) }
    var entityId by rememberSaveable { mutableLongStateOf(0L) }


    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp)
            .blur(if (showPopUp) 10.dp else 0.dp),
        topBar    = {
            CommonTopAppBar(
                mainVM = vm,
                navController = navController,
                scrollBehavior = topBarScrollBehavior)
                    },
        content   = { innerPadding ->
            ChooseActivityColumn(
                vm = vm, navController = navController, lazyGridState = lazyGridState,
                innerPadding = innerPadding, topBarScrollBehavior = topBarScrollBehavior,
                showPopUp = { showPopUp = it }, getEntityId = { entityId = it }
                )
        }
    )
    PopUpBox(
        showPopUp = showPopUp,
        size = DpSize(360.dp, 240.dp),
        onDismiss = { showPopUp = it },
        content = {
            DeleteWarning( onClick = { showPopUp = it },
            delete = { vm.deleteEntityById(entityId) ; vm.getJointUserActivityById() }
        ) }
    )

}
