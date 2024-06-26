package com.example.letscheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.compose.rememberNavController
import com.example.letscheck.data.User
import com.example.letscheck.navigation.NavGraph
import com.example.letscheck.ui.theme.LetsCheckTheme


// Подгружаем данные по умолчанию
val dataLoader = DataLoader()

// Создаем пользователя по умолчанию
val user = User(userName = "Анна")

// Выбор списка, пока вручную
//TODO потом переделать


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LetsCheckTheme {
                // константа для навигации между экранами
                val navController = rememberNavController()
                // выбранный список (по умолчанию = 0)
                var currentEntity by rememberSaveable { mutableIntStateOf(0) }
                //
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier) {
        Text(
            text = "Let's check",
            modifier = Modifier.padding(top = 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 6.em
        )
    }
}

