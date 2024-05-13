import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import common.presentation.defined_design.DefinedDesign
import common.presentation.defined_verification.DefinedVerificationScreen
import common.presentation.determination.DeterminationScreen
import common.presentation.info.InfoScreen
import common.presentation.main.CurrentScreen
import common.presentation.main.MainScreen
import common.presentation.undefined_design.UndefinedDesignScreen
import common.presentation.undefined_verification.UndefinedVerificationScreen
import common.ui.theme.AppCommonTheme
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import core.models.ProjectParams
import core.objects.Fuels
import core.solvers.SolvesState
import core.solvers.rememberSolvesState
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val solvesState = rememberSolvesState()
    AppCommonTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            CompositionLocalProvider(
                LocalNavigator provides (navController),
                LocalSolvesState provides (solvesState)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = CurrentScreen.Main.route
                ) {
                    composable(CurrentScreen.Main.route) {
                        MainScreen()
                    }
                    composable(CurrentScreen.Determination.route) {
                        DeterminationScreen()
                    }
                    composable(CurrentScreen.UndefinedDesign.route) {
                        UndefinedDesignScreen()
                    }
                    composable(CurrentScreen.UndefinedVerification.route) {
                        UndefinedVerificationScreen()
                    }
                    composable(CurrentScreen.InfoScreen.route) {
                        InfoScreen()
                    }
                    composable(CurrentScreen.DefinedDesign.route) {
                        DefinedDesign()
                    }
                    composable(CurrentScreen.DefinedVerification.route) {
                        DefinedVerificationScreen()
                    }
                }
            }
        }
    }
}


















