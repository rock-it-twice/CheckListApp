package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letscheck.ui.theme.Typography
import com.example.letscheck.viewModels.MainViewModel

@Composable
fun CurrentActivity(vm: MainViewModel){
    val currentActivity = vm.currentJointUserActivity!!.userActivity
    Text(
        modifier = Modifier.padding(vertical = 21.dp),
        text = currentActivity.activityName,
        style = Typography.titleLarge
    )
}