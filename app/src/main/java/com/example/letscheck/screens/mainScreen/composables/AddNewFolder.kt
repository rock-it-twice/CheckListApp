package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R


@Composable
fun AddNewFolder(
    visible: Boolean,
    onValueChange: (Boolean) -> Unit,
    onAddNewFolder: (String) -> Unit ) {
    AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier
                .height(95.dp)
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, end = 10.dp)
        ) {
            var folderName by remember { mutableStateOf("") }

            OutlinedTextField(
                value = folderName,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .weight(0.75F, true),
                onValueChange = { folderName = it },
                placeholder = {
                    Text( text = stringResource(id = R.string.add_new_folder_name) )
                },
                colors = OutlinedTextFieldDefaults.colors(
                )
            )
            Button(
                onClick = {
                    onAddNewFolder(folderName)
                    onValueChange(!visible)
                },
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .weight(0.25F, true)
                    .fillMaxHeight()
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_button)
                )
            }
        }
    }
}