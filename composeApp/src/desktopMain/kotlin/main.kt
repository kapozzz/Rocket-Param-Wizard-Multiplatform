
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {

    val state = rememberWindowState(size = DpSize(500.dp, 900.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "Rocket Param Wizard",
        resizable = false,
        state = state
    ) {
        App()
    }
}