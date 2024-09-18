package com.example.letscheck

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.LetsCheckTheme
import com.example.letscheck.data.Dao
import com.example.letscheck.data.MainDb
import com.example.letscheck.navigation.NavGraph
import com.example.letscheck.repository.ChecklistRepository
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.viewModels.factory.AddNewEntityViewModelFactory
import com.example.letscheck.viewModels.factory.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val database = MainDb.createDatabase(application)
            val userDao: Dao = database.dao()
            val repository = ChecklistRepository(userDao)
            val vmScope = CoroutineScope(Dispatchers.Main)

            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val appContext = LocalContext.current.applicationContext as Application
                val mainVM: MainViewModel =
                    viewModel(
                        it, "MainViewModel",
                        MainViewModelFactory( vmScope, repository, appContext)
                    )
                val addNewVM: AddNewEntityViewModel =
                    viewModel(
                        it,
                        "AddNewEntityViewModel",
                        AddNewEntityViewModelFactory( vmScope, repository, appContext)
                    )
                App(mainVM = mainVM, addNewVM = addNewVM)
            }
        }
    }
}

@Composable
fun App(mainVM: MainViewModel = viewModel(), addNewVM: AddNewEntityViewModel = viewModel()) {
    LetsCheckTheme {
        val modifier = Modifier
        // константа для навигации между экранами
        val navController = rememberNavController()
        Surface(
            modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            shape = RectangleShape
        ) {
            NavGraph(mainVM = mainVM, addNewVM = addNewVM, navController = navController)
        }
    }
}

