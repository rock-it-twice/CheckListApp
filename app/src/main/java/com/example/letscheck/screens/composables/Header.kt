package com.example.letscheck.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.ui.theme.MainGradientColors
import com.example.letscheck.ui.theme.Typography

@Composable
fun Header(vm: CheckListViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(text = "Let's check!",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(Brush.linearGradient(colors = MainGradientColors))
            )
        ClearEntityButton(vm = vm)
    }
}

@Composable
fun ClearEntityButton(vm: CheckListViewModel){
    var isGoBackButtonVisible by remember { mutableStateOf(false) }
    isGoBackButtonVisible = vm.currentEntity != null || vm.currentUser != null
    AnimatedVisibility(visible = isGoBackButtonVisible) {
        Row(modifier = Modifier.clickable(onClick = { vm.clearStepByStep() }),
            verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { vm.clearStepByStep() }) {
                Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "go back")
            }
            Text(
                text = "back",
                fontSize = 18.sp
            )
        }
    }
}