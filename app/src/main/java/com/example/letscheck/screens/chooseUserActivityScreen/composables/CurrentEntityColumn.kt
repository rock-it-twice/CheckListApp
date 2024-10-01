package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.output.JointCheckList



@Composable
fun CurrentEntityColumn(vm: MainViewModel) {
    if (vm.currentJointEntity != null) { CheckListColumn(vm = vm) }
}

    @Composable
    fun CheckListColumn(vm: MainViewModel) {

        val mainTitle = vm.currentJointEntity?.entity?.entityName ?: ""
        val checklists by rememberSaveable { mutableStateOf(vm.currentJointEntity!!.checkLists) }
        val isCheckedList by
            vm.getCheckedList(vm.currentJointEntity!!.entity.id).observeAsState(listOf())

        LazyColumn {
            item { Title(mainTitle, isCheckedList.all{ it }) }

            checklists.forEachIndexed { _, jointCheckList ->

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip( RoundedCornerShape(20.dp) )
                            .padding(bottom = 20.dp)
                            .background( MaterialTheme.colorScheme.surfaceContainer )
                    ) {
                        // Подзаголовок
                        Column(modifier = Modifier.padding(start = 10.dp)) {

                            val id = jointCheckList.checkList.id
                            val isCheckedSubList by
                                vm.getCheckedSubList(id = id).observeAsState(listOf())

                            Subtitle(jointCheckList, isCheckedSubList)
                            // Чекбоксы
                            jointCheckList.checkBoxTitles.forEach{ title ->

                                val isChecked by vm.isChecked(title.id).observeAsState(false)

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



@Composable
fun Title(mainTitle: String, isListChecked: Boolean) {

    val color = if (isListChecked) Color.Green else MaterialTheme.colorScheme.primary

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        color = color,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold,
        text = mainTitle,
    )
}


@Composable
fun Subtitle(checkList: JointCheckList, isSubListChecked: List<Boolean>) {

    val color =
        when {
            isSubListChecked.all { it } -> Color.Green
            isSubListChecked.none { it } -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.primary
        }
        Column(
            Modifier.fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = color,
                    text = checkList.checkList.checkListName
                )
            }
            HorizontalDivider(Modifier.fillMaxWidth())
        }
}



@Composable
fun CheckBoxRow(
    checkBoxTitle: CheckBoxTitle,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    val color: Color = if (isChecked) Color.Green else MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable(onClick = { onCheckedChange(!isChecked) }
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {onCheckedChange(!isChecked)},
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = MaterialTheme.colorScheme.primary
            )
        )
        Column {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                color = color,
                text = checkBoxTitle.text
            )
            if (checkBoxTitle.description != "") {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = checkBoxTitle.description
                )
            }
        }
    }
}