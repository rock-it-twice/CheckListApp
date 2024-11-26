package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.CheckBoxTitle


@Composable
fun NewCheckBoxRow(modifier: Modifier,
                   listIndex: Int,
                   checkBoxTitle: CheckBoxTitle,
                   onRename: (Int, CheckBoxTitle, String) -> Unit,
                   onDelete: (Int, CheckBoxTitle) -> Unit
) {

    var newName: String    by remember { mutableStateOf(checkBoxTitle.text) }
    var isEnabled: Boolean by remember { mutableStateOf(checkBoxTitle.text != "") }
    var isChecked: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(checkBoxTitle.text) { newName = checkBoxTitle.text }

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    ).copy()

    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier
                .size(5.dp)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .clickable(onClick = { isChecked = !isChecked }),
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked }
        )
        Spacer(Modifier.size(10.dp))
        TextField(
            modifier = Modifier
                .weight(1F)
                .clickable { isChecked = !isChecked },
            value = newName,
            onValueChange = { newName = it },
            enabled = !isEnabled,
            colors = textFieldColors,
            placeholder = { TextFieldPlaceholder( R.string.new_checkbox_name, isEnabled ) },
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(Modifier.size(10.dp))
        AcceptRenameDeleteButton(
            newName = newName,
            listIndex = listIndex,
            checkBoxTitle = checkBoxTitle,
            isEnabled = isEnabled,
            onEnabledChange = { isEnabled = it },
            onRenameCheckBox = { i, item, name -> onRename(i, item, name) },
            onDeleteCheckBox = { i, item -> onDelete( i, item) }
        )

    }
}