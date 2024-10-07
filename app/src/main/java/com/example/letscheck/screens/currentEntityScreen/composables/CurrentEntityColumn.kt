package com.example.letscheck.screens.currentEntityScreen.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.viewModels.CurrentEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentEntityColumn(vm: CurrentEntityViewModel,
                        navController: NavController,
                        lazyListState: LazyListState,
                        innerPadding: PaddingValues,
                        topBarScrollBehavior: TopAppBarScrollBehavior
) {

    val modifier = Modifier
    val mainTitle = vm.currentJointEntity?.entity?.entityName ?: ""
    val checklists = vm.currentJointEntity?.checkLists ?: listOf()
    val isCheckedList by
    vm.getCheckedList(vm.currentJointEntity?.entity?.id ?: 0).observeAsState(listOf())
    var vibrate by rememberSaveable { mutableStateOf(false) }


    LazyColumn(
        modifier = modifier.nestedScroll( connection = topBarScrollBehavior.nestedScrollConnection ),
        state = lazyListState,
        contentPadding = innerPadding
    ) {
        // Прерывает текущую вибрацию и запускает длинную вибрацию, когда все чеклисты отмечены
        vibrate = isCheckedList.all{ it }
        if (vibrate) {
            vm.vibrator.cancel()
            vm.vibrator.vibrate(vm.longVibrationEffect)
            vibrate = false
        }

        item { Title(mainTitle, isCheckedList.all { it }) }

        checklists.forEachIndexed { _, jointCheckList ->

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    // Подзаголовок
                    Column(modifier = Modifier.padding(start = 10.dp)) {

                        val id = jointCheckList.checkList.id
                        val isCheckedSubList by
                        vm.getCheckedSubList(id = id).observeAsState(listOf())

                        // Прерывает текущую и запускает среднюю вибрацию,
                        // когда все чеклисты в подсписке отмечены
                        vibrate = isCheckedSubList.all { it }
                        if (isCheckedSubList.all { it }) {
                            vm.vibrator.cancel()
                            vm.vibrator.vibrate(vm.standardVibrationEffect)
                            vibrate = false
                        }

                        Subtitle(jointCheckList, isCheckedSubList)
                        // Чекбоксы
                        jointCheckList.checkBoxTitles.forEach { title ->

                            val isChecked by vm.isChecked(title.id).observeAsState(false)

                            // Прерывает текущую и запускает короткую вибрацию,
                            // когда все чеклисты отмечены
                            vibrate = isChecked
                            if (isChecked) {
                                    vm.vibrator.cancel()
                                    vm.vibrator.vibrate(vm.shortVibrationEffect)
                                    vibrate = false
                            }

                            CheckBoxRow(
                                checkBoxTitle = title,
                                isChecked = isChecked
                            ) { vm.updateCheckBoxTitle(title.copy(checked = it)) }

                        }
                    }
                }

            }
        }
    }
}
