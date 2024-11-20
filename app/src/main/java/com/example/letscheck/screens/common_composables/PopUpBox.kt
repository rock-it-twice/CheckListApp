package com.example.letscheck.screens.common_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.letscheck.R
import com.example.letscheck.navigation.Routes

@Composable
fun PopUpBox(showPopUp: Boolean,
             size: DpSize,
             onDismiss: (Boolean) -> Unit,
             content: @Composable ()-> Unit
){
    AnimatedVisibility(
        visible = showPopUp,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.75f)
                .background(MaterialTheme.colorScheme.background)
                .shadow(elevation = 10.dp),
            contentAlignment = Alignment.Center
        ){
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(excludeFromSystemGesture = true),
                onDismissRequest = { onDismiss(!showPopUp) },
                content = {
                    Box(
                        modifier = Modifier
                            .animateEnterExit(scaleIn(), scaleOut())
                            .size(size)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(40.dp),
                        contentAlignment = Alignment.Center,
                        content = { content() }
                    )
                }
            )
        }
    }
}

@Composable
fun DeleteWarningPopUp(onClose: (Boolean) -> Unit, delete: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_delete_warning_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Сообщение
        Text(
            text = stringResource(R.string.pop_up_delete_warning_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(10.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { delete() ; onClose( false ) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_warning_accept))
            }
            Button(
                onClick = { onClose( false ) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_warning_decline))
            }
        }
        Spacer(Modifier.size(10.dp))

    }
}

@Composable
fun GoToMainScreenPopUp(navController: NavController, onClose: (Boolean) -> Unit){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок
            Text(
                text = stringResource(R.string.pop_up_complete_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(20.dp))
            // Сообщение
            Text(
                text = stringResource(R.string.pop_up_complete_message),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(10.dp))
            // Кнопки
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { navController.navigate(route = Routes.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(stringResource(R.string.pop_up_complete_accept))
                }
                Button(
                    onClick = { onClose( false ) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(stringResource(R.string.pop_up_complete_decline))
                }
            }
            Spacer(Modifier.size(10.dp))
        }
}

@Composable
fun NewEntityPopUp(navController: NavController, onAccept: () -> Unit, onDecline: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_new_entity_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Сообщение
        Text(
            text = stringResource(R.string.pop_up_new_entity_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(10.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    onAccept()
                    navController.navigate(route = Routes.AddNewEntityScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_new_entity_accept))
            }
            Button(
                onClick = {
                    onDecline()
                    navController.navigate(route = Routes.AddNewEntityScreen.route)
                          },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_new_entity_decline))
            }
        }
        Spacer(Modifier.size(10.dp))
    }

}

@Composable
fun DeleteCheckListPopUp(listIndex: Int, onAccept: (Int) -> Unit, onDecline: (Boolean) -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = stringResource(R.string.pop_up_delete_check_list_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(20.dp))
        // Кнопки
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    onAccept(listIndex)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_check_list_accept))
            }
            Button(
                onClick = {
                    onDecline(false)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(stringResource(R.string.pop_up_delete_warning_decline))
            }
        }
        Spacer(Modifier.size(10.dp))
    }

}