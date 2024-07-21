package com.example.letscheck.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.UserEntity

@Composable
fun AnimatedEntitiesGrid(isVisible: Boolean, vm: MainViewModel){
    AnimatedVisibility(
        visible = isVisible,
        enter =
        fadeIn(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + expandVertically { 100 },
        exit =
        fadeOut(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + shrinkVertically { 0 }
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(135.dp),
            contentPadding = PaddingValues(10.dp),
            content = { items(vm.entities) { EntityBox(vm = vm, entity = it) }
            }
        )
    }
}

@Composable
fun EntityBox(vm: MainViewModel, entity: UserEntity){
    Column {
        Box(modifier = Modifier
            .size(width = 135.dp, height = 240.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(
                onClick = {
                    vm.currentEntity = entity
                    vm.entityId = entity.id
                }),
            contentAlignment = Alignment.Center){
            if (entity.image != 0) {
                Image(
                    painter = painterResource(id = entity.image),
                    contentDescription = "icon"
                )
            }
        }
        Text(text = entity.entityName)
    }

}

// Функция проверяет 3 условия:
// 1. Пользователь выбран; 2. Чеклисты существуют; 3. Cписок не выбран
fun isEntityListVisible(vm: MainViewModel): Boolean{
    return vm.currentUserActivity != null && vm.entities.isNotEmpty() && vm.currentEntity == null
}
