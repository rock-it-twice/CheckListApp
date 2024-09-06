package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainWhiteColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun NewEntityRow(vm: MainViewModel){

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = MainBackgroundColor,
        disabledTextColor = MainWhiteColor,
        focusedTextColor = MainWhiteColor,
        focusedContainerColor = SecondaryBackgroundColor,
        unfocusedContainerColor = MainBackgroundColor,
        unfocusedTextColor = MainWhiteColor
    )

    var isEnabled by rememberSaveable { mutableStateOf(
        vm.newEntity!!.entity.entityName != ""
    ) }
    var newName by remember { mutableStateOf( vm.newEntity!!.entity.entityName ) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        TextField(
            value = newName,
            onValueChange = { newName = it },
            modifier = Modifier,
            enabled = !isEnabled,
            textStyle = EntityTypography.titleMedium,
            colors = textFieldColors,
            placeholder = {
                            if (vm.newEntity!!.entity.entityName == "") {
                                Text(stringResource(R.string.new_entity_name))
                            }
                          }
        )

        AcceptOrEditEntityButton( vm = vm, newName = newName,
            isEnabled = isEnabled, onValueChange = { isEnabled = it} )

    }

}

@Composable
fun AcceptOrEditEntityButton(
                       vm: MainViewModel,
                       newName: String,
                       isEnabled: Boolean,
                       onValueChange: (Boolean) -> Unit){
    Row {
        AnimatedVisibility(visible = !isEnabled ) {
            IconButton(
                onClick = {
                    vm.renameNewEntity( newName )
                    onValueChange(!isEnabled)
                },
                enabled = !isEnabled,
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                colors = onMainIconButtonColors
            ) {  Icon(
                imageVector = Icons.Default.Done,
                modifier = Modifier.size(15.dp),
                contentDescription = "create") }

        }
        AnimatedVisibility(visible = isEnabled) {
            IconButton(
                onClick = { onValueChange(!isEnabled) },
                enabled = isEnabled,
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                colors = onMainIconButtonColors
            ) { Icon(
                imageVector = Icons.Default.Edit,
                modifier = Modifier.size(15.dp),
                contentDescription = "edit")
            }
        }
    }
}