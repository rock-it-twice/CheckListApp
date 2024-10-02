package com.example.letscheck.screens.chooseUserActivityScreen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Title(mainTitle: String, isListChecked: Boolean) {

    val color = if (isListChecked) Color.Green else MaterialTheme.colorScheme.primary

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        color = color,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold,
        text = mainTitle,
    )
}