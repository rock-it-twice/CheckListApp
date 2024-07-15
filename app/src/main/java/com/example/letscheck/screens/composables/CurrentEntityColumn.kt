package com.example.letscheck.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.CheckListViewModel

@Composable
fun CurrentEntityColumn(vm: CheckListViewModel) {
    val modifier = Modifier
    if (vm.currentEntity != null) {
        vm.entityId = vm.currentEntity!!.id
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            // Имя списка
            Text(
                modifier = modifier
                    .padding(bottom = 20.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                text = vm.currentEntity!!.entityName,
            )

            vm.getJointCheckList()
            LazyColumn(
                modifier = modifier
                ) {
                items(vm.jointCheckList) {
                    HorizontalDivider(Modifier.fillMaxWidth().padding(vertical = 10.dp))
                    // Имя подсписка
                    Row(
                        modifier = modifier
                    ) {
                        Text(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            text = it.checkList!!.checkListName
                        )
                    }
                    // Чекбоксы и заголовки
                    it.checkBoxTitles.forEach {
                        var isChecked by rememberSaveable { mutableStateOf(false) }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { isChecked = !isChecked }),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked = !isChecked }
                            )
                            Text(
                                fontSize = 16.sp,
                                text = it.str
                            )
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

    }

}
