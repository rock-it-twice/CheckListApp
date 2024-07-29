package com.example.letscheck.screens.ChooseUserScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R
import com.example.letscheck.ui.theme.Typography
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MenuButton(vm: MainViewModel, scope: CoroutineScope, drawerState: DrawerState) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                scope.launch { drawerState.apply { if (isClosed) open() else close() } }
            })
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            style = Typography.titleLarge,
            text = if (vm.currentJointUserActivity != null) {
                vm.currentJointUserActivity!!.userActivity.activityName
            } else stringResource(id = R.string.activity_filter)
        )

        IconToggleButton(
            checked = drawerState.isClosed,
            onCheckedChange = {}
        ) {
            if (drawerState.isClosed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "open menu"
                )

            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "close menu"
                )
            }
        }
    }
}