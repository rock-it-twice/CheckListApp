package com.example.letscheck.screens.addNewEntityScreen.composables

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
import androidx.navigation.NavController
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.AddNewEntityViewModel

@Composable
fun Header(navController: NavController, addNewVM: AddNewEntityViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Let's check!",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta)))
        )
        when(navController.currentDestination?.route){
            Routes.Home.route -> {}
            else -> {}
        }

    }
}