package com.example.letscheck.screens.ChooseUserScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.R
import com.example.letscheck.data.classes.input.UserActivity
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.SecondaryTextColor
import com.example.letscheck.ui.theme.Typography
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ActivityModalMenu(
    vm: MainViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState,
    isVisible: Boolean,
    onValueChange: (Boolean) -> Unit
) {

    if (drawerState.isClosed) onValueChange(false)
    val activities by vm.userActivities.observeAsState(listOf())

    ModalDrawerSheet(
        modifier = Modifier,
        drawerContainerColor = SecondaryBackgroundColor,
        windowInsets = WindowInsets(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ) {
            activities.forEach { it ->
                ActivityRow(
                    vm = vm,
                    scope = scope,
                    activity = it,
                    drawerState = drawerState
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 5.dp),color = Color.Cyan)
            Text(
                text = stringResource(id = R.string.add_new_activity),
                fontSize = 24.sp,
                color = SecondaryTextColor,
                modifier = Modifier.clickable(onClick = { onValueChange(!isVisible) })
            )
            CreateNewActivity(
                vm = vm,
                visible = isVisible,
                onValueChange = { onValueChange(!isVisible) })
        }

    }
}


@Composable
fun ActivityRow(
    vm: MainViewModel, scope: CoroutineScope,
    activity: UserActivity, drawerState: DrawerState
) {
    Row {
        Text(
            text = activity.activityName,
            fontSize = 24.sp,
            color = SecondaryTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .clickable(onClick = {
                    vm.getJointActivityByIdAndClearPrevious(activity.id)
                    scope.launch { drawerState.apply { close() } }
                })
        )
    }
}