package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun AddNewCheckBoxButton(index: Int, onAdd: (index: Int) -> Unit) {

    TextButton(
        onClick = { onAdd(index) },
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = stringResource(id = R.string.add_new_checkbox)
        )
    }

}