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
import common.presentation.main.AppScreen
import common.presentation.main.MainScreen
import common.presentation.mass_analyze.MassAnalyzeScreen
import common.presentation.undefined_design.UndefinedDesignScreen
import common.presentation.undefined_verification.UndefinedVerificationScreen
import common.ui.theme.AppCommonTheme
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import core.solvers.state.rememberSolvesState
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
                    startDestination = AppScreen.Main.route
                ) {
                    composable(AppScreen.Main.route) {
                        MainScreen()
                    }
                    composable(AppScreen.Determination.route) {
                        DeterminationScreen()
                    }
                    composable(AppScreen.UndefinedDesign.route) {
                        UndefinedDesignScreen()
                    }
                    composable(AppScreen.UndefinedVerification.route) {
                        UndefinedVerificationScreen()
                    }
                    composable(AppScreen.InfoScreen.route) {
                        InfoScreen()
                    }
                    composable(AppScreen.DefinedDesign.route) {
                        DefinedDesign()
                    }
                    composable(AppScreen.DefinedVerification.route) {
                        DefinedVerificationScreen()
                    }
                    composable(AppScreen.MassAnalyze.route) {
                        MassAnalyzeScreen()
                    }
                }
            }
        }
    }
}


















