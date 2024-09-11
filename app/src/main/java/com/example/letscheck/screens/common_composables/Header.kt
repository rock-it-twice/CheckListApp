package com.example.letscheck.screens.common_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.R
import com.example.letscheck.navigation.Routes


@Composable
fun Header(navController: NavController, vm: MainViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(text = "Let's check!",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta)))
            )
        when(navController.currentDestination?.route){
            Routes.Home.route ->   { CancelChoiceButton(vm = vm) }
            else -> {}
        }

    }
}


@Composable
fun CancelChoiceButton(vm: MainViewModel){

    var isGoBackButtonVisible by remember { mutableStateOf(false) }
    isGoBackButtonVisible = ( vm.currentJointEntity != null || vm.currentJointUserActivity != null )
//    val buttonColors = ButtonDefaults.buttonColors(
//        containerColor = SecondaryBackgroundColor,
//        contentColor = MainWhiteColor)

    AnimatedVisibility(
        visible = isGoBackButtonVisible
    ) {
        Row(modifier = Modifier.clickable(onClick = { vm.clearStepByStep() }),
            verticalAlignment = Alignment.CenterVertically) {
            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = { vm.clearStepByStep() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "go back")
                Text(
                    text = stringResource(id = R.string.back),
                    fontSize = 18.sp
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun GoBackButtonPreview(){
//    CancelChoiceButton(){
//
//    }
//}