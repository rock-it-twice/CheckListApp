package com.example.letscheck.screens.addNewEntityScreen.composables
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.main.CheckList
import com.example.letscheck.viewModels.AddNewEntityViewModel



@Composable
fun NewCheckListLazyColumn(vm: AddNewEntityViewModel, navController: NavController) {

    val modifier = Modifier

    AnimatedVisibility(visible = (vm.newEntity != null)) {

//            vm.newChecklists.forEachIndexed { listIndex, checkList ->
//                Box(
//                    modifier = modifier
//                        .padding(bottom = 10.dp)
//                        .clip(RoundedCornerShape(20.dp))
//                        .background(MaterialTheme.colorScheme.surfaceContainer)
//                        .animateContentSize()
//                ) {
//                    LazyColumn(
//                        modifier = modifier
//                    ) {
//                        item(checkList.index) {
//                            // Новый подзаголовок
//                            NewCheckListRow(vm, modifier.animateItem(), listIndex, checkList)
//                        }
//                        items(
//                            items = vm.newCheckBoxes[listIndex],
//                            key   = { item -> item.index }
//                        ) {
//                            // Новый чекбокс
//                            NewCheckBoxRow(vm, modifier.animateItem(), listIndex, it)
//                        }
//                        item {
//                            // Кнопка добавления чекбокса
//                            AddNewCheckBoxButton(vm = vm, index = listIndex)
//                        }
//                    }
//                }
//
//
//            }
//            // Кнопка добавления подзаголовка
//            AddNewChecklistButton(vm = vm)
//            // Кнопка сохранения списка в БД
//            SaveResultToDataBaseButton(vm = vm, navController = navController)
//        }
//
//}


//        LazyColumn(
//            modifier = modifier.padding(bottom = 30.dp),
//        ) {
//            itemsIndexed(items = (vm.newChecklists), key = { _, item -> item.index }){
//                listIndex, checkList ->
//                    Box(
//                        modifier = modifier
//                            .padding(bottom = 10.dp)
//                            .clip(RoundedCornerShape(20.dp))
//                            .background(MaterialTheme.colorScheme.surfaceContainer)
//                            .animateContentSize()
//                    ) {
//                        Column(
//                            modifier = modifier.padding(horizontal = 10.dp)
//                        ) {
//
//                            // Новый подзаголовок
//                            NewCheckListRow(vm, modifier.animateItem(), listIndex, checkList)
//                            // Новые чекбоксы
//                            vm.newCheckBoxes[listIndex].forEachIndexed { index, it ->
//                                NewCheckBoxRow(vm, modifier.animateItem(), listIndex, it)
//                            }
//                            // Кнопка добавления чекбокса
//                            AddNewCheckBoxButton(vm = vm, index = listIndex)
//                        }
//                    }
//            }
//            // Кнопка добавления подзаголовка
//            item { AddNewChecklistButton(vm = vm) }
//            // Кнопка сохранения списка в БД
//            item {SaveResultToDataBaseButton(vm = vm, navController = navController) }
//        }
//   }
//}


        LazyColumn(
            modifier = modifier.padding(bottom = 30.dp),
        ) {
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
                            .padding(top = 0.dp, bottom = 0.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .animateContentSize()
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


