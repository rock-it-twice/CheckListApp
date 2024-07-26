package com.example.letscheck.screens.ChooseUserScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun CreateNewActivity(
    vm: MainViewModel,
    isButtonPushed: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    AnimatedVisibility(visible = isButtonPushed, enter = fadeIn(), exit = fadeOut()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .clickable(onClick = { onValueChange(!isButtonPushed) }),
            contentAlignment = Alignment.Center
        ) {
//        Box(modifier = Modifier.fillMaxSize().background(Color.Transparent).blur(20.dp))
            Row(
                Modifier.fillMaxWidth().padding(20.dp)
            ) {
                OutlinedTextField(
                    value = vm.userActivityName,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .weight(0.75F, true),
                    onValueChange = { vm.changeActivityName(it) },
                    placeholder = {
                        Text(text = stringResource(id = R.string.add_new_activity_type))
                    },
                    colors = OutlinedTextFieldDefaults.colors(MainTextColor)
                )
                Button(
                    onClick = {
                        vm.addUserActivity();
                        onValueChange(!isButtonPushed)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier.weight(0.25F, true)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_button)
                    )
                }
            }
        }
    }
}