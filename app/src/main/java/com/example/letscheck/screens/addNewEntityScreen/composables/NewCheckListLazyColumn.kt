package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.ThirdBackgroundColor
import com.example.letscheck.ui.theme.onMainButtonColors
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NewCheckListLazyColumn(vm: MainViewModel ) {
    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp))
        {
            items(
                items = vm.newChecklists,
                key = { it.id }
            ) { NewCheckListRow(vm = vm, checkList = it) }
            item { AddNewEmptyChecklistButton(vm = vm) }
        }
    }
}

@Composable
fun NewCheckListRow(vm: MainViewModel, checkList: CheckList) {
    val standardName = "Новый подзаголовок"
    if (checkList.checkListName == "") {vm.renameNewCheckList(checkList, standardName)}
    Surface(
        modifier = Modifier.padding(bottom = 5.dp),
        shape = RoundedCornerShape(4.dp),
        color = ThirdBackgroundColor
    ){
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            val textFieldColors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MainTextColor,
                focusedTextColor = MainTextColor,
                focusedContainerColor = SecondaryBackgroundColor,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = MainTextColor
            )
            var isEnabled by rememberSaveable { mutableStateOf(
                checkList.checkListName != ""
            ) }
            val name: String by remember { mutableStateOf(checkList.checkListName) }
            var newName: String by remember { mutableStateOf(name) }

            TextField(
                value = newName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                enabled = !isEnabled,
                onValueChange = { newName = it },
                textStyle = EntityTypography.titleSmall,
                colors = textFieldColors,
            )
            Row {
                AddAndDeleteButtons(vm = vm, newName = newName, checkList = checkList,
                    isEnabled = isEnabled, onValueChange = { isEnabled = it })
            }

        }
    }
}


@Composable
fun AddAndDeleteButtons(vm: MainViewModel, newName: String, checkList: CheckList,
                        isEnabled: Boolean, onValueChange: (Boolean) -> Unit ){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedVisibility(
            visible = !isEnabled
        ) {
            Button(onClick = {
                vm.renameNewCheckList( checkList, newName )
                onValueChange(!isEnabled) },
                enabled = !isEnabled,
                shape = RoundedCornerShape(4.dp),
                colors = onMainButtonColors
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "ok")
                VerticalDivider(thickness = 5.dp, color = Color.Transparent)
                Text(text = stringResource(id = R.string.accept))
            }
        }
        AnimatedVisibility(visible = isEnabled) {
            Button(
                onClick = { onValueChange(!isEnabled) },
                enabled = isEnabled,
                shape = RoundedCornerShape(4.dp),
                colors = onMainButtonColors
            ) { Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
                VerticalDivider(thickness = 5.dp, color = Color.Transparent)
                Text(text = stringResource(id = R.string.edit))
            }

        }

        VerticalDivider(thickness = 10.dp)

        Button(
            onClick = { vm.deleteNewCheckList(checkList) },
            shape = RoundedCornerShape(4.dp),
            colors = onMainButtonColors
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            VerticalDivider(thickness = 5.dp, color = Color.Transparent)
            Text(text = stringResource(id = R.string.delete))
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
fun AddCheckBoxTitle(vm: MainViewModel){
    Button(onClick = { /*TODO*/ }) {

    }
}