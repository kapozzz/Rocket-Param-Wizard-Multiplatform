import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.core.base.models.ProjectParams
import common.core.base.solvers.DeterminationOfEngineEfficiencyIndicators
import common.main_screen.CurrentScreen
import common.main_screen.MainScreen
import common.main_screen.MainScreenState
import common.ui.theme.AppCommonTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {

    val state = remember {
        mutableStateOf(MainScreenState())
    }
    val currentScreen = remember {
        mutableStateOf<CurrentScreen>(CurrentScreen.Main)
    }
    AppCommonTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            when (currentScreen.value) {
                is CurrentScreen.Main -> {
                    MainScreen(
                        state = state.value,
                        onComputeClick = {
                            state.value.projectParams.value = it
                            currentScreen.value = CurrentScreen.Results
                        }
                    )
                }

                is CurrentScreen.Results -> {
                    ResultsScreen(state.value.projectParams.value)
                }
            }
        }
    }
}

@Composable
fun ResultsScreen(
    projectParams: ProjectParams,
) {

    val solve = DeterminationOfEngineEfficiencyIndicators(projectParams)

    Column {
        Text(
            text = solve.specificGravityCalculatedFirst.toString()
        )
        Text(
            text = solve.specificGravityCalculatedSecond.toString()
        )
        Text(
            text = solve.specificGravityInVoidFirst.toString()
        )
        Text(
            text = solve.specificGravityInVoidSecond.toString()
        )
        Text(
            text = solve.specificGravityOnStartFromEarth.toString()
        )
        Text(
            text = solve.middleSpecificGravity.toString()
        )
        Text(
            text = solve.firstStageThrustCapacityInVoid.toString()
        )
    }
}
















