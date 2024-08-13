package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainGreenColor
import com.example.letscheck.ui.theme.MainWhiteColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun CheckBoxesColumn(vm: MainViewModel, index: Int){

    Column(modifier = Modifier.padding(start = 5.dp)) {
        vm.newCheckBoxes[index].forEach {
            Row(modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val textFieldColors = TextFieldDefaults.colors (
                    disabledContainerColor = Color.Transparent,
                    disabledTextColor = MainWhiteColor,
                    focusedTextColor = MainWhiteColor,
                    focusedContainerColor = SecondaryBackgroundColor,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedTextColor = MainWhiteColor
                )

                var newName: String by rememberSaveable {
                    mutableStateOf(if (it.text != "") it.text else "")
                }
                var isEnabled: Boolean by rememberSaveable { mutableStateOf(false) }
                var isChecked: Boolean by remember { mutableStateOf(false) }

                Checkbox(
                    modifier = Modifier
                        .size(8.dp)
                        .clickable(onClick = { isChecked = !isChecked }),
                    checked = isChecked,
                    onCheckedChange = {},
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = MainWhiteColor,
                        checkedColor = MainGreenColor
                    )
                )
                VerticalDivider(modifier = Modifier.width(10.dp))
                TextField(
                    modifier = Modifier.weight(1F),
                    value = newName,
                    onValueChange = { newName = it },
                    enabled = !isEnabled,
                    textStyle = EntityTypography.titleSmall,
                    colors = textFieldColors,
                    placeholder = { Text(text = "check me!") }
                )
                VerticalDivider(modifier = Modifier.width(5.dp))
                AcceptOrEditCheckBoxButton(
                    vm = vm,  newName = newName, index = index, checkBoxTitle = it,
                    isEnabled = isEnabled, onValueChange = { isEnabled = it } )
                VerticalDivider(modifier = Modifier.width(5.dp))
                DeleteCheckBoxButton( vm = vm, index = index, checkBoxTitle = it )
            }
        }
    }
}

@Composable
fun AcceptOrEditCheckBoxButton(
    vm: MainViewModel,
    newName: String,
    index: Int,
    checkBoxTitle: CheckBoxTitle,
    isEnabled: Boolean,
    onValueChange: (Boolean) -> Unit){
    if (!isEnabled){
        IconButton(
            onClick = {
                vm.renameNewCheckBoxTitle(
                    index = index, checkBoxId = checkBoxTitle.id, str = newName
                )
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

@Composable
fun DeleteCheckBoxButton(vm: MainViewModel, index: Int, checkBoxTitle: CheckBoxTitle){
    IconButton(
        onClick = { vm.deleteNewCheckBox(index, checkBoxTitle) },
        colors = onMainIconButtonColors) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
    }
}