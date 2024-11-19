package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun NewEntityRow(name: String, onNameChange: (String) -> Unit){

    var isEnabled by rememberSaveable { mutableStateOf( name != "") }

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        TextField(
            value = name,
            onValueChange = { onNameChange(it) },
            modifier = Modifier.padding(vertical = 10.dp),
            enabled = !isEnabled,
            textStyle = MaterialTheme.typography.headlineMedium,
            label = { Text( stringResource(R.string.new_title_label) ) },
            placeholder = { TextFieldPlaceholder(R.string.new_entity_name, isEnabled) },
            colors = textFieldColors
        )

        AcceptOrEditEntityButton(
            name = name,
            isEnabled = isEnabled,
            onNameChange = onNameChange,
            onValueChange = { isEnabled = it}
        )

    }

}

@Composable
fun AcceptOrEditEntityButton(
                       name: String,
                       isEnabled: Boolean,
                       onNameChange: (String) -> Unit,
                       onValueChange: (Boolean) -> Unit){
    Row {
        AnimatedVisibility(visible = !isEnabled ) {
            IconButton(
                onClick = {
                    onNameChange(name)
                    onValueChange(!isEnabled)
                },
                enabled = !isEnabled,
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    modifier = Modifier.size(15.dp),
                    contentDescription = "create"
                )
            }

        }
        AnimatedVisibility(visible = isEnabled) {
            IconButton(
                onClick = { onValueChange(!isEnabled) },
                enabled = isEnabled,
                modifier = Modifier.size(width = 50.dp, height = 25.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    modifier = Modifier.size(15.dp),
                    contentDescription = "edit"
                )
            }
        }
    }
}