package com.example.letscheck

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.LetsCheckTheme
import com.example.letscheck.navigation.NavGraph
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.viewModels.factories.MainViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val viewModel: MainViewModel =
                    viewModel(
                        it,
                        "MainViewModel",
                        MainViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                App(viewModel)
            }
        }
    }
}

@Composable
fun App(vm: MainViewModel = viewModel()) {
    LetsCheckTheme {
        val modifier = Modifier
        // константа для навигации между экранами
        val navController = rememberNavController()
        Surface(
            modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            shape = RectangleShape
        ) {
            NavGraph( vm = vm, navController = navController )
        }
    }
}

