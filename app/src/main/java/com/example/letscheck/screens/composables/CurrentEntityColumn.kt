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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.data.classes.CheckBoxTitle
import com.example.letscheck.data.classes.JointCheckList
import com.example.letscheck.ui.theme.HeaderTypography
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainCheckedColor
import com.example.letscheck.ui.theme.MainLineColor
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentEntityColumn(vm: CheckListViewModel) {
    val modifier = Modifier
    if (vm.currentEntity != null) {
        vm.entityId = vm.currentEntity!!.id
        // Получение чеклиста
        vm.getJointCheckList()
        Column(modifier.fillMaxWidth()) {
            // Заголовок
            Title(vm = vm)
            Spacer(Modifier.height(20.dp))
        }
        LazyColumn(modifier.fillMaxWidth()) {
            vm.jointCheckLists.forEach { jointCheckList ->
                // Подзаголовок
                stickyHeader { Subtitle(jointCheckList = jointCheckList) }
                item { Spacer(Modifier.height(20.dp)) }
                // Чекбоксы
                items(jointCheckList.checkBoxTitles) { CheckBoxRow(checkBoxTitle = it) }
            }
        }
    }
}


@Composable
fun Title(vm: CheckListViewModel) {
    Text(
        modifier = Modifier
            .padding(bottom = 20.dp),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        text = vm.currentEntity!!.entityName,
    )
}

@Composable
fun Subtitle(jointCheckList: JointCheckList) {
Column(
    Modifier
        .fillMaxWidth()
        .background(color = MainBackgroundColor)
) {
    Row {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            style = HeaderTypography.titleLarge,
            text = jointCheckList.checkList!!.checkListName
        )
    }
    HorizontalDivider( Modifier.fillMaxWidth() )
}



}

@Composable
fun CheckBoxRow(checkBoxTitle: CheckBoxTitle) {

    var isChecked by rememberSaveable { mutableStateOf(false) }
    var color: Color = if (isChecked) MainCheckedColor else MainTextColor

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { isChecked = !isChecked }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
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
