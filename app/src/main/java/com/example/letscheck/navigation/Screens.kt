package com.example.letscheck.navigation

sealed class Screens (val route: String) {
    data object Home: Screens("home")
    data object CheckListScreen: Screens("check_list")
    data object CreateUserScreen: Screens("create_user")
    data object ChooseUserScreen: Screens("choose_user")
}