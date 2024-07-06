package com.example.letscheck.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.data.classes.User
import com.example.letscheck.navigation.Screens
import com.example.letscheck.screens.composables.Header
import com.example.letscheck.ui.theme.Typography


@Composable
fun ChooseUserScreen(navController: NavController, vm: CheckListViewModel = viewModel()) {

    val users by vm.users.observeAsState(initial = listOf())
    var isExpanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Header()
        Box(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
             {
                Text(
                    text = if (vm.currentUser != null) vm.currentUser!!.userName else "Choose user",
                    style = Typography.titleLarge)
                 IconToggleButton(checked = isExpanded, onCheckedChange = { isExpanded = it }) {
                     if (!isExpanded) {
                         Icon(
                             imageVector = Icons.Default.KeyboardArrowDown,
                             contentDescription = null)
                     } else {
                         Icon(imageVector = Icons.Default.KeyboardArrowUp,
                             contentDescription = null)
                     }
                 }
            }

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = !isExpanded },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 5.dp))
                {
                    users.forEach { Text(
                        text = it.userName,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .clickable(onClick = {
                                vm.getUserId(it.userId)
                                vm.getUserById()
                                isExpanded = !isExpanded }
                            )
                    )}
                    HorizontalDivider()
                    Text(text = "add new user",
                        fontSize = 24.sp,
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate(Screens.CreateUserScreen.route)
                        }))
                }

            }

        }
    }

}