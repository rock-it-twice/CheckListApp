package com.example.letscheck.screens.addNewEntityScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.SaveResultToDataBaseButton
import com.example.letscheck.screens.common_composables.top_app_bar.CommonTopAppBar
import com.example.letscheck.viewModels.AddNewEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewEntityScreen(
    folderId: Long?,
    entityId: Long,
    navController: NavController,
    vm: AddNewEntityViewModel,
    settings: AppSettings,
    onSettingsChange: () -> Unit
) {

    // Создание entity при запуске
    LaunchedEffect(key1 = Unit){
        // передаем id списка в addNewVM
        vm.getEntityId(entityId)

        when {
            (vm.entityId == 0L && vm.newEntity == null)
                -> { vm.createNewEntity(folderId = folderId, str = "") }

            (vm.entityId  > 0L && vm.newEntity == null)
                -> { vm.setCurrentEntityAsNew() }
        }
    }

    val folders by vm.folders.observeAsState(listOf())

    val lazyListState = rememberLazyListState()
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val fabVisibility by remember {
        derivedStateOf {
            val lastVisibleIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleIndex == lazyListState.layoutInfo.totalItemsCount-1
        }
    }

    Scaffold(
        modifier  = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
            .padding(10.dp),
        topBar    = {
            CommonTopAppBar(
                navController = navController,
                addNewVM = vm,
                settings = settings,
                onSettingsChange = onSettingsChange,
                scrollBehavior   = topBarScrollBehavior,
            )
        },
        content   = { innerPadding ->
            NewCheckListLazyColumn(
                navController = navController,
                vm = vm,
                folders = folders,
                lazyListState = lazyListState,
                innerPadding  = innerPadding,
                topAppBarScrollBehavior = topBarScrollBehavior
            )
                    },
        floatingActionButton = { SaveResultToDataBaseButton(vm, navController, fabVisibility) }
    )
//    PopUpBox(
//        showPopUp = showPopUp,
//        size = popUpSize,
//        onPopUpClose = { showPopUp = it }
//    ) {
//        EditFoldersPopUp(
//            folders  = folders,
//            onAdd    = { vm.addFolder(it) },
//            onRename = { id, name -> vm.renameFolder(id, name) },
//            onDelete = { vm.deleteFolder(it) }
//        )
//    }
}