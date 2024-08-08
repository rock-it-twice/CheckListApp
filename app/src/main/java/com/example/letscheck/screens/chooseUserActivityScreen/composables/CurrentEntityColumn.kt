package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateListOf

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.output.JointCheckList
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainCheckedColor
import com.example.letscheck.ui.theme.MainTextColor


@Composable
fun CurrentEntityColumn(vm: MainViewModel) {
    if (vm.currentJointEntity != null) {
        CheckListColumn(vm = vm)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckListColumn(vm: MainViewModel) {
    val checklists = vm.currentJointEntity!!.checkLists
    // Заполняем во viewModel checkBoxStateList данными
    checklists.forEach {
        val subList: MutableList<Boolean> = mutableStateListOf()
        (0..<it.checkBoxTitles.size).forEach { _ -> subList.add(false) }
        vm.checkBoxStateList.add(subList)
    }
    LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
        item { Title(vm = vm) }
        checklists.forEachIndexed { index, jointCheckList ->

            // Подзаголовок
            stickyHeader {
                Subtitle(jointCheckList = jointCheckList, stateList = vm.checkBoxStateList[index])
            }
            item { Spacer(Modifier.height(20.dp)) }
            // Чекбоксы
            itemsIndexed(jointCheckList.checkBoxTitles) { i, title ->
                CheckBoxRow(checkBoxTitle = title,
                    isChecked = vm.checkBoxStateList[index][i],
                    onCheckedChange = { vm.checkBoxStateList[index][i] = it }
                )
            }

        }
    }
}

@Composable
fun Title(vm: MainViewModel) {

    val isEverythingChecked: Boolean = vm.checkBoxStateList.flatten().all { it }
    val color = if (isEverythingChecked) MainCheckedColor else MainTextColor

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        style = EntityTypography.titleLarge,
        color = color,
        fontWeight = FontWeight.SemiBold,
        text = vm.currentJointEntity!!.entity.entityName,
    )
}

@Composable
fun Subtitle(jointCheckList: JointCheckList, stateList: List<Boolean>) {

    val color =
        when {
            stateList.all { it } -> MainCheckedColor
            stateList.none { it } -> MainTextColor
            else -> MainTextColor
        }

    Column(
        Modifier
            .fillMaxWidth()
            .background(color = MainBackgroundColor)
    ) {
        Row {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                style = EntityTypography.titleMedium,
                color = color,
                text = jointCheckList.checkList.checkListName
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

    val color: Color = if (isChecked) MainCheckedColor else MainTextColor

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
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MainCheckedColor,
                uncheckedColor = MainTextColor
            )
        )
        Text(
            color = color,
            style = EntityTypography.titleSmall,
            text = checkBoxTitle.text
        )
    }
}