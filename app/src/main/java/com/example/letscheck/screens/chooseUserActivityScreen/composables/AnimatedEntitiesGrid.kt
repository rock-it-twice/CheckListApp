package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainTextColor
import com.example.letscheck.ui.theme.SecondaryBackgroundColor

@Composable
fun AnimatedEntitiesGrid(vm: MainViewModel, navController: NavController){
    AnimatedVisibility(
        visible = vm.isGridVisible(),
        enter =
        fadeIn(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + expandVertically { 100 },
        exit =
        fadeOut(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + shrinkVertically { 0 }
    ) {
        val entities = vm.currentJointUserActivity?.entities ?: listOf()
        LazyVerticalGrid(
            modifier = Modifier.background(MainBackgroundColor),
            columns = GridCells.Adaptive(135.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                items(entities) { EntityBox(vm = vm, jointEntity = it) }
                item { AddNewEntity(navController = navController) }
            }
        )
    }
}

@Composable
fun EntityBox(vm: MainViewModel, jointEntity: JointEntity){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(width = 135.dp, height = 240.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { vm.getJointEntity(jointEntity) }),
            contentAlignment = Alignment.Center){ if (jointEntity.entity.image != 0) {
                Image(
                    painter = painterResource(id = jointEntity.entity.image),
                    contentDescription = "icon"
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text = jointEntity.entity.entityName
        )
    }
}

@Composable
fun AddNewEntity(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 135.dp, height = 240.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = { /* ToDo переход к созданию нового списка */ })
                .background(color = SecondaryBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "ADD",
                tint = MainTextColor
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text = stringResource(id = R.string.add_new_entity)
        )
    }
}
