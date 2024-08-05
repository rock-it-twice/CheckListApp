package com.example.letscheck.navigation

sealed class Routes (val route: String) {
    data object Home: Routes("home")
    data object AddNewEntityScreen: Routes("add_new_entity")
}