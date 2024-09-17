package com.example.letscheck.screens.addNewEntityScreen.composables
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
        LazyColumn(
            modifier = modifier.padding(bottom = 60.dp)
        ) {
            itemsIndexed(items = vm.newChecklists, key = { _, item -> item.id }){
                listIndex, checkList ->
                    Box(
                        modifier = modifier
                            .padding(bottom = 10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = modifier.padding(horizontal = 10.dp)
                        ) {
                            // Новый подзаголовок
                            NewCheckListRow(vm, modifier.animateItem(), listIndex, checkList)
                            // Новые чекбоксы
                            vm.newCheckBoxes[listIndex].forEach {
                                NewCheckBoxRow(vm, modifier.animateItem(), listIndex, it)
                            }
                            // Кнопка добавления чекбокса
                            AddNewCheckBoxButton(vm = vm, index = listIndex)
                        }
                    }
            }
            // Кнопка добавления подзаголовка
            item { AddNewChecklistButton(vm = vm) }
            // Кнопка сохранения списка в БД
            item {SaveResultToDataBaseButton(vm = vm, navController = navController) }
        }
   }
}



