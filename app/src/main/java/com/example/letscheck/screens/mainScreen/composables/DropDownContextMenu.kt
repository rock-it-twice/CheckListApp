package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun DropDownContextMenu(vm: MainViewModel,
                        navController: NavController,
                        isExpanded: Boolean,
                        size: DpSize,
                        entityId: Long,
                        progressObserver: List<Boolean>,
                        onValueChange: (Boolean) -> Unit,
                        showPopUp: (Boolean) -> Unit,
                        getEntityId: (Long) -> Unit
){
    DropdownMenu(
        expanded         = isExpanded,
        onDismissRequest = { onValueChange(!isExpanded) },
        offset           = DpOffset(size.width/2, (-size.height)/2),
        modifier         = Modifier,
    ) {
        // Сброс чекбоксов
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.entity_reset)) },
            onClick      = {
                vm.resetCheckBoxes(entityId)
                onValueChange(!isExpanded)
            },
            enabled      = progressObserver.any { it },
            leadingIcon  = { Icon(Icons.Default.Refresh, stringResource(R.string.entity_reset)) }
        )
        // Редактирование
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.edit)) },
            onClick      = {
                getEntityId(entityId)
                navController.navigate( route = Routes.AddNewEntityScreen.route )
                onValueChange(!isExpanded)
            },
            leadingIcon  = { Icon(Icons.Default.Edit, stringResource(R.string.edit)) }
        )
        // Удаление
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.delete)) },
            onClick      = {
                showPopUp(true)
                getEntityId(entityId)
                onValueChange(!isExpanded)
            },
            leadingIcon  = { Icon(Icons.Default.Delete, stringResource(R.string.delete)) }
        )

    }
}
