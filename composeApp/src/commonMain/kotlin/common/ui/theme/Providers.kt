package common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

val LocalTheme = staticCompositionLocalOf<Theme> { error("theme not found :(") }