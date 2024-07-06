package com.example.letscheck.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.ui.theme.MainGradientColors
import com.example.letscheck.ui.theme.Typography

@Composable
fun Header() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(text = "Let's check!",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(Brush.linearGradient(colors = MainGradientColors))
            )
    }
}