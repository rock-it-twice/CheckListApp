package com.example.letscheck.screens.FolderDispatcherScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.screens.addNewEntityScreen.composables.AcceptRenameDeleteButton
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun FolderDispatcherScreen(
    previousScreenRoute: String?,
    navController: NavController,
    vm: AddNewEntityViewModel
){

    val route = previousScreenRoute ?: "home"
    val folders by vm.folders.observeAsState( listOf() )

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    ).copy()

    Surface(
        Modifier.fillMaxSize().padding(vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Заголовок


    //        // Сообщение
    //        Text(
    //            text = stringResource(R.string.pop_up_edit_folders_message),
    //            style = MaterialTheme.typography.titleLarge,
    //            textAlign = TextAlign.Center
    //        )
    //        Spacer(Modifier.size(10.dp))
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item{ GoToPreviousScreenButton(navController, route) }
            item{ Text(
                text = stringResource(R.string.pop_up_edit_folders_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
                )
            }
            item{ Spacer(Modifier.size(20.dp)) }
            // поля
            items(folders){ folder ->

                var isEnabled by remember { mutableStateOf(false) }
                var name by remember { mutableStateOf(folder.folderName) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1F),
                        value = name,
                        enabled = isEnabled,
                        onValueChange = { name = it },
                        colors = textFieldColors
                    )
                    Spacer(Modifier.size(10.dp))
                    AcceptRenameDeleteButton(
                        newName = name,
                        folderId = folder.id,
                        isEnabled = isEnabled,
                        onEnabledChange = { isEnabled = it },
                        onRenameFolder  = { vm.renameFolder(folder.id, it) },
                        onDeleteFolder  = { vm.deleteFolder(it) }
                    )
                }
            }
            item{
                TextButton(
                    onClick = { vm.addFolder("Новая папка") },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(stringResource(R.string.pop_up_edit_folders_add))
                }
            }
        }
    }
}

@Composable
fun GoToPreviousScreenButton(navController: NavController, route: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        // Вернуться на предыдущий экран
        IconButton(
            onClick = { navController.navigate(route = route) },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.pop_up_edit_folders_close)
            )
        }
    }
}