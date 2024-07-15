package com.example.letscheck.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.letscheck.CheckListViewModel

@Composable
fun AnimatedEntitiesRow(isVisible: Boolean, vm: CheckListViewModel){
    AnimatedVisibility(
        visible = isVisible,
        enter =
        fadeIn(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + expandVertically { 100 },
        exit =
        fadeOut(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + shrinkVertically { 0 }
    ) {
        LazyRow(
            modifier = Modifier,
        ) {
            items(vm.entities) {
                Box(modifier = Modifier
                    .size(120.dp)
                    .background(color = Color.Blue)
                    .clickable(
                        onClick = {
                            vm.currentEntity = it
                            vm.entityId = it.id
                        }),
                    contentAlignment = Alignment.Center){
                    Text(text = it.entityName)
                }

            }

        }
    }
}

// Функция проверяет 3 условия:
// 1. Пользователь выбран; 2. Чеклисты существуют; 3. Cписок не выбран
fun isEntityListVisible(vm: CheckListViewModel): Boolean{
    return vm.currentUser != null && vm.entities.isNotEmpty() && vm.currentEntity == null
}
