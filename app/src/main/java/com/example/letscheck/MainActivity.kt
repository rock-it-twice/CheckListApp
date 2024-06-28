package com.example.letscheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.compose.rememberNavController
import com.example.letscheck.data.DataLoader
import com.example.letscheck.navigation.NavGraph
import com.example.letscheck.ui.theme.LetsCheckTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // Инициализация экземпляра DAO, для взаимодействия с БД
        val userDao = App
        // Подгружаем данные по умолчанию
        DataLoader(userDao)




        enableEdgeToEdge()
        setContent {

            LetsCheckTheme {
                // константа для навигации между экранами
                val navController = rememberNavController()
                NavGraph(userDao = userDao, navController = navController)
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