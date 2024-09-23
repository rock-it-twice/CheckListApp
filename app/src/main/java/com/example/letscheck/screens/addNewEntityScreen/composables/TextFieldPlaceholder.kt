package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.letscheck.R

@Composable
fun TextFieldPlaceholder( stringPath: Int, isEnabled: Boolean){
    when{
        isEnabled  -> { Text("") }
        !isEnabled -> { Text(stringResource(stringPath)) }
    }
}

