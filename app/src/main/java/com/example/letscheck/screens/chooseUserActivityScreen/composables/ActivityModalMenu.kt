package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    val state = rememberScrollState()

    ModalDrawerSheet(
        modifier = Modifier,
        drawerContainerColor = SecondaryBackgroundColor,
        windowInsets = WindowInsets(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .verticalScroll(state)
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable(onClick = { onValueChange(!isVisible) }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.add_new_activity),
                    fontSize = 24.sp,
                    color = SecondaryTextColor
                )
                when(!isVisible){
                    true -> Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "show",
                        tint = SecondaryTextColor
                    )
                    false -> Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "hide",
                        tint = SecondaryTextColor
                    )
                }
            }
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
                .padding(vertical = 10.dp)
                .clickable(onClick = {
                    vm.getJointActivityByIdAndClearPrevious(activity.id)
                    scope.launch { drawerState.apply { close() } }
                })
        )
    }
}