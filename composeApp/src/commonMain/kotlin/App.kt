import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import common.presentation.determination.DeterminationScreen
import common.presentation.main.CurrentScreen
import common.presentation.main.MainScreen
import common.presentation.undefined_design.UndefinedDesignScreen
import common.ui.theme.AppCommonTheme
import common.ui.theme.LocalNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    AppCommonTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            CompositionLocalProvider(
                LocalNavigator provides (navController)
            ) {
                NavHost(navController = navController, startDestination = CurrentScreen.Main.route) {
                    composable(CurrentScreen.Main.route) {
                        MainScreen()
                    }
                    composable(CurrentScreen.Determination.route) {
                        DeterminationScreen()
                    }
                    composable(CurrentScreen.UndefinedDesign.route) {
                        UndefinedDesignScreen()
                    }
                }
            }
        }
    }
}


















