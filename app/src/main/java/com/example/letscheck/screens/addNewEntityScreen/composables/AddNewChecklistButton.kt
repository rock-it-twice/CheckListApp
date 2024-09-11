package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R

import com.example.letscheck.viewModels.MainViewModel


@Composable
fun AddNewChecklistButton(vm: MainViewModel){

    Button(
        onClick = { vm.addNewCheckList("") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 30.dp),
        shape = RoundedCornerShape(20.dp)
    ) { Text( text = stringResource(id = R.string.add_new_subtitle) ) }

}