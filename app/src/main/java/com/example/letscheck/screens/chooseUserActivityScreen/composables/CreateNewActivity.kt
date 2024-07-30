package com.example.letscheck.screens.chooseUserActivityScreen.composables

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.SecondaryTextColor
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun CreateNewActivity( vm: MainViewModel, visible: Boolean, onValueChange: (Boolean) -> Unit ) {
    AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
        Row(
            Modifier
                .height(95.dp)
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, end = 10.dp)
        ) {
            OutlinedTextField(
                value = vm.userActivityName,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .weight(0.75F, true),
                onValueChange = { vm.changeActivityName(it) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.add_new_activity_type),
                        color = SecondaryTextColor
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = SecondaryTextColor,
                    focusedTextColor = MainTextColor,
                    unfocusedBorderColor = SecondaryTextColor,
                    focusedBorderColor = SecondaryTextColor
                )
            )
            Button(
                onClick = {
                    vm.addUserActivity()
                    onValueChange(!visible)
                },
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryTextColor),
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