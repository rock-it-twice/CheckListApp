package com.example.letscheck

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.letscheck.screens.ChooseUserScreen
import com.example.letscheck.screens.CreateUserScreen
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainTextColor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val viewModel: CheckListViewModel =
                    viewModel(
                        it,
                        "CheckListViewModel",
                        CheckListViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                Main(viewModel)
            }
        }
    }
}

@Composable
fun Main(vm: CheckListViewModel = viewModel()) {
    val modifier = Modifier
    // константа для навигации между экранами
    val navController = rememberNavController()
    Surface(
        modifier.fillMaxSize(),
        shape = RectangleShape,
        color = MainBackgroundColor,
        contentColor = MainTextColor
    ) {
//      CreateUserScreen(navController, vm)
        ChooseUserScreen(navController, vm)
    }
}