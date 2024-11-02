package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    topBarScrollBehavior: TopAppBarScrollBehavior,
    showPopUp: (Boolean) -> Unit,
    getEntityId: (Long) -> Unit
){

    vm.getJointFolderById()
    val cellSize = DpSize(135.dp, 135.dp)
    val entities = ( vm.currentJointFolder?.entities ?: listOf() )

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
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll( connection = topBarScrollBehavior.nestedScrollConnection ),
            columns = GridCells.Adaptive(cellSize.width),
            state = state,
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            content = {
                items(
                    items= entities,
                    key = {item -> item.entity.id}) {
                    val progressObserver by vm.getCheckedList(it.entity.id).observeAsState(listOf())
                    EntityBox(vm, navController, cellSize, it, progressObserver, showPopUp, getEntityId)
                }
                item {
                    AddNewEntityBox(navController, cellSize)
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
              progressObserver: List<Boolean>,
              showPopUp: (Boolean) -> Unit,
              getEntityId: (Long) -> Unit
){
    val entityId by remember { mutableLongStateOf(jointEntity.entity.id) }
    var isExpanded: Boolean by remember { mutableStateOf(false) }

    val listSize = progressObserver.size
    val progress = progressObserver.count{ it }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
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
            DropDownContextMenu(
                vm = vm,
                navController = navController,
                isExpanded = isExpanded,
                size = gridSize,
                entityId = entityId,
                progressObserver = progressObserver,
                onValueChange = { isExpanded = it },
                showPopUp = showPopUp,
                getEntityId= getEntityId
            )

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
                ProgressIndicator(progress, listSize)
            } else {
                ProgressIndicator(progress, listSize)
                NoImageBox(progress, listSize)
            }

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
        visible = (progress == listSize),
        enter = fadeIn(),
        exit = fadeOut(),
        content = { Box( Modifier.fillMaxSize().alpha(0.5f).background(checkedDeepGreen) ) }
    )
    if (progress == listSize) {

        Row(
            modifier = Modifier.padding(bottom = 10.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Done, "Done",
                modifier = Modifier.size(15.dp),
                tint = Color.Green
            )
            Spacer(Modifier.size(5.dp))
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
fun NoImageBox(progress: Int, listSize: Int){

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when ( progress == listSize ){
                true  -> {
                    Icon(
                        painter = painterResource(R.drawable.image_icon_checked),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Green
                    )
                }
                false -> {
                    Icon(
                        painter = painterResource(R.drawable.image_icon_unchecked),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

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
                contentDescription = "ADD"
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            text     = stringResource(id = R.string.add_new_entity)
        )
    }
}
