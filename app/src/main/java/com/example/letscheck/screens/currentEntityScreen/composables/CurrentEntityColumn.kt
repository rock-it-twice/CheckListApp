package com.example.letscheck.screens.currentEntityScreen.composables

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.viewModels.CurrentEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentEntityColumn(vm: CurrentEntityViewModel,
                        lazyListState: LazyListState,
                        innerPadding: PaddingValues,
                        topBarScrollBehavior: TopAppBarScrollBehavior,
                        isListChecked: List<Boolean>
) {

    val modifier = Modifier
    val checklists = vm.currentJointEntity?.checkLists ?: listOf()
    val onClickSoundEffect = vm.playSound(R.raw.click_type_2)

    LazyColumn(
        modifier = modifier
            .nestedScroll( connection = topBarScrollBehavior.nestedScrollConnection ),
        state = lazyListState,
        contentPadding = innerPadding
    ) {

        item { Title(vm.entityName, isListChecked.all { it }) }

        checklists.forEachIndexed { _, jointCheckList ->

            item(key = jointCheckList.checkList.id) {
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
                        val isSubListChecked by
                        vm.getCheckedSubList(id = id).observeAsState(listOf(false))

                        // Определение условия, при котором срабатывает вибрация
                        val vibrateStandard = (isSubListChecked.all { it } && !isListChecked.all{ it })
                        // Вызов вибрации, если все чекбоксы в подсписке отмечены
                        LaunchedEffect(vibrateStandard) {
                        vm.vibrate(vibrateStandard, "standard")
                        }

                        Subtitle(jointCheckList, isSubListChecked)
                        // Чекбоксы
                        jointCheckList.checkBoxTitles.forEach { title ->

                            val isChecked by vm.isChecked(title.id).observeAsState(false)

                            CheckBoxRow(
                                checkBoxTitle = title,
                                isChecked = isChecked,
                                soundEffect = onClickSoundEffect
                            ) { vm.updateCheckBoxTitle(title.copy(checked = it))}
                            // Прерывает текущую и запускает короткую вибрацию,
                            // когда все чеклисты отмечены
                        }
                    }
                }
            }
        }
    }
}
