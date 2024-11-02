package com.example.compose
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.letscheck.data.classes.main.AppSettings
import com.example.letscheck.data.classes.main.AppTheme
import com.example.letscheck.ui.theme.backgroundDark
import com.example.letscheck.ui.theme.backgroundLight
import com.example.letscheck.ui.theme.errorContainerDark
import com.example.letscheck.ui.theme.errorContainerLight
import com.example.letscheck.ui.theme.errorDark
import com.example.letscheck.ui.theme.errorLight
import com.example.letscheck.ui.theme.inverseOnSurfaceDark
import com.example.letscheck.ui.theme.inverseOnSurfaceLight
import com.example.letscheck.ui.theme.inversePrimaryDark
import com.example.letscheck.ui.theme.inversePrimaryLight
import com.example.letscheck.ui.theme.inverseSurfaceDark
import com.example.letscheck.ui.theme.inverseSurfaceLight
import com.example.letscheck.ui.theme.onBackgroundDark
import com.example.letscheck.ui.theme.onBackgroundLight
import com.example.letscheck.ui.theme.onErrorContainerDark
import com.example.letscheck.ui.theme.onErrorContainerLight
import com.example.letscheck.ui.theme.onErrorDark
import com.example.letscheck.ui.theme.onErrorLight
import com.example.letscheck.ui.theme.onPrimaryContainerDark
import com.example.letscheck.ui.theme.onPrimaryContainerLight
import com.example.letscheck.ui.theme.onPrimaryDark
import com.example.letscheck.ui.theme.onPrimaryLight
import com.example.letscheck.ui.theme.onSecondaryContainerDark
import com.example.letscheck.ui.theme.onSecondaryContainerLight
import com.example.letscheck.ui.theme.onSecondaryDark
import com.example.letscheck.ui.theme.onSecondaryLight
import com.example.letscheck.ui.theme.onSurfaceDark
import com.example.letscheck.ui.theme.onSurfaceLight
import com.example.letscheck.ui.theme.onSurfaceVariantDark
import com.example.letscheck.ui.theme.onSurfaceVariantLight
import com.example.letscheck.ui.theme.onTertiaryContainerDark
import com.example.letscheck.ui.theme.onTertiaryContainerLight
import com.example.letscheck.ui.theme.onTertiaryDark
import com.example.letscheck.ui.theme.onTertiaryLight
import com.example.letscheck.ui.theme.outlineDark
import com.example.letscheck.ui.theme.outlineLight
import com.example.letscheck.ui.theme.outlineVariantDark
import com.example.letscheck.ui.theme.outlineVariantLight
import com.example.letscheck.ui.theme.primaryContainerDark
import com.example.letscheck.ui.theme.primaryContainerLight
import com.example.letscheck.ui.theme.primaryDark
import com.example.letscheck.ui.theme.primaryLight
import com.example.letscheck.ui.theme.scrimDark
import com.example.letscheck.ui.theme.scrimLight
import com.example.letscheck.ui.theme.secondaryContainerDark
import com.example.letscheck.ui.theme.secondaryContainerLight
import com.example.letscheck.ui.theme.secondaryDark
import com.example.letscheck.ui.theme.secondaryLight
import com.example.letscheck.ui.theme.surfaceBrightDark
import com.example.letscheck.ui.theme.surfaceBrightLight
import com.example.letscheck.ui.theme.surfaceContainerDark
import com.example.letscheck.ui.theme.surfaceContainerHighDark
import com.example.letscheck.ui.theme.surfaceContainerHighLight
import com.example.letscheck.ui.theme.surfaceContainerHighestDark
import com.example.letscheck.ui.theme.surfaceContainerHighestLight
import com.example.letscheck.ui.theme.surfaceContainerLight
import com.example.letscheck.ui.theme.surfaceContainerLowDark
import com.example.letscheck.ui.theme.surfaceContainerLowLight
import com.example.letscheck.ui.theme.surfaceContainerLowestDark
import com.example.letscheck.ui.theme.surfaceContainerLowestLight
import com.example.letscheck.ui.theme.surfaceDark
import com.example.letscheck.ui.theme.surfaceDimDark
import com.example.letscheck.ui.theme.surfaceDimLight
import com.example.letscheck.ui.theme.surfaceLight
import com.example.letscheck.ui.theme.surfaceVariantDark
import com.example.letscheck.ui.theme.surfaceVariantLight
import com.example.letscheck.ui.theme.tertiaryContainerDark
import com.example.letscheck.ui.theme.tertiaryContainerLight
import com.example.letscheck.ui.theme.tertiaryDark
import com.example.letscheck.ui.theme.tertiaryLight
import com.example.letscheck.viewModels.SettingsViewModel
import com.example.ui.theme.AppTypography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)


@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
 fun LetsCheckTheme(
    settingsVM: SettingsViewModel,
    content: @Composable() () -> Unit
 ) {

     val settings by settingsVM.settings.observeAsState(AppSettings())

//    val sharedPref = LocalContext.current.getSharedPreferences("main_prefs", Context.MODE_PRIVATE)
//
//    if (!sharedPref.contains("dark_mode")) {
//        sharedPref.edit().putBoolean("dark_mode", false).apply()
//    }
//
//    var darkModeEnabled: Boolean by remember {
//        mutableStateOf(sharedPref.getBoolean("dark_mode", false))
//    }
//
//    LaunchedEffect(key1 = Unit) {
//        sharedPref.registerOnSharedPreferenceChangeListener {
//            _, key -> if (key == "dark_mode") {
//                darkModeEnabled = sharedPref.getBoolean("dark_mode", false)
//            }
//        }
//    }

    val colorScheme = when(settings.appTheme) {
             AppTheme.DARK   -> darkScheme
             AppTheme.LIGHT  -> lightScheme
        }

    MaterialTheme(
      colorScheme = colorScheme,
      typography  = AppTypography,
      content     = content
    )

}

