package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.letscheck.viewModels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewCheckListLazyColumn(vm: MainViewModel) {

    val modifier = Modifier

    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn(modifier) {

            vm.newChecklists.forEachIndexed{ listIndex, checkList ->

                item(key = checkList.id) {

                    NewCheckListRow(vm = vm,
                        index = listIndex,
                        modifier = modifier.animateItemPlacement(),
                        checkList = checkList
                    )

                }

                items(
                    items = vm.newCheckBoxes[listIndex],
                    key = { item -> item.id }
                )
                { item ->
                    NewCheckBoxRow(
                        vm = vm,
                        listIndex = listIndex,
                        modifier = modifier.animateItemPlacement(),
                        checkBoxTitle = item
                    )

                }
                item{ AddNewCheckBoxButton(vm = vm, index = listIndex) }
            }
            item { AddNewChecklistButton(vm = vm) }
        }

    }
}

