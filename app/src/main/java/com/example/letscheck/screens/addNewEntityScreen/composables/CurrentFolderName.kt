package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun CurrentFolderName(vm: MainViewModel){
    val currentFolder = vm.currentJointFolder!!.folder
    Text(
        modifier = Modifier.padding(vertical = 21.dp),
        text = currentFolder.folderName,
    )
}