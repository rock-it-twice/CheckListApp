package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.data.classes.input.CheckList
import com.example.letscheck.ui.theme.onMainButtonColors
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun NewCheckListLazyColumn(vm: MainViewModel ) {
    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .padding(bottom = 30.dp)

        ) {
            items(vm.newCheckLists) { checkList ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    TextField(
                        value = checkList.checkListName,
                        onValueChange = { checkList.checkListName = it },
                        placeholder = { checkList.checkListName })
                    ControlButtons(vm, checkList)
                }
            }
            item {
                AddNewEmptyChecklistButton(vm = vm)
            }
        }

    }
}

@Composable
fun ControlButtons( vm: MainViewModel, checkList: CheckList ){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        VerticalDivider(thickness = 10.dp)
        IconButton(
            onClick = {
                vm.deleteNewCheckList(checkList)
            },
            colors = onMainIconButtonColors
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
        }
    }
}



@Composable
fun AddNewEmptyChecklistButton(vm: MainViewModel){
    val entityId = vm.newEntity!!.id
        Button(
            onClick = {
                vm.addNewEmptyCheckList(entityId = entityId)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = onMainButtonColors
        ) {
            Text(
                text = stringResource(id = R.string.add_new_subtitle),
            )
        }
}