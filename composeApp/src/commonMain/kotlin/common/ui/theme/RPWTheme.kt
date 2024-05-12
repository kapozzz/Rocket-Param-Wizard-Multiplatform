package common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val primary = Color(0xFF0095DA)
private val onPrimary = Color.White
private val background = Color.White
private val onBackground = Color.Black
private val hint = Color(0xFFDCDCDC)
private val onHint = Color.White

private val titleTypo = TextStyle(
    fontSize = 26.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold
)

private val regularTypo = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold
)

private val hintTypo = TextStyle(
    fontSize = 12.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold
)

private val secondaryRegularTypo = TextStyle(
    fontSize = 16.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Medium
)

@Composable
fun AppCommonTheme(
    content: @Composable () -> Unit
) {
    val colors = Colors(
        primary = primary,
        onPrimary = onPrimary,
        background = background,
        onBackground = onBackground,
        hint = hint,
        onHint = onHint
    )

    val typo = Typo(
        title = titleTypo,
        regular = regularTypo,
        hint = hintTypo,
        secondaryRegular = secondaryRegularTypo
    )

    val theme = Theme(
        colors = colors,
        typo = typo
    )

    CompositionLocalProvider(
        LocalTheme provides (theme)
    ) {
        content()
    }
}