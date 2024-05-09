import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.models.ProjectParams
import core.solvers.DeterminationOfEngineEfficiencyIndicators
import main_screen.CurrentScreen
import main_screen.MainScreen
import main_screen.MainScreenState
import common.ui.theme.AppCommonTheme
import core.solvers.DesignBallisticCalculation
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
//    state.value.projectParams.value = ProjectParams.getDefault()
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

    val firstSolve = DeterminationOfEngineEfficiencyIndicators(projectParams)
    val secondSolve = DesignBallisticCalculation(projectParams)

    Column {
        Text("Первый шаг:")
        Text(
            text = "Удальная тяга 1:" + firstSolve.specificGravityCalculatedFirst.toString()
        )
        Text(
            text = "Удельная тяга 2:" + firstSolve.specificGravityCalculatedSecond.toString()
        )
        Text(
            text = "Удельная тяга в пустоте 1:" + firstSolve.specificGravityInVoidFirst.toString()
        )
        Text(
            text = "Удельная тяга в пустоте 2:" + firstSolve.specificGravityInVoidSecond.toString()
        )
        Text(
            text = "Удельная тяга при старте:" + firstSolve.specificGravityOnStartFromEarth.toString()
        )
        Text(
            text = "Средняя удельная тяга:" + firstSolve.middleSpecificGravity.toString()
        )
        Text(
            text = "Тяговооруженность при старте 1:" + firstSolve.firstStageThrustCapacityInVoid.toString()
        )
        Text("Второй шаг:")
        Text(
            text = "Центральный угол:" + secondSolve.centralAngle
        )
        Text(
            text = "Безразмерная скорость в конце полёта:" + secondSolve.dimensionlessVelocityOnFinishActiveFly.toString()
        )
        Text(
            text = "Скорость в конце полёта:" + secondSolve.velocityOnFinishActiveFly
        )
        Text(
            text = "Коэффициент заполнения топливом первой ступени:" + secondSolve.reducedPropellantFillFactorForFirstStage
        )
        Text(
            text = "Коэффициент заполнения топливом второй ступени:" + secondSolve.reducedPropellantFillFactorForSecondStage
        )
        Text(
            text = "Приведенный коэффициент заполнения ракеты топливом:" + secondSolve.reducedPropellantFillFactor
        )
        Text(
            text = "Скорость продуктов истечения:" + secondSolve.fuelFlowRate
        )
        Text(
            text = "Идеальная скорость:" + secondSolve.greatVelocity
        )
    }
}
















