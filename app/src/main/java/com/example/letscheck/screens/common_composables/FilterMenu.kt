package com.example.letscheck.screens.common_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.navigation.Routes

@Composable
fun FilterMenu(
    navController: NavController,
    folders: List<Folder>,
    foldersAndCounts: Map<Long?, Int>,
    id: Long?,
    onFolderChange: (Long?) -> Unit
){
    val route = navController.currentDestination!!.route
    var expanded by remember { mutableStateOf(false) }

    val noFolder    = stringResource(id = R.string.folder_no_folder)
    val allFolders  = stringResource(id = R.string.folder_all)
    val folderName  = when(id) {
                                null -> { noFolder }
                                0L   -> { allFolders }
                                else -> { folders.find { it.id == id }?.folderName ?: noFolder }
                                }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
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
                Text( text = folderName )
            }
        }

        // Выпадающее меню
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Все списки
            if (route == Routes.Home.route){
                DropdownMenuItem(
                    text    = { Text(text = "$allFolders (${foldersAndCounts.values.sum()})") },
                    onClick = { onFolderChange(0L)
                                expanded = false
                    }
                )
            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
            ){
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.folder_icon),
                            contentDescription = "folder_icon",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer( Modifier.size(10.dp) )
                        Text( stringResource(R.string.folder_folders) )
                        Spacer( Modifier.size(10.dp) )
                        }

                    // Кнопка перехода на экран управления папками
                    IconButton(
                        onClick = {
                            navController.navigate(
                                route = "${Routes.FolderDispatcherScreen.route}/${route}"
                            )
                            expanded = false
                        },
                        modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
                    ){
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit_icon",
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                HorizontalDivider(Modifier.fillMaxWidth())

                folders.forEach {
                    val count = foldersAndCounts[it.id] ?: 0
                    // Список папок
                    DropdownMenuItem(
                        text    = { Text(text = "${it.folderName} (${count})")},
                        onClick = {
                            onFolderChange(it.id)
                            expanded = false
                        }
                    )
                }
            }

            // Без папки
            DropdownMenuItem(
                text    = { Text(text = "$noFolder (${foldersAndCounts[null]})") },
                onClick = {
                    onFolderChange(null)
                    expanded = false
                }
            )
        }
    }
}