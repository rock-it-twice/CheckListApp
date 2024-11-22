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


@Composable
fun AcceptRenameDeleteButton(newName: String,
                             folderId: Long? = null,
                             listIndex: Int? = null,
                             checkList: CheckList? = null,
                             checkBoxTitle: CheckBoxTitle? = null,
                             isEnabled: Boolean,
                             onEnabledChange:   (Boolean) -> Unit,
                             onRenameFolder:    ( (String) -> Unit )? = null,
                             onDeleteFolder:    ( (Long) -> Unit )? = null,
                             onRenameCheckList: ( (Int, String) -> Unit )? = null,
                             onRenameCheckBox:  ( (Int, CheckBoxTitle, String ) -> Unit)? = null,
                             onDeleteCheckBox:  ( (Int, CheckBoxTitle) -> Unit )? = null
) {
    Row {
        // изменить запись
        AnimatedVisibility(visible = isEnabled) {
            IconButton(
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                onClick = { onEnabledChange(!isEnabled) },
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
                            (folderId != null) -> {
                                onRenameFolder!!(newName)
                            }
                            (checkList != null) -> {
                                onRenameCheckList!!(listIndex as Int, newName)
                            }
                            (checkBoxTitle != null) -> {
                                onRenameCheckBox!!(listIndex as Int, checkBoxTitle, newName)
                            }
                            else -> {}
                        }
                        onEnabledChange(!isEnabled)
                    },
                    enabled = !isEnabled
                ) { Icon(
                    imageVector = Icons.Default.Done,
                    modifier = Modifier.size(15.dp),
                    contentDescription = "accept"
                    )

                }
                Spacer(Modifier.size(10.dp))
                // Удалить или стереть
                IconButton(
                    modifier = Modifier.size(width = 50.dp, height = 25.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        when(true){
                            (folderId != null) -> {
                                onDeleteFolder!!(folderId as Long)
                            }
                            (checkList != null) -> {
                                onRenameCheckList!!(listIndex as Int, "")
                                onEnabledChange(!isEnabled)
                            }
                            (checkBoxTitle != null) -> {
                                onDeleteCheckBox!!(listIndex as Int, checkBoxTitle)
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