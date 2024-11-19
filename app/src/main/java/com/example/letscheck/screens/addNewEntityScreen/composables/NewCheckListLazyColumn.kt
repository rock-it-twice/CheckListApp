package com.example.letscheck.screens.addNewEntityScreen.composables



import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.AddNewEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCheckListLazyColumn(
    vm: AddNewEntityViewModel,
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    topAppBarScrollBehavior: TopAppBarScrollBehavior) {

    val modifier = Modifier

    var entityName = vm.newEntity?.entityName ?: ""

    LaunchedEffect(vm.newEntity?.entityName) {
        entityName = vm.newEntity?.entityName ?: ""
    }

    LazyColumn(
        modifier = modifier.nestedScroll(
            connection = topAppBarScrollBehavior.nestedScrollConnection
        ),
        state = lazyListState,
        contentPadding = innerPadding
    ) {
        item { PhotoPicker(vm.newImageUri) { vm.addNewImageUri(uri = it) } }
        item { NewEntityRow(entityName) { vm.renameNewEntity(it) } }
        vm.newChecklists.forEachIndexed { listIndex, checkList ->


            // Новый подсписок
            item(checkList.index + 1000) {
                Box(
                    modifier = modifier
                        .padding(bottom = 0.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .animateItem()
                ) {
                    NewCheckListRow(vm, modifier.animateItem(), listIndex, checkList)
                }
            }
            // чеклисты
            items(
                items = vm.newCheckBoxes[listIndex], key = { item -> item.index })
            {
                Box(
                    modifier = modifier
                        .animateContentSize().animateItem()
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    NewCheckBoxRow(vm, modifier.animateItem(), listIndex, it)
                }
            }
            // создание нового чеклиста
            item {
                Box(
                    modifier = modifier
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .animateItem()
                ) {
                    AddNewCheckBoxButton(vm = vm, index = listIndex)
                }
            }
        }
        // создание нового подсписка
        item { AddNewChecklistButton(vm = vm) }
        item { Spacer(Modifier.size(80.dp)) }
    }

}



