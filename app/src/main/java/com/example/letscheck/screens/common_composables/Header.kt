package com.example.letscheck.screens.common_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.navigation.Routes
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel
import com.example.letscheck.viewModels.MainViewModel


@Composable
fun Header(
    navController: NavController,
    mainVM: MainViewModel? = null,
    currentVM: CurrentEntityViewModel? = null,
    AddNewVM: AddNewEntityViewModel? = null
) {

    Row(
        modifier = Modifier
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
            Routes.Home.route                -> { GoBackButton(vm = mainVM) }
            Routes.CurrentEntityScreen.route -> { GoBackButton(vm = currentVM, navController) }
            Routes.AddNewEntityScreen.route  -> { GoBackButton(vm = AddNewVM, navController) }
            else -> {}
        }

    }
}


@Composable
fun GoBackButton(vm: MainViewModel?){

val visible = vm?.currentJointUserActivity != null

    AnimatedVisibility(
        visible = visible
    ) {
        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = { vm?.clear() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "go back"
            )
            Text(
                text = stringResource(id = R.string.back),
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun GoBackButton(vm: CurrentEntityViewModel?, navController: NavController){

    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            navController.navigate(route = Routes.Home.route)
            vm?.clear()
                  },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "go back"
        )
        Text(
            text = stringResource(id = R.string.back),
            fontSize = 18.sp
        )
    }

}


@Composable
fun GoBackButton(vm: AddNewEntityViewModel?, navController: NavController){

    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            navController.navigate(route = Routes.Home.route)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "go back"
        )
        Text(
            text = stringResource(id = R.string.back),
            fontSize = 18.sp
        )
    }
}
