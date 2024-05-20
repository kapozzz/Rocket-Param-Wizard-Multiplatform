package common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import core.solvers.state.SolvesState


val LocalTheme = staticCompositionLocalOf<Theme> { error("theme not found :(") }
val LocalNavigator = staticCompositionLocalOf<NavController> { error("navigator not found :(") }
val LocalSolvesState = staticCompositionLocalOf<SolvesState> { error("solves state is not found :(") }