package com.example.letscheck.screens.common_composables.top_app_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.letscheck.data.classes.main.AppSettings

@Composable
fun SettingsButton(settings: AppSettings, onSettingsChange: ()-> Unit){

    var isVisible by rememberSaveable { mutableStateOf(false) }
    Row {

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally( initialOffsetX = { it / 2 } ),
            exit = slideOutHorizontally( targetOffsetX  = { it / 2 } )
        ) {
            ThemeSwitcher(settings, onSettingsChange)
        }
        // Гамбургер меню
        IconButton(
            onClick = { isVisible = !isVisible }
        ) {
            Icon(
                Icons.Default.MoreVert, "Settings",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}