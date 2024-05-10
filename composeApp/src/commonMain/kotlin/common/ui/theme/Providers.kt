package common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController


val LocalTheme = staticCompositionLocalOf<Theme> { error("theme not found :(") }
val LocalNavigator = staticCompositionLocalOf<NavController> { error("navigator not found :(") }