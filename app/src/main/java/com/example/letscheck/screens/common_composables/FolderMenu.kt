package com.example.letscheck.screens.common_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.navigation.Routes

@Composable
fun FolderMenu(
    navController: NavController,
    folders: List<Folder>,
    id: Long?,
    onFolderChange: (Long?) -> Unit
){

    var expanded by remember { mutableStateOf(false) }
    val noFolder = stringResource(id = R.string.folder_no_folder)
    val allFolders = stringResource(id = R.string.folder_all)
    var folderName by remember { mutableStateOf("") }
    val route = navController.currentDestination!!.route

    Column(
        modifier = Modifier.padding(bottom = 10.dp),
    ) {
        // Заголовок
        Text(stringResource(R.string.folder_title))
        // Текущая папка
        TextButton(
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            onClick = { expanded = true }
        ) {
            Text( text =
            if (folderName == "") folders.find { it.id == id }?.folderName ?: noFolder
            else folderName
            )
        }
        // Выпадающее меню
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Все списки
            if (route == Routes.Home.route){
                DropdownMenuItem(
                    text    = { Text(text = allFolders) },
                    onClick = { folderName = allFolders
                                onFolderChange(0)
                                expanded = false
                    }
                )
            }
            // Без папки
            DropdownMenuItem(
                text    = { Text(text = noFolder) },
                onClick = { folderName = noFolder
                            onFolderChange(null)
                            expanded = false
                }
            )
            // Список папок
            folders.forEach {
                DropdownMenuItem(
                    text    = { Text(text = it.folderName) },
                    onClick = {
                        folderName = it.folderName
                        onFolderChange(it.id)
                        expanded = false
                    }
                )
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            // Кнопка перехода на экран управления папками
            DropdownMenuItem(
                text    = { Text(stringResource(R.string.folder_edit)) },
                onClick = {
                    navController.navigate(
                        route = "${Routes.FolderDispatcherScreen.route}/${route}"
                    )
                }
            )
        }
    }
}