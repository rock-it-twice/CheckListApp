package com.example.letscheck.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.letscheck.CheckListViewModel
import com.example.letscheck.data.classes.User
import com.example.letscheck.screens.composables.Header
import com.example.letscheck.ui.theme.Typography

@Composable
fun CreateUserScreen(navController: NavController, vm: CheckListViewModel = viewModel()) {

    val userList by vm.users.observeAsState(initial = listOf())

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(vm)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = vm.userName,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .weight(0.75F, true),
                    onValueChange = { vm.changeName(it) },
                    placeholder = { Text(text = "Enter your name")})
                Button(
                    onClick = { vm.addUser() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier
                        .weight(0.25F, true)
                        .fillMaxHeight()

                ) {
                    Text(
                        text = "+",
                        fontSize = 24.sp,
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            UserList(users = userList, delete = { vm.deleteUser(it) })
        }
    }
}

@Composable
fun UserList(users: List<User>, delete: (Int) -> Unit) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item { UserTitleRow() }
        items(users) { user -> UserRow(user, { delete(user.id) }) }

    }
}

@Composable
fun UserRow(user: User, delete:(Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = user.id.toString(),
            style = Typography.titleMedium
            )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = user.userName,
            style = Typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "delete",
            Modifier.clickable { delete(user.id) },
            style = Typography.titleMedium,
            color = Color.Blue
        )
    }
}

@Composable
fun UserTitleRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "Id", style = Typography.titleLarge)
        Text(text = "Name", style = Typography.titleLarge)
        Text(text = "       ", style = Typography.titleLarge)
    }

}