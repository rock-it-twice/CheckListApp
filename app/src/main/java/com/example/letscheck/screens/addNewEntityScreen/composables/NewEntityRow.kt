package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun NewEntityRow(vm: MainViewModel){

    val activityId = vm.currentJointUserActivity!!.userActivity.id

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = MainBackgroundColor,
        disabledTextColor = MainTextColor,
        focusedTextColor = MainTextColor,
        focusedContainerColor = SecondaryBackgroundColor,
        unfocusedContainerColor = MainBackgroundColor,
        unfocusedTextColor = MainTextColor
    )
    var isEnabled by rememberSaveable { mutableStateOf(false) }
    var newName by rememberSaveable { mutableStateOf( vm.newEntity?.entityName ?: "") }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        TextField(
            value = newName,
            onValueChange = { newName = it },
            modifier = Modifier,
            enabled = !isEnabled,
            textStyle = EntityTypography.titleMedium,
            placeholder = { if (vm.newEntity == null) Text(text = "Придумайте имя списка") },
            colors = textFieldColors
        )

        CreateOrEditEntityButton(
            vm = vm,
            activityId = activityId,
            newName = newName,
            isEnabled = isEnabled,
            onValueChange = { isEnabled = it} )

    }

}

@Composable
fun CreateOrEditEntityButton(vm: MainViewModel,
                       activityId: Int,
                       newName: String,
                       isEnabled: Boolean,
                       onValueChange: (Boolean) -> Unit){
    if (!isEnabled){
        IconButton(
            onClick = {
                vm.createNewEntity(activityId = activityId, name = newName)
                onValueChange(!isEnabled)
            },
            enabled = !isEnabled,
            colors = onMainIconButtonColors
        ) {  Icon(imageVector = Icons.Default.Done, contentDescription = "create") }
    } else {
        IconButton(
            onClick = { onValueChange(!isEnabled) },
            enabled = isEnabled,
            colors = onMainIconButtonColors
        ) { Icon(imageVector = Icons.Default.Edit, contentDescription = "edit") }
    }
}