package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.Folder
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun CurrentFolderName(folders: List<Folder>, id: Long?, onFolderChange: (Long?) -> Unit){

    var expanded by remember { mutableStateOf(false) }
    val noFolder = stringResource(id = R.string.new_folder_no_folder)
    var folderName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(bottom = 10.dp),
    ) {
        Text(stringResource(R.string.new_folder_title))
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

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            folders.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.folderName) },
                    onClick = {
                        folderName = it.folderName
                        onFolderChange(it.id)
                        expanded = false
                    }
                )
            }
        }
    }
}