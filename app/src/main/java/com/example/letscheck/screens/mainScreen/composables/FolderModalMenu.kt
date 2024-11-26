package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.screens.addNewEntityScreen.composables.AcceptRenameDeleteButton
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FolderModalMenu(vm: MainViewModel,
                    scope: CoroutineScope,
                    drawerState: DrawerState,
                    isVisible: Boolean,
                    onValueChange: (Boolean) -> Unit
) {

    if (drawerState.isClosed) onValueChange(false)
    val folders by vm.folders.observeAsState(listOf())
    val state = rememberScrollState()

    ModalDrawerSheet(
        modifier = Modifier,
        windowInsets = WindowInsets(0.dp),
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .verticalScroll(state)
        ) {
            folders.forEach { folder -> FolderRow(
                scope = scope,
                folder = folder,
                drawerState = drawerState,
                onChooseFolder = {
                    vm.getFolderId(it)
                    vm.getJointFolderById()
                },
                onRenameFolder = { vm.renameFolder(it, folder) },
                onDeleteFolder = { vm.deleteFolder(it) }
            ) }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable(onClick = { onValueChange(!isVisible) }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.add_new_folder),
                    fontSize = 18.sp
                )
                when(!isVisible){
                    true -> Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "show",
                    )
                    false -> Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "hide",
                    )
                }
            }
            AddNewFolder(
                visible = isVisible,
                onValueChange  = { onValueChange(!isVisible) },
                onAddNewFolder = { vm.addFolder(it) }
            )
        }

    }
}

@Composable
fun FolderRow(scope: CoroutineScope,
              folder: Folder,
              drawerState: DrawerState,
              onChooseFolder: (Long)   -> Unit,
              onRenameFolder: (String) -> Unit,
              onDeleteFolder: (Long)   -> Unit
) {

    var isEnabled  by remember { mutableStateOf(true) }
    var folderName by remember { mutableStateOf(folder.folderName) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = isEnabled) {
            Text(
                text = folderName,
                fontSize = 18.sp,
                modifier = Modifier.clickable(onClick = {
                    onChooseFolder(folder.id)
                    scope.launch { drawerState.apply { close() } }
                    }
                )
            )
        }
        AnimatedVisibility(visible = !isEnabled) {
            TextField(
                modifier = Modifier.width(200.dp),
                value = folderName,
                onValueChange = { folderName = it }
            )
        }
        AcceptRenameDeleteButton(
            newName   = folderName,
            folderId  = folder.id,
            isEnabled = isEnabled,
            onEnabledChange = { isEnabled = it },
            onRenameFolder  = { onRenameFolder(it) },
            onDeleteFolder  = { onDeleteFolder(it) }
        )
    }
}