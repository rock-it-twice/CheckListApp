package com.example.letscheck.screens.mainScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letscheck.R

@Composable
fun EmptyFolderSurface( onCreate: () -> Unit ){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.empty_folder_surface_title),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.empty_folder_surface_message),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.size(10.dp))
            Button(onClick = { onCreate() }) {
                Text(text = stringResource(R.string.empty_folder_surface_create))
            }
        }

    }
}