package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
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
import kotlinx.coroutines.launch
import okhttp3.internal.wait


@Composable
fun AnimatedEntitiesGrid(vm: MainViewModel, navController: NavController){

    val size: DpSize = DpSize(135.dp, 240.dp)

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
            modifier = Modifier,
            columns = GridCells.Adaptive(135.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                items(entities) { EntityBox(vm = vm, size, jointEntity = it) }
                item { AddNewEntity(navController = navController, size) }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EntityBox(vm: MainViewModel, size: DpSize, jointEntity: JointEntity){

    val id = jointEntity.entity.id
    var isExpanded: Boolean by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = { vm.getJointEntity(jointEntity) },
                onLongClick = { isExpanded = !isExpanded },
                onLongClickLabel = stringResource(R.string.long_click_label)
            ),
            contentAlignment = Alignment.Center
        ){
            DropDownContextMenu(vm, isExpanded, size, id) { isExpanded = it }
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
            text = jointEntity.entity.entityName
        )
    }
}

@Composable
fun AddNewEntity(navController: NavController, size: DpSize) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 135.dp, height = 240.dp)
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
            text = stringResource(id = R.string.add_new_entity)
        )
    }
}

@Composable
fun DropDownContextMenu(vm: MainViewModel,
                        isExpanded: Boolean,
                        size: DpSize,
                        entityId: Int,
                        onValueChange: (Boolean) -> Unit
){
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onValueChange(!isExpanded) },
        offset = DpOffset(size.width/2, (-size.height)/2),
        modifier = Modifier,
    ) {
        DropdownMenuItem(
            modifier    = Modifier,
            text        = { Text(stringResource(R.string.edit)) },
            onClick     = {
            /* ToDo написать функцию помещения выбранных данных на экран редактирования */
                onValueChange(!isExpanded)
            },
            leadingIcon = { Icon(Icons.Default.Edit, stringResource(R.string.edit)) }
        )
        DropdownMenuItem(
            modifier    = Modifier,
            text        = { Text(stringResource(R.string.delete)) },
            onClick     = {
                vm.deleteEntity(entityId)
                onValueChange(!isExpanded)
                          },
            leadingIcon = { Icon(Icons.Default.Delete, stringResource(R.string.delete)) }
        )
    }
}
