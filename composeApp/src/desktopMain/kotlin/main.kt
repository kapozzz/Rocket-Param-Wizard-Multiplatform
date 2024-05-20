
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rocketparamwizard.composeapp.generated.resources.Res
import rocketparamwizard.composeapp.generated.resources.main_logo

@OptIn(ExperimentalResourceApi::class)
fun main() = application {

    val state = rememberWindowState(size = DpSize(1300.dp, 900.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "Rocket Param Wizard",
        resizable = false,
        state = state,
        icon = painterResource(Res.drawable.main_logo)
    ) {
        App()
    }
}