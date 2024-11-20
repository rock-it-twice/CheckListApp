package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.letscheck.R

@Composable
fun DeleteCheckListButton(
    showPopUp: Boolean,
    onShowPopUpChange: (Boolean) -> Unit
){
    // Удалить
    TextButton(
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = {
            onShowPopUpChange(!showPopUp)
        }
    ) {
        Text(text = stringResource(id = R.string.add_new_delete_checklist))
    }
}

@Preview
@Composable
fun DeleteCheckListButtonPreview(){
    DeleteCheckListButton(showPopUp = false){}
}