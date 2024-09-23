package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun AcceptRenameDeleteButton( vm: AddNewEntityViewModel,
                              newName: String,
                              listIndex: Int,
                              checkList: CheckList? = null,
                              checkBoxTitle: CheckBoxTitle? = null,
                              isEnabled: Boolean,
                              onValueChange: (Boolean) -> Unit
) {
    Row {
        // изменить запись
        AnimatedVisibility(visible = isEnabled) {
            IconButton(
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                onClick = { onValueChange(!isEnabled) },
                enabled = isEnabled,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) { Icon(
                imageVector = Icons.Default.Edit,
                modifier = Modifier.size(15.dp),
                contentDescription = "edit"
            ) }
        }
        // принять изменения либо удалить
        AnimatedVisibility(visible = !isEnabled) {
            Row {
                // Принять
                IconButton(
                    modifier = Modifier.size(width = 50.dp, height = 25.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        when(true){
                            (checkList != null) ->
                                vm.renameNewCheckList(checkList, newName)
                            (checkBoxTitle != null) ->
                                vm.renameNewCheckBoxTitle(listIndex, checkBoxTitle, newName)
                            else -> {}
                        }
                        onValueChange(!isEnabled)
                    },
                    enabled = !isEnabled
                ) { Icon(
                    imageVector = Icons.Default.Done,
                    modifier = Modifier.size(15.dp),
                    contentDescription = "accept"
                    )

                }
                Spacer(Modifier.size(10.dp))
                // Удалить
                IconButton(
                    modifier = Modifier.size(width = 50.dp, height = 25.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        when(true){
                            (checkList != null) ->
                                vm.deleteNewCheckList(checkList)
                            (checkBoxTitle != null) -> {
                                vm.deleteNewCheckBox(listIndex, checkBoxTitle)
                            }
                            else -> {}
                        }
                    }
                ) { Icon (
                    imageVector = Icons.Default.Delete,
                    modifier = Modifier.size(15.dp),
                    contentDescription = "delete"
                    )
                }
            }
        }
    }
}