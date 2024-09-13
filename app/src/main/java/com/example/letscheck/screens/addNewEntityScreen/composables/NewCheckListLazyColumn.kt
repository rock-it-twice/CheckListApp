package com.example.letscheck.screens.addNewEntityScreen.composables
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun NewCheckListLazyColumn(vm: AddNewEntityViewModel, navController: NavController) {

    val modifier = Modifier

    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn(modifier) {

            vm.newChecklists.forEachIndexed{ listIndex, checkList ->

                item(key = checkList.id) {

                    NewCheckListRow(vm = vm,
                        index = listIndex,
                        modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
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
                        modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                        checkBoxTitle = item
                    )

                }
                item{ AddNewCheckBoxButton(vm = vm, index = listIndex) }
            }
            item { AddNewChecklistButton(vm = vm) }
            item { SaveResultToDataBaseButton(vm = vm, navController = navController) }
        }

    }
}

