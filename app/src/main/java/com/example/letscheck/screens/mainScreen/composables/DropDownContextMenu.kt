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
import com.example.letscheck.R


@Composable
fun DropDownContextMenu(size: DpSize,
                        entityId: Long,
                        isExpanded: Boolean,
                        isResetEnabled: Boolean,
                        resetProgress: (Long) -> Unit,
                        onExpandedChange: (Boolean) -> Unit,
                        showPopUp: (Boolean) -> Unit,
                        getEntityId: (Long) -> Unit,
                        navigateToEditScreen: () -> Unit
){
    DropdownMenu(
        expanded         = isExpanded,
        onDismissRequest = { onExpandedChange(!isExpanded) },
        offset           = DpOffset(size.width/2, (-size.height)/2),
        modifier         = Modifier,
    ) {
        // Сброс чекбоксов
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.entity_reset)) },
            onClick      = {
                resetProgress(entityId)
                onExpandedChange(!isExpanded)
            },
            enabled      = isResetEnabled,
            leadingIcon  = { Icon(Icons.Default.Refresh, stringResource(R.string.entity_reset)) }
        )
        // Редактирование
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.add_new_edit)) },
            onClick      = {
                getEntityId(entityId)
                navigateToEditScreen()
                onExpandedChange(!isExpanded)
            },
            leadingIcon  = { Icon(Icons.Default.Edit, stringResource(R.string.add_new_edit)) }
        )
        // Удаление
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.add_new_delete)) },
            onClick      = {
                showPopUp(true)
                getEntityId(entityId)
                onExpandedChange(!isExpanded)
            },
            leadingIcon  = { Icon(Icons.Default.Delete, stringResource(R.string.add_new_delete)) }
        )

    }
}
