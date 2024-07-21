package com.example.letscheck.screens.composables

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

import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.ui.theme.HeaderTypography
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainCheckedColor
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.Typography


@Composable
fun CurrentEntityColumn(vm: MainViewModel) {
    if (vm.currentEntity != null) {
        // Получение чеклиста
        vm.getJointCheckList()
        // Вывод заголовка
        Title(vm = vm)
        // Вывод чеклиста
        CheckListColumn( checklists = vm.jointCheckLists, vm = vm )
    }
}



@Composable
fun Title(vm: MainViewModel) {
    Text(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth(),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        text = vm.currentEntity!!.entityName,
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
                style = HeaderTypography.titleLarge,
                color = color,
                text = jointCheckList.checkList!!.checkListName
            )
        }
        HorizontalDivider(Modifier.fillMaxWidth())
    }
}

@Composable
fun CheckBoxRow(checkBoxTitle: CheckBoxTitle,
                isChecked: Boolean,
                onCheckedChange: (Boolean) -> Unit) {

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
            onCheckedChange =  onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MainCheckedColor,
                uncheckedColor = MainTextColor
            )
        )
        Text(
            color = color,
            style = Typography.bodyMedium,
            text = checkBoxTitle.text
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckListColumn(vm: MainViewModel, checklists: List<JointCheckList>){
    // Заполняем данными список во viewModel
    checklists.forEach{
        val list: MutableList<Boolean> = mutableStateListOf()
        (0..it.checkBoxTitles.size-1).forEach{ _ -> list.add(false) }
        vm.checkBoxStateList.add(list)
    }
    LazyColumn {
        checklists.forEachIndexed { index, jointCheckList ->

            // Подзаголовок
            stickyHeader {
                Subtitle(jointCheckList = jointCheckList, stateList = vm.checkBoxStateList[index])
            }
            item { Spacer(Modifier.height(20.dp)) }
            // Чекбоксы
            itemsIndexed(jointCheckList.checkBoxTitles){ i, title ->
                vm.checkBoxStateList.forEachIndexed{ n, it -> println("index: $n list: $it") }
                CheckBoxRow(checkBoxTitle = title,
                    isChecked = vm.checkBoxStateList[index][i],
                    onCheckedChange = { vm.checkBoxStateList[index][i] = it }
                )
            }
        }
    }
}



