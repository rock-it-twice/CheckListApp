package com.example.letscheck.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFFC02D5D)


val MainGradientColors = listOf(Color.Cyan, Color.Magenta)
val MainBackgroundColor = Color(0xFF0F1932)
val MainWhiteColor = Color(0xFFE1E1E1)
val MainGreenColor = Color(0xFF46A537)
val MainLineColor = Color(0xFF78AAC8)

val SecondaryBackgroundColor = Color(0xFF1C2D5E)
val SecondaryTextColor = Color(0xFF00BCD4)

val TertiaryBackgroundColor = Color(0xFF111E3C)

val onMainButtonColors = ButtonColors(
                                      containerColor = SecondaryBackgroundColor,
                                      contentColor = MainWhiteColor,
                                      disabledContainerColor = MainBackgroundColor,
                                      disabledContentColor = MainWhiteColor
                                     )

val onMainAddCheckboxButtonColors = ButtonColors(
        containerColor = MainGreenColor,
        contentColor = MainWhiteColor,
        disabledContainerColor = Color.DarkGray,
        disabledContentColor = Color.LightGray
)

val onMainIconButtonColors = IconButtonColors(
                                              containerColor = SecondaryBackgroundColor,
                                              contentColor = MainWhiteColor,
                                              disabledContainerColor = MainBackgroundColor,
                                              disabledContentColor = MainBackgroundColor
                                             )