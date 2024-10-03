package com.example.letscheck.screens.currentEntityScreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.data.classes.output.JointCheckList

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