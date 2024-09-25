package com.example.letscheck.screens.addNewEntityScreen.composables

import android.content.ContentResolver
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.letscheck.R
import kotlinx.coroutines.delay


@Composable
fun PhotoPicker(selectedImageUri: Uri?, onUriChange: (Uri?) -> Unit) {

    var isExpanded: Boolean by remember { mutableStateOf(selectedImageUri != null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onUriChange(it)
            isExpanded = true
        }
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Crossfade(
            targetState = (selectedImageUri != null),
            modifier = Modifier,
            animationSpec = tween(durationMillis = 1000),
            label = ""
        ) { targetState ->
            Box(
                modifier = Modifier
                    .clip( RoundedCornerShape(20.dp) )
                    .clickable(onClick = {
                        onUriChange(null)
                        imagePickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                        onUriChange(selectedImageUri)
                    }
                    )
                    .animateContentSize()
                    .defaultMinSize(minWidth = 135.dp)
                    .fillMaxWidth( if (selectedImageUri == null || !isExpanded) 1f else 0f )
                    .height( if (selectedImageUri == null || !isExpanded) 60.dp else 240.dp ),
                contentAlignment = Alignment.Center
            ) {
                when (targetState) {
                    true  -> {
                        LaunchedEffect( isExpanded ) { delay(4000L) ; isExpanded = false }
                        PhotoBox(selectedImageUri)
                        ExpandButton(isExpanded) { isExpanded = it }
                    }
                    false -> { EmptyBox(selectedImageUri) }
                }
            }

        }

    }
}


@Composable
fun EmptyBox(selectedImageUri: Uri?) {
    AnimatedVisibility(
        visible = ( selectedImageUri == null ),
        enter = fadeIn() + scaleIn(),
        exit = fadeOut(tween(200)) + scaleOut()
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "pick an image",
                )
                HorizontalDivider(modifier = Modifier.width(10.dp), color = Color.Transparent)
                Text(
                    text = "выберите изображение",
                    textAlign = TextAlign.Center,
                )
            }
        }
}

@Composable
fun PhotoBox(selectedImageUri: Uri?) {
    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp)),
        model = selectedImageUri,
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ExpandButton(
    isExpanded: Boolean,
    onValueChange: (Boolean) -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = if (isExpanded) Alignment.TopEnd else Alignment.CenterEnd
    ) {
        Crossfade(
            targetState = isExpanded,
            animationSpec = tween(durationMillis = 500),
            label = ""
        ) { targetState ->

            IconButton(
                onClick = { onValueChange(!isExpanded) },
                modifier = Modifier.size(60.dp).padding(10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                when(targetState){
                    true  -> { Icon(Icons.Rounded.KeyboardArrowUp, "collapse") }
                    false -> { Icon(Icons.Rounded.KeyboardArrowDown, "expand") }
                }

            }
        }
    }
}



@Preview
@Composable
fun ExpandButtonPreview() {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    ExpandButton(isExpanded = isExpanded) { isExpanded = it}
}

@Preview
@Composable
fun PhotoBoxPreview() {
    val res = LocalContext.current.applicationContext.resources
    val image = R.drawable.fitness_bag_1
    val uriBuilder = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(res.getResourcePackageName(image))
        .appendPath(res.getResourceTypeName(image))
        .appendPath(res.getResourceEntryName(image))
        .build()
    Column(modifier = Modifier.fillMaxSize()) {
    Box{ PhotoBox(uriBuilder) }
    }
}