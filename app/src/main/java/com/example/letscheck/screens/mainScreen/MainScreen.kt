package com.example.letscheck.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.navigation.Routes
import com.example.letscheck.screens.common_composables.FolderMenu
import com.example.letscheck.screens.common_composables.PopUpBox
import com.example.letscheck.screens.common_composables.popups.DeleteWarningPopUp
import com.example.letscheck.screens.common_composables.top_app_bar.CommonTopAppBar
import com.example.letscheck.screens.mainScreen.composables.AnimatedEntitiesGrid
import com.example.letscheck.screens.mainScreen.composables.EmptyFolderSurface
import com.example.letscheck.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    vm: MainViewModel,
    settings: AppSettings,
    onSettingsChange: () -> Unit
    ) {

    val folders by vm.folders.observeAsState(listOf())
    val foldersAndCounts by vm.foldersAndCounts.observeAsState(emptyMap())
    val lazyGridState = rememberLazyGridState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var showPopUp by rememberSaveable { mutableStateOf(false) }
    var entityId by rememberSaveable { mutableLongStateOf(0L) }

    LaunchedEffect(vm.currentFolderId){
        vm.countEntities()
    }

    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp)
            .blur(if (showPopUp) 10.dp else 0.dp),
        topBar    = {
            CommonTopAppBar(
                navController = navController,
                mainVM   = vm,
                settings = settings,
                onSettingsChange = onSettingsChange,
                scrollBehavior   = topBarScrollBehavior,
            )
        },
        content   = {
            innerPadding ->
            val topPadding = innerPadding.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding, bottom = 40.dp, start = 10.dp, end = 10.dp)
            ) {
                FolderMenu(
                    navController = navController,
                    folders = folders,
                    foldersAndCounts = foldersAndCounts,
                    id = vm.currentFolderId,
                    onFolderChange = { vm.changeFolder(it) }
                )
                if (vm.counter == 0){
                    // Предложение создать первый список
                    EmptyFolderSurface { navController.navigate(Routes.AddNewEntityScreen.route) }
                } else {
                    // Сетка со списком чеклистов
                    AnimatedEntitiesGrid(
                        vm = vm,
                        navController = navController,
                        state = lazyGridState,
                        topBarScrollBehavior = topBarScrollBehavior,
                        showPopUp = { showPopUp = it },
                        getEntityId = { entityId = it ; vm.getEntityId(it) }
                    )
                }
            }
//            ChooseFolderColumn(
//                vm = vm,
//                navController = navController,
//                lazyGridState = lazyGridState,
//                innerPadding  = innerPadding,
//                topBarScrollBehavior = topBarScrollBehavior,
//                showPopUp   = { showPopUp = it },
//                getEntityId = { entityId = it
//                                vm.getEntityId(it)
//                }
//            )
        }
    )
    PopUpBox(
        showPopUp = showPopUp,
        size = DpSize(360.dp, 240.dp),
        onPopUpClose = { showPopUp = it },
        content = {
            DeleteWarningPopUp(
                onClose   = { showPopUp = it },
                onDelete  = {
                    vm.deleteEntityById(entityId)
                    vm.getJointFolderById()
                }
            )
        }
    )
}
