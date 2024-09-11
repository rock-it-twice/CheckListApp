package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@Composable
fun SaveResultToDataBaseButton(vm: MainViewModel, navController: NavController){
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
        ) {
        Button(
            modifier = Modifier.size(60.dp, 60.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                scope.launch {
                    vm.saveImageToInternalStorage()
                    delay(200)
                    vm.getNewImageUriFromInternalStorage()
                    delay(200)
                    vm.assignImageToNewEntity()
                    delay(200)
                    vm.saveNewEntityToDataBase()
                    delay(2000)
                    vm.clearAddNewEntityScreenData()
                    delay(200)
                    // Переход на главную
                    navController.navigate( Routes.Home.route )
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(Icons.Default.Add, contentDescription = "save to DB")
        }
    }

}