package com.example.letscheck.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFFC02D5D)

val MainGradientColors = listOf(Color.Cyan, Color.Magenta)
val MainBackgroundColor = Color(0xFF0F1932)
val MainTextColor = Color(0xFFE1E1E1)
val MainCheckedColor = Color(0xFF50E632)
val MainLineColor = Color(0xFF78AAC8)

val SecondaryBackgroundColor = Color(0xFF1C2D5E)
val SecondaryTextColor = Color(0xFF00BCD4)

val ThirdBackgroundColor = Color(0xFF111E3C)

val onMainButtonColors = ButtonColors(
                                      containerColor = SecondaryBackgroundColor,
                                      contentColor = MainTextColor,
                                      disabledContainerColor = MainBackgroundColor,
                                      disabledContentColor = MainTextColor
                                     )
val onMainIconButtonColors = IconButtonColors(
                                              containerColor = SecondaryBackgroundColor,
                                              contentColor = MainTextColor,
                                              disabledContainerColor = Color.DarkGray,
                                              disabledContentColor = MainBackgroundColor
                                             )