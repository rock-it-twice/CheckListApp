package com.example.letscheck.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.navigation.Screens
import com.example.letscheck.ui.theme.Typography

@Composable
fun ChooseUserDropDownMenu(vm: CheckListViewModel, navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }
    // отслеживание выбран ли чеклист
    var isEntityChosen by remember { mutableStateOf(vm.currentEntity != null) }

    val users by vm.users.observeAsState(initial = listOf())
    Box(
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = if (vm.currentUserActivity != null) vm.currentUserActivity!!.userName else "Choose user",
                style = Typography.titleLarge
            )
            IconToggleButton(checked = isExpanded, onCheckedChange = { isExpanded = it }) {
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
            AnimatedVisibility(
                visible = isEntityChosen,
                enter =
                fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
                        + expandHorizontally { 100 },
                exit =
                fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
                        + shrinkHorizontally { 0 }
            ) {
                Text(
                    text = vm.currentEntity!!.entityName,
                    style = Typography.titleLarge)
            }
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = !isExpanded },
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 5.dp)
            )
            {
                users.forEach {
                    Text(
                        text = it.userName,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .clickable(onClick = {
                                vm.currentEntity = null
                                vm.getUserId(it.id)
                                vm.getUserById()
                                vm.getEntitiesByUserId()
                                isExpanded = !isExpanded
                            }
                            )
                    )
                }
                HorizontalDivider()
                Text(text = "add new user",
                    fontSize = 24.sp,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screens.CreateUserScreen.route)
                    })
                )
            }

        }

    }
}


