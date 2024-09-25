package com.example.letscheck.screens.addNewEntityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.AddNewEntityViewModel


@Composable
fun SaveResultToDataBaseButton(
    vm: AddNewEntityViewModel,
    navController: NavController,
    fabVisibility: Boolean
){
    AnimatedVisibility(
        modifier = Modifier,
        visible = fabVisibility,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(
            modifier = Modifier.size(60.dp, 60.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                // Сохранение выбранного изображения в internal storage (внутреннем хранилище)
                vm.saveImageToInternalStorage()
                // Получение нового uri из internal storage
                vm.getNewImageUriFromInternalStorage()
                // Присвоение ссылки на изображение классу NewEntity
                vm.assignImageToNewEntity()
                // Сохранение нового списка в БД
                vm.saveNewDataToDB()
                // Переход на главную
                navController.navigate(Routes.Home.route)
                // Очистка экрана создания
                vm.clearNewEntity()
            },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(Icons.Default.Add, contentDescription = "save into DB")
        }
    }

}

