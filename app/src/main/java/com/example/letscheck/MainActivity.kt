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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.Theme
import com.example.letscheck.data.dao.Dao
import com.example.letscheck.data.MainDb
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.data.dao.SettingsDao
import com.example.letscheck.navigation.NavGraph
import com.example.letscheck.repository.ChecklistRepository
import com.example.letscheck.repository.SettingsRepository
import com.example.letscheck.viewModels.AddNewEntityViewModel
import com.example.letscheck.viewModels.CurrentEntityViewModel
import com.example.letscheck.viewModels.MainViewModel
import com.example.letscheck.viewModels.SettingsViewModel
import com.example.letscheck.viewModels.factory.AddNewEntityViewModelFactory
import com.example.letscheck.viewModels.factory.CurrentEntityViewModelFactory
import com.example.letscheck.viewModels.factory.MainViewModelFactory
import com.example.letscheck.viewModels.factory.SettingsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val database = MainDb.createDatabase(application)

            val userDao: Dao = database.dao()
            val settingsDao: SettingsDao = database.settingsDao()

            val settingsRepository = SettingsRepository(settingsDao)
            val mainRepository = ChecklistRepository(userDao)

            val vmScope = CoroutineScope(Dispatchers.Main)

            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val appContext = LocalContext.current.applicationContext as Application
                val settingsVM: SettingsViewModel =
                    viewModel(
                        it, "SettingsViewModel",
                        SettingsViewModelFactory(vmScope, settingsRepository)
                    )
                val mainVM: MainViewModel =
                    viewModel(
                        it, "MainViewModel",
                        MainViewModelFactory( vmScope, mainRepository, appContext )
                    )
                val currentVM: CurrentEntityViewModel =
                    viewModel(
                        it, "CurrentEntityViewModel",
                        CurrentEntityViewModelFactory ( vmScope, mainRepository, appContext )
                    )
                val addNewVM: AddNewEntityViewModel =
                    viewModel(
                        it,
                        "AddNewEntityViewModel",
                        AddNewEntityViewModelFactory( vmScope, mainRepository, appContext )
                    )
                App(settingsVM = settingsVM, mainVM = mainVM, currentVM = currentVM, addNewVM = addNewVM)
            }
        }
    }
}

@Composable
fun App(
    settingsVM: SettingsViewModel      = viewModel(),
    mainVM:     MainViewModel          = viewModel(),
    currentVM:  CurrentEntityViewModel = viewModel(),
    addNewVM:   AddNewEntityViewModel  = viewModel()
) {

    LaunchedEffect(settingsVM.settings) {
        settingsVM.getSettings()
    }

    Theme(settingsVM.settings) {

        val modifier = Modifier
        val navController = rememberNavController()

        Surface(
            modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            shape = RectangleShape
        ) {
            NavGraph(
                mainVM = mainVM,
                currentVM = currentVM,
                addNewVM = addNewVM,
                navController = navController,
                settings = settingsVM.settings,
                onSettingsChange = { settingsVM.updateTheme() ; settingsVM.getSettings() }
            )
        }
    }
}

