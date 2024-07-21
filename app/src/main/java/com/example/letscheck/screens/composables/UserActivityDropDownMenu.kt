package com.example.letscheck.screens.composables

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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.R
import com.example.letscheck.navigation.Screens
import com.example.letscheck.ui.theme.SecondaryBackgroundColor
import com.example.letscheck.ui.theme.SecondaryTextColor
import com.example.letscheck.ui.theme.Typography

@Composable
fun UserActivityDropDownMenu(vm: MainViewModel, navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }

    val users by vm.userActivities.observeAsState(initial = listOf())
    Box(
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        FilterEntityByActivityButton(vm, isExpanded, onValueChange = { isExpanded = it })
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = !isExpanded },
            modifier = Modifier.background(color = SecondaryBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .fillMaxSize()
            )
            {
                users.forEach {
                    Text(
                        text = it.activityName,
                        fontSize = 24.sp,
                        color = SecondaryTextColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable(onClick = {
                                vm.currentEntity = null
                                vm.getUserActivityId(it.id)
                                vm.getUserActivityById()
                                vm.getEntitiesByUserId()
                                isExpanded = !isExpanded
                            }
                            )
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp), color = Color.Cyan)
                Text(
                    text = stringResource(id = R.string.add_new_activity),
                    fontSize = 24.sp,
                    color = SecondaryTextColor,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screens.CreateUserScreen.route)
                    })
                )
            }

        }

    }
}

@Composable
fun FilterEntityByActivityButton(vm: MainViewModel, isExpanded: Boolean, onValueChange: (Boolean) -> Unit){
    Row(
        modifier = Modifier.clickable(onClick = { onValueChange(!isExpanded) }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = if (vm.currentUserActivity != null) {
                vm.currentUserActivity!!.activityName
            } else stringResource(id = R.string.activity_filter),
            style = Typography.titleLarge
        )
        IconToggleButton(
            checked = isExpanded,
            onCheckedChange = onValueChange ) {
            if (!isExpanded) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            } else {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
    }

}


