package com.example.letscheck.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary     = Color(0xFF0F1932),
    secondary   = Color(0xFF1C2D5E),
    tertiary    = Color(0xFF111E3C),
    onPrimary   = Color(0xFFE1E1E1),
    onSecondary = Color(0xFF70A4AB),
    onTertiary  = Color(0xFFE1E1E1),
    background  = Color(0xFF0F1932)
)

private val LightColorScheme = lightColorScheme(
    primary     = Color(0xFFE1E1E1),
    secondary   = Color(0xFF5E8893),
    tertiary    = Color(0xFF3E5962),
    onPrimary   = Color(0xFF707070),
    onSecondary = Color(0xFFA9A9A9),
    onTertiary  = Color(0xFFE1E1E1),
    background  = Color(0xFFE1E1E1)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun LetsCheckTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}