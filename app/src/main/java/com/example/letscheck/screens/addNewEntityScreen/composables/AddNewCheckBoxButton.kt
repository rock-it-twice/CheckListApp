package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.MainGreenColor
import com.example.letscheck.ui.theme.MainWhiteColor
import com.example.letscheck.ui.theme.onMainAddCheckboxButtonColors
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun AddNewCheckBoxButton(vm: MainViewModel, index: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextButton(
            onClick = { vm.addNewCheckBox(index, "") },
            colors = ButtonDefaults.textButtonColors()
        ) {
            Text(
                text = stringResource(id = R.string.add_new_checkbox),
                color = MainWhiteColor,
                style = EntityTypography.bodyMedium
            )
        }

    }

}