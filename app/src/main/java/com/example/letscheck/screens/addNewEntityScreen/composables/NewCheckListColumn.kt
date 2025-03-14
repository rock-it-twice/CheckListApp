package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun NewCheckListColumn(vm: AddNewEntityViewModel, listIndex: Int){

    Column(modifier = Modifier.padding(start = 5.dp)) {
        vm.newCheckBoxes[listIndex].forEachIndexed { index, checkBoxTitle ->

            var newName: String by remember { mutableStateOf(checkBoxTitle.text) }
            var isEnabled: Boolean by remember { mutableStateOf(checkBoxTitle.text != "") }
            var isChecked: Boolean by remember { mutableStateOf(false) }

            val textFieldColors = TextFieldDefaults.colors (
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )

            Row(modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    modifier = Modifier
                        .size(5.dp)
                        .clickable(onClick = { isChecked = !isChecked }),
                    checked = isChecked,
                    onCheckedChange = { isChecked = !isChecked },
                    colors = CheckboxDefaults.colors(
                    )
                )

                VerticalDivider(modifier = Modifier.width(5.dp))

                TextField(
                    modifier = Modifier.weight(1F),
                    value = newName,
                    onValueChange = { newName = it },
                    enabled = !isEnabled,
                    colors = textFieldColors
                )

                VerticalDivider(modifier = Modifier.width(5.dp))

                AcceptRenameDeleteButton(newName = newName,
                    listIndex = listIndex, checkBoxTitle = checkBoxTitle,
                    isEnabled = isEnabled, onEnabledChange = { isEnabled = it })
            }
        }
    }
}
