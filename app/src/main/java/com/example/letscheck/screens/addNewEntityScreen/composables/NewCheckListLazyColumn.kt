package com.example.letscheck.screens.addNewEntityScreen.composables



import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.screens.common_composables.FolderMenu
import com.example.letscheck.screens.common_composables.PopUpBox
import com.example.letscheck.screens.common_composables.popups.DeleteCheckListPopUp
import com.example.letscheck.viewModels.AddNewEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCheckListLazyColumn(
    navController: NavController,
    vm: AddNewEntityViewModel,
    folders: List<Folder>,
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
) {

    val modifier = Modifier
    var entityName = vm.newEntity?.entityName ?: ""
    val folderId = vm.newEntity?.folderId

    LaunchedEffect(vm.newEntity?.entityName) {
        entityName = vm.newEntity?.entityName ?: ""
    }

    LazyColumn(
        modifier = modifier.nestedScroll(
            connection = topAppBarScrollBehavior.nestedScrollConnection
        ),
        state = lazyListState,
        contentPadding = innerPadding
    ) {
        item { PhotoPicker(vm.newImageUri) { vm.addNewImageUri(uri = it) } }
        item { FolderMenu(
            navController = navController,
            folders = folders,
            id = folderId,
            onFolderChange = { vm.changeFolder(it) }
            )
        }
        item { NewTitleRow(entityName) { vm.renameNewEntity(it) } }
        vm.newChecklists.forEachIndexed { listIndex, checkList ->
            // Новый раздел
            item(checkList.index + 1000) {

                var showPopUp by rememberSaveable { mutableStateOf(false) }

                Box(
                    modifier = modifier
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .animateContentSize()
                        .animateItem()
                        .blur( if (showPopUp) 10.dp else 0.dp ),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        NewSubtitleRow(
                            modifier = modifier.animateItem(),
                            index = listIndex,
                            checkList = checkList,
                            onRename = { i, name -> vm.renameNewCheckList(i, name) }
                        )
                        vm.newCheckBoxes[listIndex].forEachIndexed { _, it ->
                            NewCheckBoxRow(
                                modifier  = modifier.animateItem(),
                                listIndex = listIndex,
                                checkBoxTitle = it,
                                onRename = { i, item, name ->  vm.renameCheckBox(i, item, name) },
                                onDelete = { i, item -> vm.deleteCheckBox(i, item) }
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment     = Alignment.CenterVertically
                        ) {
                            DeleteCheckListButton(showPopUp) { showPopUp = it }
                            AddNewCheckBoxButton(listIndex) { vm.addNewCheckBox(it) }
                        }
                    }
                    PopUpBox(
                        showPopUp = showPopUp,
                        size = DpSize(300.dp, 200.dp) ,
                        onPopUpClose = { showPopUp = it }
                    ) {
                        DeleteCheckListPopUp(
                            listIndex = listIndex,
                            onAccept  = { vm.deleteNewCheckList(it) },
                            onDecline = { showPopUp = it }
                        )
                    }
                }
            }
        }
        // создание нового раздела
        item { AddNewChecklistButton() { vm.addNewCheckList() } }
        item { Spacer(Modifier.size(80.dp)) }
    }
}






