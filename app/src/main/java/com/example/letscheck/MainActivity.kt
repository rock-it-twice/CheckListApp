package com.example.letscheck

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.letscheck.data.classes.input.UserActivity
import com.example.letscheck.data.classes.output.JointCheckList
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.data.classes.output.JointUserActivity
import com.example.letscheck.screens.ChooseUserScreen.ChooseUserScreen
import com.example.letscheck.screens.UserRow
import com.example.letscheck.screens.UserTitleRow
import com.example.letscheck.ui.theme.MainBackgroundColor
import com.example.letscheck.ui.theme.MainTextColor
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
    val modifier = Modifier
    // константа для навигации между экранами
    val navController = rememberNavController()
    Surface(
        modifier.fillMaxSize(),
        shape = RectangleShape,
        color = MainBackgroundColor,
        contentColor = MainTextColor
    ) {
        ChooseUserScreen(navController, vm)
    }
}

