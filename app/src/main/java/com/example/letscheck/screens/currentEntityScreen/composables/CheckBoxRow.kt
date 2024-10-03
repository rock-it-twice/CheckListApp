package com.example.letscheck.screens.currentEntityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.data.classes.main.CheckBoxTitle

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