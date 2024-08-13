package com.example.letscheck.screens.addNewEntityScreen.composables

import android.widget.ToggleButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainWhiteColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.TertiaryBackgroundColor
import com.example.letscheck.ui.theme.onMainAddCheckboxButtonColors
import com.example.letscheck.ui.theme.onMainButtonColors
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NewCheckListLazyColumn(vm: MainViewModel ) {
    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 20.dp))
        {
            items(
                items = vm.newChecklists, key = { it.id }
            ) {
                Surface(
                    modifier = Modifier.padding(bottom = 10.dp),
                    shape = RoundedCornerShape(4.dp),
                    color = TertiaryBackgroundColor
                ) {
                    Column(modifier = Modifier.padding(all = 10.dp)){
                        val index = vm.newChecklists.indexOf(it)
                        NewCheckListRow(vm = vm, checkList = it)
                        CheckBoxesColumn(vm = vm, index = index)
                        AddCheckBoxButton(vm = vm, index = index)
                    }
                }
            }
            item { AddNewEmptyChecklistButton(vm = vm) }

        }
    }
}

@Composable
fun NewCheckListRow(vm: MainViewModel, checkList: CheckList) {
    val standardName = "Новый подзаголовок"
    if (checkList.checkListName == "") {vm.renameNewCheckList(checkList, standardName)}

    Column {
        val textFieldColors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            disabledTextColor = MainWhiteColor,
            focusedTextColor = MainWhiteColor,
            focusedContainerColor = SecondaryBackgroundColor,
            unfocusedContainerColor = Color.Transparent,
            unfocusedTextColor = MainWhiteColor
        )
        var isEnabled by rememberSaveable { mutableStateOf(
            checkList.checkListName != ""
        ) }
        val name: String by remember { mutableStateOf(checkList.checkListName) }
        var newName: String by remember { mutableStateOf(name) }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextField(
                value = newName,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                enabled = !isEnabled,
                onValueChange = { newName = it },
                textStyle = EntityTypography.titleSmall,
                colors = textFieldColors,
            )
            VerticalDivider(modifier = Modifier.size(10.dp), color = Color.Transparent)
            AcceptRenameDeleteButton(vm = vm, newName = newName, checkList = checkList,
                isEnabled = isEnabled, onValueChange = { isEnabled = it })
        }
    }
}

@Composable
fun AcceptRenameDeleteButton(vm: MainViewModel, newName: String, checkList: CheckList,
                             isEnabled: Boolean, onValueChange: (Boolean) -> Unit ){
    Row {
        AnimatedVisibility(visible = !isEnabled) {
            Row {
                IconButton(
                    modifier = Modifier.width(60.dp),
                    onClick = {
                    vm.renameNewCheckList( checkList, newName )
                    onValueChange(!isEnabled)
                },
                    enabled = !isEnabled,
                    colors = onMainIconButtonColors
                ) { Icon( imageVector = Icons.Default.Done, contentDescription = "accept") }
                VerticalDivider(modifier = Modifier.size(10.dp), color = Color.Transparent)
                IconButton(
                    modifier = Modifier.width(60.dp),
                    onClick = { vm.deleteNewCheckList(checkList) },
                    colors = onMainIconButtonColors
                ) { Icon(imageVector = Icons.Default.Delete, contentDescription = "delete") }
            }
        }

        AnimatedVisibility(visible = isEnabled) {
            IconButton(
                modifier = Modifier.width(60.dp),
                onClick = { onValueChange(!isEnabled) },
                enabled = isEnabled,
                colors = onMainIconButtonColors
            ) { Icon( imageVector = Icons.Default.Edit, contentDescription = "edit" ) }
        }
    }
}

@Composable
fun AddNewEmptyChecklistButton(vm: MainViewModel){
        Button(
            onClick = { vm.addNewCheckList() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = onMainButtonColors
        ) {
            Text(
                text = stringResource(id = R.string.add_new_subtitle),
            )
        }
}

@Composable
fun AddCheckBoxButton(vm: MainViewModel, index: Int) {
    Button(
        onClick = { vm.addNewCheckBox(index) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = onMainAddCheckboxButtonColors
    ) {
        VerticalDivider(thickness = 10.dp, color = Color.Transparent)
        Text(text = stringResource(id = R.string.add_new_checkbox))
    }
}

