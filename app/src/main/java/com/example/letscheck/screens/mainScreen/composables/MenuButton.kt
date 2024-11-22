package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MenuButton(vm: MainViewModel, scope: CoroutineScope, drawerState: DrawerState) {

        TextButton(
            onClick = {
                scope.launch { drawerState.apply { if (isClosed) open() else close() } }
            },
            modifier = Modifier.padding(10.dp).defaultMinSize(minWidth = 120.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = if (vm.currentJointFolder != null) {
                    vm.currentJointFolder!!.folder.folderName
                } else stringResource(id = R.string.choose_folder),
                color = MaterialTheme.colorScheme.onSurface,
            )

            if (drawerState.isClosed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "open menu"
                )

            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "close menu"
                )
            }
        }
}
