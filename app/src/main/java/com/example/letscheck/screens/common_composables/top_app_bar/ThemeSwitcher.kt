package com.example.letscheck.screens.common_composables.top_app_bar

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letscheck.R

@Composable
fun ThemeSwitcher(){

    val sharedPref = LocalContext.current.getSharedPreferences("main_prefs", Context.MODE_PRIVATE)

    var darkModeEnabled by remember {
        mutableStateOf(sharedPref.getBoolean("dark_mode", false))
    }

    Switch(
        checked = darkModeEnabled,
        onCheckedChange = {
            with(sharedPref.edit()){
                putBoolean("dark_mode", it)
                apply()
            }
            darkModeEnabled = sharedPref.getBoolean("dark_mode", it)
            println(sharedPref.getBoolean("dark_mode", false))
        },
        thumbContent = {
            val size = 12.dp
            if (darkModeEnabled)
                Icon(
                    painter            = painterResource(R.drawable.dark_mode_24dp),
                    contentDescription = "dark_mode",
                    modifier           = Modifier.size(size)
                )

            else
                Icon(
                    painter            = painterResource(R.drawable.light_mode_24dp),
                    contentDescription = "light_mode",
                    modifier           = Modifier.size(size)
                )
        }
    )
}


@Preview
@Composable
fun ThemeSwitcherPreview(){
    ThemeSwitcher()
}