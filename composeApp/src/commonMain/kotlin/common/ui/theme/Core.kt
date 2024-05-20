package common.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


data class Theme(
    val colors: Colors,
    val typo: Typo
)

data class Colors(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val hint: Color,
    val onHint: Color
)

data class Typo(
    val title: TextStyle,
    val regular: TextStyle,
    val hint: TextStyle,
    val secondaryRegular: TextStyle,
    val regularBold: TextStyle
)