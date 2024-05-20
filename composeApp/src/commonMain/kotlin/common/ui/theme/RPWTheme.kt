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

private val regularBold = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Bold
)

private val lightTheme = Colors(
    primary = primary,
    onPrimary = onPrimary,
    background = background,
    onBackground = onBackground,
    hint = hint,
    onHint = onHint
)

// неправильные цвета
private val darkTheme = Colors(
    primary = Color.Blue,
    onPrimary = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    hint = Color.LightGray,
    onHint = Color.Black
)

@Composable
fun AppCommonTheme(
    content: @Composable () -> Unit
) {
    val colors = lightTheme

    val typo = Typo(
        title = titleTypo,
        regular = regularTypo,
        hint = hintTypo,
        secondaryRegular = secondaryRegularTypo,
        regularBold = regularBold
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