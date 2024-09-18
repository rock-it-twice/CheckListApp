package com.example.letscheck.screens.addNewEntityScreen.composables


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun NewCheckListLazyColumn(vm: AddNewEntityViewModel, navController: NavController) {

    val modifier = Modifier

    AnimatedVisibility(visible = (vm.newEntity != null)) {

        LazyColumn() {
            vm.newChecklists.forEachIndexed { listIndex, checkList ->
                item(checkList.index + 1000) {
                    Box(
                        modifier = modifier
                            .padding(bottom = 0.dp)
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        NewCheckListRow(vm, modifier.animateItem(), listIndex, checkList)
                    }
                }
                items(items = vm.newCheckBoxes[listIndex], key = { item -> item.index }
                ) {
                    Box(
                        modifier = modifier
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .animateContentSize().animateItem()
                    ) {
                        NewCheckBoxRow(vm, modifier.animateItem(), listIndex, it)
                    }
                }
                item {
                    Box(
                        modifier = modifier
                            .padding(bottom = 20.dp)
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        AddNewCheckBoxButton(vm = vm, index = listIndex)
                    }
                }
            }
            // Кнопка добавления подзаголовка
            item { AddNewChecklistButton(vm = vm) }
            // Кнопка сохранения списка в БД
            item { SaveResultToDataBaseButton(vm = vm, navController = navController) }
        }
    }
}


