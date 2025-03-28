package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import com.example.letscheck.data.classes.main.CheckList


@Composable
fun NewSubtitleRow(modifier: Modifier,
                   index: Int,
                   checkList: CheckList,
                   onRename: (Int, String) -> Unit
) {


    var newName   by rememberSaveable { mutableStateOf( checkList.checkListName ) }
    val _isEnabled by remember(newName) { derivedStateOf { newName.isBlank() }  }
    var isEnabled by rememberSaveable { mutableStateOf( _isEnabled ) }

    val textFieldColors = TextFieldDefaults.colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    )

    Column {

        var isVisible by remember { mutableStateOf( newName.isNotBlank() ) }
        LaunchedEffect(isEnabled) { isVisible = newName.isNotBlank() }
        LaunchedEffect(isVisible) { isEnabled = newName.isBlank() }

        AnimatedVisibility(visible = !isVisible) {
            Row(
                modifier = modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { isVisible = !isVisible },
                    colors = ButtonDefaults
                        .textButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                ) { Text(text = stringResource(R.string.add_new_subtitle)) }
            }
        }
        AnimatedVisibility(visible = isVisible) {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = newName,
                    modifier = Modifier.weight(1F).fillMaxWidth(),
                    enabled = isEnabled,
                    onValueChange = { newName = it },
                    textStyle = MaterialTheme.typography.titleLarge,
                    colors = textFieldColors,
                    placeholder = { TextFieldPlaceholder(R.string.new_subtitle_name, isEnabled) }
                )
                Spacer(modifier = Modifier.size(10.dp))
                AcceptRenameDeleteButton(
                    newName   = newName,
                    listIndex = index,
                    checkList = checkList,
                    isEnabled = isEnabled,
                    onEnabledChange   = { isEnabled = it },
                    onRenameCheckList = { i, name ->
                        onRename(i, name)
                        newName = name
                    }
                )
            }
        }

    }
}