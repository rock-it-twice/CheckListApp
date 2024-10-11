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
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.letscheck.R
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.navigation.Routes
import com.example.letscheck.ui.theme.checkedDeepGreen
import com.example.letscheck.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedEntitiesGrid(
    vm: MainViewModel,
    navController: NavController,
    state: LazyGridState,
    topBarScrollBehavior: TopAppBarScrollBehavior
){

    vm.getJointUserActivityById()
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
            modifier = Modifier.nestedScroll( connection = topBarScrollBehavior.nestedScrollConnection),
            columns = GridCells.Adaptive(cellSize.width),
            state = state,
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                items(
                    items= entities,
                    key = {item -> item.entity.id}) {
                    val progressObserver by vm.getCheckedList(it.entity.id).observeAsState(listOf())
                    EntityBox(vm, navController, cellSize, it, progressObserver)
                }
                item {
                    AddNewEntityBox(navController = navController, cellSize)
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EntityBox(vm: MainViewModel,
              navController: NavController,
              gridSize: DpSize,
              jointEntity: JointEntity,
              progressObserver: List<Boolean>
){
    val entityId by remember { mutableLongStateOf(jointEntity.entity.id) }
    var isExpanded: Boolean by remember { mutableStateOf(false) }

    val listSize = progressObserver.size
    val progress = progressObserver.count{ it }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(gridSize)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = {
                    vm.getEntityId(entityId)
                    navController.navigate( route = Routes.CurrentEntityScreen.route )
                          },
                onLongClick = { isExpanded = !isExpanded },
                onLongClickLabel = stringResource(R.string.long_click_label)
            ),
            contentAlignment = Alignment.BottomCenter
        ){
            DropDownContextMenu( vm, isExpanded, gridSize, entityId, progressObserver)  { isExpanded = it }

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
            } else {
                NoImageBox()
            }
            ProgressIndicator(progress, listSize)
        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text     = jointEntity.entity.entityName,
        )
    }
}

@Composable
fun ProgressIndicator(progress: Int, listSize: Int){

    AnimatedVisibility(
        visible = progress == listSize,
        enter = fadeIn(),
        exit = fadeOut(),
        content = { Box( Modifier.fillMaxSize().alpha(0.5f).background(checkedDeepGreen) ) }
    )
    if (progress == listSize) {

        Row(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Done, "Done",
                modifier = Modifier.size(20.dp),
                tint = Color.Green
            )
            Text(
                text = "$progress/$listSize",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Green
            )
        }

    } else {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "${stringResource(R.string.progress_bar_text)} $progress/$listSize",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun DropDownContextMenu(vm: MainViewModel,
                        isExpanded: Boolean,
                        size: DpSize,
                        entityId: Long,
                        progressObserver: List<Boolean>,
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
            text         = { Text(stringResource(R.string.entity_reset)) },
            onClick      = {
                vm.resetCheckBoxes(entityId)
                onValueChange(!isExpanded)
            },
            enabled      = progressObserver.any { it },
            leadingIcon  = { Icon(Icons.Default.Refresh, stringResource(R.string.entity_reset)) }
        )

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
                vm.getJointUserActivityById()
                onValueChange(!isExpanded)
                          },
            leadingIcon  = { Icon(Icons.Default.Delete, stringResource(R.string.delete)) }
        )

    }
}

@Composable
fun NoImageBox(){
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                tint = (MaterialTheme.colorScheme.onSurface).copy(alpha = 0.3f)
            )
            Spacer(Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.no_image_added),
                color = (MaterialTheme.colorScheme.onSurface).copy(alpha = 0.3f),
                textAlign = TextAlign.Center,
                minLines = 2
            )
        }
    }
}

@Composable
fun AddNewEntityBox(navController: NavController, size: DpSize) {

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
