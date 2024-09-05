package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.onMainButtonColors
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun AddNewChecklistButton(vm: MainViewModel){

    val newName = stringResource(id = R.string.new_subtitle_name)
    val size = vm.newChecklists.size + 1

    Button(
        onClick = { vm.addNewCheckList("$newName $size") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = onMainButtonColors
    ) { Text( text = stringResource(id = R.string.add_new_subtitle) ) }

}