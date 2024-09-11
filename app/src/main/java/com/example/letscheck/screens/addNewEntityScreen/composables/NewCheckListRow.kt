package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.CheckList

import com.example.letscheck.viewModels.MainViewModel


@Composable
fun NewCheckListRow(vm: MainViewModel,
                    modifier: Modifier,
                    index: Int,
                    checkList: CheckList) {

    Column {
        val textFieldColors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,

        )
        var isEnabled by rememberSaveable { mutableStateOf( checkList.checkListName != "" ) }
        var newName: String by remember { mutableStateOf( checkList.checkListName ) }

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newName,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                enabled = !isEnabled,
                onValueChange = { newName = it },
                colors = textFieldColors,
                placeholder = { Text(stringResource(R.string.new_subtitle_name)) }
            )
            VerticalDivider(modifier = Modifier.size(10.dp), color = Color.Transparent)
            AcceptRenameDeleteButton(vm = vm, newName = newName, listIndex = index,
                checkList = checkList, isEnabled = isEnabled, onValueChange = { isEnabled = it })
        }
    }
}