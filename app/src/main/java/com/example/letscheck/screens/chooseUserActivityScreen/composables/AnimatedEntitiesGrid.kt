package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.letscheck.R
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.navigation.Routes


@Composable
fun AnimatedEntitiesGrid(vm: MainViewModel, navController: NavController){

    val cellSize: DpSize = DpSize(135.dp, 240.dp)
    val entities = ( vm.currentJointUserActivity?.entities ?: listOf() )

    AnimatedVisibility(
        visible = vm.isGridVisible(),
        enter =
        fadeIn(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + expandVertically { 100 },
        exit =
        fadeOut(animationSpec =
        spring(stiffness = Spring.StiffnessLow)) + shrinkVertically { 0 }
    ) {

        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Adaptive(cellSize.width),
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                items(
                    items= entities,
                    key = {item -> item.entity.id}) {
                    EntityBox(vm = vm, cellSize, jointEntity = it)
                }
                item {
                    AddNewEntity(navController = navController, cellSize)
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EntityBox(vm: MainViewModel,
              gridSize: DpSize, jointEntity: JointEntity
){

    val activityId = jointEntity.entity.activityId
    val entityId = jointEntity.entity.id
    var isExpanded: Boolean by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(gridSize)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = { vm.getJointEntity(jointEntity) },
                onLongClick = { isExpanded = !isExpanded },
                onLongClickLabel = stringResource(R.string.long_click_label)
            ),
            contentAlignment = Alignment.Center
        ){
            DropDownContextMenu( vm, isExpanded, gridSize, activityId, entityId)  { isExpanded = it }

            if (jointEntity.entity.image != "") {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(jointEntity.entity.image.toUri())
                    .crossfade(500)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            }

        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text     = jointEntity.entity.entityName
        )
    }
}

@Composable
fun AddNewEntity(navController: NavController, size: DpSize) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(size.width, size.height)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
                .clickable(onClick = {
                    navController.navigate(route = Routes.AddNewEntityScreen.route)
                }),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "ADD",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text     = stringResource(id = R.string.add_new_entity)
        )
    }
}

@Composable
fun DropDownContextMenu(vm: MainViewModel,
                        isExpanded: Boolean,
                        size: DpSize,
                        activityId: Long,
                        entityId: Long,
                        onValueChange: (Boolean) -> Unit
){
    DropdownMenu(
        expanded         = isExpanded,
        onDismissRequest = { onValueChange(!isExpanded) },
        offset           = DpOffset(size.width/2, (-size.height)/2),
        modifier         = Modifier,
    ) {

        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.edit)) },
            onClick      = {
            /* ToDo написать функцию помещения выбранных данных на экран редактирования */
                onValueChange(!isExpanded)
            },
            leadingIcon  = { Icon(Icons.Default.Edit, stringResource(R.string.edit)) }
        )
        DropdownMenuItem(
            modifier     = Modifier,
            text         = { Text(stringResource(R.string.delete)) },
            onClick      = {
                vm.deleteEntityById(entityId)
                vm.getJointActivityByIdAndClearPrevious(activityId)
                onValueChange(!isExpanded)
                          },
            leadingIcon  = { Icon(Icons.Default.Delete, stringResource(R.string.delete)) }
        )

    }
}
