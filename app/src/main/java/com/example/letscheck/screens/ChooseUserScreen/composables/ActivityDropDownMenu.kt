package com.example.letscheck.screens.ChooseUserScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
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
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.data.classes.input.UserActivity
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.SecondaryTextColor
import com.example.letscheck.ui.theme.Typography
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun ActivityDropDownMenu(vm: MainViewModel, navController: NavController){

    val activities by vm.userActivities.observeAsState(listOf())
    var isExpanded by remember { mutableStateOf(false) }


    Box {
        ChooseActivityButton(vm = vm, isExpanded = isExpanded,  onValueChange = {isExpanded = it})
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = !isExpanded },
            modifier = Modifier.background(SecondaryBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .fillMaxSize()
            ) {
                activities.forEach{ activity ->
                    ClickableActivityRow(
                        activity = activity,
                        vm = vm,
                        isExpanded= isExpanded,
                        onValueChange = { isExpanded = it }
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 5.dp),color = Color.Cyan)
                Text(
                    text = stringResource(id = R.string.add_new_activity),
                    fontSize = 24.sp,
                    color = SecondaryTextColor,
                    modifier = Modifier.clickable(onClick = { isExpanded = !isExpanded })
                )
            }
        }
    }



}

@Composable
fun ChooseActivityButton(
    vm: MainViewModel,
    isExpanded: Boolean,
    onValueChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = { onValueChange(!isExpanded) }),
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
        IconToggleButton(checked = isExpanded, onCheckedChange = onValueChange) {
            if (!isExpanded) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
            }
        }
    }
}

@Composable
fun ClickableActivityRow(
    activity: UserActivity,
    vm: MainViewModel,
    isExpanded: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Text(
        text = activity.activityName,
        fontSize = 24.sp,
        color = SecondaryTextColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable(onClick = {
                vm.getJointActivityByIdAndClearPrevious(activity.id)
                onValueChange(!isExpanded)
            }
            )
    )
}
