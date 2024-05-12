package common.presentation.determination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.ParametersViewer
import common.presentation.components.RPWText
import common.presentation.components.RPWTextButton
import common.presentation.main.CurrentScreen
import common.presentation.util.ParamsHandler
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalTheme
import core.solvers.DeterminationOfEngineEfficiencyIndicators

@Composable
fun DeterminationScreen(
) {
    val navigator = LocalNavigator.current
    ParamsHandler.determination.value = DeterminationOfEngineEfficiencyIndicators(
        projectParams = ParamsHandler.projectParams.value,
        fuel = ParamsHandler.fuel.value
    )
    val firstSolve =
        ParamsHandler.determination.value ?: throw IllegalStateException("Null determination stage")
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            ParametersViewer(
                name = "Определение показателей эффективности двигателей",
                Pair("Удальная тяга (1)", firstSolve.specificGravityCalculatedFirst.toString()),
                Pair("Удельная тяга (2)", firstSolve.specificGravityCalculatedSecond.toString()),
                Pair(
                    "Удельная тяга в пустоте (1)",
                    firstSolve.specificGravityInVoidFirst.toString()
                ),
                Pair(
                    "Удельная тяга в пустоте (2)",
                    firstSolve.specificGravityInVoidSecond.toString()
                ),
                Pair(
                    "Удельная тяга при старте с земли",
                    firstSolve.specificGravityOnStartFromEarth.toString()
                ),
                Pair("Средняя удельная тяга", firstSolve.middleSpecificGravity.toString()),
                Pair(
                    "Тяговооруженность при старте (1)",
                    firstSolve.firstStageThrustCapacityInVoid.toString()
                )
            )
            IconButton(
                {
                    navigator.navigate(CurrentScreen.UndefinedDesign.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
            IconButton(
                {
                    navigator.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}


