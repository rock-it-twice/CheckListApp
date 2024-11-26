package com.example.letscheck.screens.common_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.navigation.Routes
import com.example.letscheck.screens.addNewEntityScreen.composables.AcceptRenameDeleteButton

@Composable
fun PopUpBox(showPopUp: Boolean,
             size: DpSize,
             onPopUpClose: (Boolean) -> Unit,
             content: @Composable ()-> Unit
){
    AnimatedVisibility(
        visible = showPopUp,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.75f)
                .background(MaterialTheme.colorScheme.background)
                .shadow(elevation = 10.dp),
            contentAlignment = Alignment.Center
        ){
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(excludeFromSystemGesture = true),
                onDismissRequest = { onPopUpClose(!showPopUp) },
                content = {
                    Box(
                        modifier = Modifier
                            .animateEnterExit(scaleIn(), scaleOut())
                            .size(size)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center,
                        content = { content() }
                    )
                }
            )
        }
    }
}

@Composable
fun EditFoldersPopUp(
    folders:  List<Folder>,
    onAdd:    (String) -> Unit,
    onRename: (Long, String) -> Unit,
    onDelete: (Long) -> Unit
){

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    ).copy()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_edit_folders_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
//        // Сообщение
//        Text(
//            text = stringResource(R.string.pop_up_edit_folders_message),
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center
//        )
//        Spacer(Modifier.size(10.dp))
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    onRenameFolder  = { onRename(folder.id, it) },
                    onDeleteFolder  = { onDelete(it) }
                    )
            }
        }
        item{
            TextButton(
                onClick = { onAdd("Новая папка") },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(stringResource(R.string.pop_up_edit_folders_add))
            }
        }
    }
}

@Composable
fun DeleteWarningPopUp(
    onClose: (Boolean) -> Unit,
    onDelete: () -> Unit){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_delete_entity_warning_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Сообщение
        Text(
            text = stringResource(R.string.pop_up_delete_entity_warning_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(10.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { onDelete() ; onClose( false ) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_entity_warning_accept))
            }
            Button(
                onClick = { onClose( false ) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_entity_warning_decline))
            }
        }
        Spacer(Modifier.size(10.dp))

    }
}

@Composable
fun GoToMainScreenPopUp(navController: NavController, onClose: (Boolean) -> Unit){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок
            Text(
                text = stringResource(R.string.pop_up_complete_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(20.dp))
            // Сообщение
            Text(
                text = stringResource(R.string.pop_up_complete_message),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(10.dp))
            // Кнопки
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { navController.navigate(route = Routes.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(stringResource(R.string.pop_up_complete_accept))
                }
                Button(
                    onClick = { onClose( false ) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(stringResource(R.string.pop_up_complete_decline))
                }
            }
            Spacer(Modifier.size(10.dp))
        }
}

@Composable
fun NewEntityPopUp(navController: NavController, onAccept: () -> Unit, onDecline: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_new_entity_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Сообщение
        Text(
            text = stringResource(R.string.pop_up_new_entity_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(10.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    onAccept()
                    navController.navigate(route = Routes.AddNewEntityScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_new_entity_accept))
            }
            Button(
                onClick = {
                    onDecline()
                    navController.navigate(route = Routes.AddNewEntityScreen.route)
                          },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_new_entity_decline))
            }
        }
        Spacer(Modifier.size(10.dp))
    }

}

@Composable
fun DeleteCheckListPopUp(listIndex: Int, onAccept: (Int) -> Unit, onDecline: (Boolean) -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_delete_check_list_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    onAccept(listIndex)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_check_list_accept))
            }
            Button(
                onClick = {
                    onDecline(false)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_entity_warning_decline))
            }
        }
        Spacer(Modifier.size(10.dp))
    }

}

@Composable
fun ClosePopUpIconButton(showPopUp: Boolean, onClose: (Boolean) -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        // Закрыть окно
        IconButton(
            onClick = { onClose(!showPopUp) },
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