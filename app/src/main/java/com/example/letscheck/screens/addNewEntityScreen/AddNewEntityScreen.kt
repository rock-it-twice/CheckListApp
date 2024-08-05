package com.example.letscheck.screens.addNewEntityScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.data.classes.input.CheckBoxTitle
import com.example.letscheck.data.classes.input.CheckList
import com.example.letscheck.data.classes.input.UserEntity
import com.example.letscheck.screens.addNewEntityScreen.composables.NewCheckListLazyColumn
import com.example.letscheck.screens.addNewEntityScreen.composables.NewEntityRow
import com.example.letscheck.screens.common_composables.Header
import com.example.letscheck.ui.theme.EntityTypography
import com.example.letscheck.ui.theme.Typography
import com.example.letscheck.ui.theme.onMainButtonColors
import com.example.letscheck.ui.theme.onMainIconButtonColors
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun AddNewEntityScreen(navController: NavController, vm: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .padding(bottom = 30.dp)
    ) {
        Header(navController = navController, vm = vm)
        CurrentActivity(vm = vm)
        NewEntityRow(vm = vm)
        NewCheckListLazyColumn(vm = vm)
        }
}

@Composable
fun CurrentActivity(vm: MainViewModel){
    val currentActivity = vm.currentJointUserActivity!!.userActivity
    Text(
        modifier = Modifier.padding(vertical = 21.dp),
        text = currentActivity.activityName,
        style = Typography.titleLarge
    )
}






