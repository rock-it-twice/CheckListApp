package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.onMainAddCheckboxButtonColors
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun AddNewCheckBoxButton(vm: MainViewModel, index: Int) {

    val newName = stringResource(id = R.string.new_checkbox_name)
    val size = vm.newCheckBoxes[index].size + 1

    Button(
        onClick = { vm.addNewCheckBox(index, "$newName $size") },
        modifier = Modifier.fillMaxWidth().height(35.dp),
        shape = RoundedCornerShape(20.dp),
        colors = onMainAddCheckboxButtonColors
    ) {
        VerticalDivider(thickness = 10.dp, color = Color.Transparent)
        Text(text = stringResource(id = R.string.add_new_checkbox))
    }

}