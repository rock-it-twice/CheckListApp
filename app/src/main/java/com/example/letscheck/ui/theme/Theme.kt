package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.letscheck.ui.theme.backgroundDark
import com.example.letscheck.ui.theme.backgroundDarkHighContrast
import com.example.letscheck.ui.theme.backgroundDarkMediumContrast
import com.example.letscheck.ui.theme.backgroundLight
import com.example.letscheck.ui.theme.backgroundLightHighContrast
import com.example.letscheck.ui.theme.backgroundLightMediumContrast
import com.example.letscheck.ui.theme.errorContainerDark
import com.example.letscheck.ui.theme.errorContainerDarkHighContrast
import com.example.letscheck.ui.theme.errorContainerDarkMediumContrast
import com.example.letscheck.ui.theme.errorContainerLight
import com.example.letscheck.ui.theme.errorContainerLightHighContrast
import com.example.letscheck.ui.theme.errorContainerLightMediumContrast
import com.example.letscheck.ui.theme.errorDark
import com.example.letscheck.ui.theme.errorDarkHighContrast
import com.example.letscheck.ui.theme.errorDarkMediumContrast
import com.example.letscheck.ui.theme.errorLight
import com.example.letscheck.ui.theme.errorLightHighContrast
import com.example.letscheck.ui.theme.errorLightMediumContrast
import com.example.letscheck.ui.theme.inverseOnSurfaceDark
import com.example.letscheck.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.letscheck.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.letscheck.ui.theme.inverseOnSurfaceLight
import com.example.letscheck.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.letscheck.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.letscheck.ui.theme.inversePrimaryDark
import com.example.letscheck.ui.theme.inversePrimaryDarkHighContrast
import com.example.letscheck.ui.theme.inversePrimaryDarkMediumContrast
import com.example.letscheck.ui.theme.inversePrimaryLight
import com.example.letscheck.ui.theme.inversePrimaryLightHighContrast
import com.example.letscheck.ui.theme.inversePrimaryLightMediumContrast
import com.example.letscheck.ui.theme.inverseSurfaceDark
import com.example.letscheck.ui.theme.inverseSurfaceDarkHighContrast
import com.example.letscheck.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.letscheck.ui.theme.inverseSurfaceLight
import com.example.letscheck.ui.theme.inverseSurfaceLightHighContrast
import com.example.letscheck.ui.theme.inverseSurfaceLightMediumContrast
import com.example.letscheck.ui.theme.onBackgroundDark
import com.example.letscheck.ui.theme.onBackgroundDarkHighContrast
import com.example.letscheck.ui.theme.onBackgroundDarkMediumContrast
import com.example.letscheck.ui.theme.onBackgroundLight
import com.example.letscheck.ui.theme.onBackgroundLightHighContrast
import com.example.letscheck.ui.theme.onBackgroundLightMediumContrast
import com.example.letscheck.ui.theme.onErrorContainerDark
import com.example.letscheck.ui.theme.onErrorContainerDarkHighContrast
import com.example.letscheck.ui.theme.onErrorContainerDarkMediumContrast
import com.example.letscheck.ui.theme.onErrorContainerLight
import com.example.letscheck.ui.theme.onErrorContainerLightHighContrast
import com.example.letscheck.ui.theme.onErrorContainerLightMediumContrast
import com.example.letscheck.ui.theme.onErrorDark
import com.example.letscheck.ui.theme.onErrorDarkHighContrast
import com.example.letscheck.ui.theme.onErrorDarkMediumContrast
import com.example.letscheck.ui.theme.onErrorLight
import com.example.letscheck.ui.theme.onErrorLightHighContrast
import com.example.letscheck.ui.theme.onErrorLightMediumContrast
import com.example.letscheck.ui.theme.onPrimaryContainerDark
import com.example.letscheck.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.onPrimaryContainerLight
import com.example.letscheck.ui.theme.onPrimaryContainerLightHighContrast
import com.example.letscheck.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.onPrimaryDark
import com.example.letscheck.ui.theme.onPrimaryDarkHighContrast
import com.example.letscheck.ui.theme.onPrimaryDarkMediumContrast
import com.example.letscheck.ui.theme.onPrimaryLight
import com.example.letscheck.ui.theme.onPrimaryLightHighContrast
import com.example.letscheck.ui.theme.onPrimaryLightMediumContrast
import com.example.letscheck.ui.theme.onSecondaryContainerDark
import com.example.letscheck.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.onSecondaryContainerLight
import com.example.letscheck.ui.theme.onSecondaryContainerLightHighContrast
import com.example.letscheck.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.onSecondaryDark
import com.example.letscheck.ui.theme.onSecondaryDarkHighContrast
import com.example.letscheck.ui.theme.onSecondaryDarkMediumContrast
import com.example.letscheck.ui.theme.onSecondaryLight
import com.example.letscheck.ui.theme.onSecondaryLightHighContrast
import com.example.letscheck.ui.theme.onSecondaryLightMediumContrast
import com.example.letscheck.ui.theme.onSurfaceDark
import com.example.letscheck.ui.theme.onSurfaceDarkHighContrast
import com.example.letscheck.ui.theme.onSurfaceDarkMediumContrast
import com.example.letscheck.ui.theme.onSurfaceLight
import com.example.letscheck.ui.theme.onSurfaceLightHighContrast
import com.example.letscheck.ui.theme.onSurfaceLightMediumContrast
import com.example.letscheck.ui.theme.onSurfaceVariantDark
import com.example.letscheck.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.letscheck.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.letscheck.ui.theme.onSurfaceVariantLight
import com.example.letscheck.ui.theme.onSurfaceVariantLightHighContrast
import com.example.letscheck.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.letscheck.ui.theme.onTertiaryContainerDark
import com.example.letscheck.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.onTertiaryContainerLight
import com.example.letscheck.ui.theme.onTertiaryContainerLightHighContrast
import com.example.letscheck.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.onTertiaryDark
import com.example.letscheck.ui.theme.onTertiaryDarkHighContrast
import com.example.letscheck.ui.theme.onTertiaryDarkMediumContrast
import com.example.letscheck.ui.theme.onTertiaryLight
import com.example.letscheck.ui.theme.onTertiaryLightHighContrast
import com.example.letscheck.ui.theme.onTertiaryLightMediumContrast
import com.example.letscheck.ui.theme.outlineDark
import com.example.letscheck.ui.theme.outlineDarkHighContrast
import com.example.letscheck.ui.theme.outlineDarkMediumContrast
import com.example.letscheck.ui.theme.outlineLight
import com.example.letscheck.ui.theme.outlineLightHighContrast
import com.example.letscheck.ui.theme.outlineLightMediumContrast
import com.example.letscheck.ui.theme.outlineVariantDark
import com.example.letscheck.ui.theme.outlineVariantDarkHighContrast
import com.example.letscheck.ui.theme.outlineVariantDarkMediumContrast
import com.example.letscheck.ui.theme.outlineVariantLight
import com.example.letscheck.ui.theme.outlineVariantLightHighContrast
import com.example.letscheck.ui.theme.outlineVariantLightMediumContrast
import com.example.letscheck.ui.theme.primaryContainerDark
import com.example.letscheck.ui.theme.primaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.primaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.primaryContainerLight
import com.example.letscheck.ui.theme.primaryContainerLightHighContrast
import com.example.letscheck.ui.theme.primaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.primaryDark
import com.example.letscheck.ui.theme.primaryDarkHighContrast
import com.example.letscheck.ui.theme.primaryDarkMediumContrast
import com.example.letscheck.ui.theme.primaryLight
import com.example.letscheck.ui.theme.primaryLightHighContrast
import com.example.letscheck.ui.theme.primaryLightMediumContrast
import com.example.letscheck.ui.theme.scrimDark
import com.example.letscheck.ui.theme.scrimDarkHighContrast
import com.example.letscheck.ui.theme.scrimDarkMediumContrast
import com.example.letscheck.ui.theme.scrimLight
import com.example.letscheck.ui.theme.scrimLightHighContrast
import com.example.letscheck.ui.theme.scrimLightMediumContrast
import com.example.letscheck.ui.theme.secondaryContainerDark
import com.example.letscheck.ui.theme.secondaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.secondaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.secondaryContainerLight
import com.example.letscheck.ui.theme.secondaryContainerLightHighContrast
import com.example.letscheck.ui.theme.secondaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.secondaryDark
import com.example.letscheck.ui.theme.secondaryDarkHighContrast
import com.example.letscheck.ui.theme.secondaryDarkMediumContrast
import com.example.letscheck.ui.theme.secondaryLight
import com.example.letscheck.ui.theme.secondaryLightHighContrast
import com.example.letscheck.ui.theme.secondaryLightMediumContrast
import com.example.letscheck.ui.theme.surfaceBrightDark
import com.example.letscheck.ui.theme.surfaceBrightDarkHighContrast
import com.example.letscheck.ui.theme.surfaceBrightDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceBrightLight
import com.example.letscheck.ui.theme.surfaceBrightLightHighContrast
import com.example.letscheck.ui.theme.surfaceBrightLightMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerDark
import com.example.letscheck.ui.theme.surfaceContainerDarkHighContrast
import com.example.letscheck.ui.theme.surfaceContainerDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerHighDark
import com.example.letscheck.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.letscheck.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerHighLight
import com.example.letscheck.ui.theme.surfaceContainerHighLightHighContrast
import com.example.letscheck.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerHighestDark
import com.example.letscheck.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.letscheck.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerHighestLight
import com.example.letscheck.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.letscheck.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerLight
import com.example.letscheck.ui.theme.surfaceContainerLightHighContrast
import com.example.letscheck.ui.theme.surfaceContainerLightMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerLowDark
import com.example.letscheck.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.letscheck.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerLowLight
import com.example.letscheck.ui.theme.surfaceContainerLowLightHighContrast
import com.example.letscheck.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerLowestDark
import com.example.letscheck.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.letscheck.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceContainerLowestLight
import com.example.letscheck.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.letscheck.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.letscheck.ui.theme.surfaceDark
import com.example.letscheck.ui.theme.surfaceDarkHighContrast
import com.example.letscheck.ui.theme.surfaceDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceDimDark
import com.example.letscheck.ui.theme.surfaceDimDarkHighContrast
import com.example.letscheck.ui.theme.surfaceDimDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceDimLight
import com.example.letscheck.ui.theme.surfaceDimLightHighContrast
import com.example.letscheck.ui.theme.surfaceDimLightMediumContrast
import com.example.letscheck.ui.theme.surfaceLight
import com.example.letscheck.ui.theme.surfaceLightHighContrast
import com.example.letscheck.ui.theme.surfaceLightMediumContrast
import com.example.letscheck.ui.theme.surfaceVariantDark
import com.example.letscheck.ui.theme.surfaceVariantDarkHighContrast
import com.example.letscheck.ui.theme.surfaceVariantDarkMediumContrast
import com.example.letscheck.ui.theme.surfaceVariantLight
import com.example.letscheck.ui.theme.surfaceVariantLightHighContrast
import com.example.letscheck.ui.theme.surfaceVariantLightMediumContrast
import com.example.letscheck.ui.theme.tertiaryContainerDark
import com.example.letscheck.ui.theme.tertiaryContainerDarkHighContrast
import com.example.letscheck.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.letscheck.ui.theme.tertiaryContainerLight
import com.example.letscheck.ui.theme.tertiaryContainerLightHighContrast
import com.example.letscheck.ui.theme.tertiaryContainerLightMediumContrast
import com.example.letscheck.ui.theme.tertiaryDark
import com.example.letscheck.ui.theme.tertiaryDarkHighContrast
import com.example.letscheck.ui.theme.tertiaryDarkMediumContrast
import com.example.letscheck.ui.theme.tertiaryLight
import com.example.letscheck.ui.theme.tertiaryLightHighContrast
import com.example.letscheck.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

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

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
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
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else      -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

