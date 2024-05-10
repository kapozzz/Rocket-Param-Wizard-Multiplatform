package common.presentation.determination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                "Определение показателей эффективности двигателей:",
                style = LocalTheme.current.typo.title
            )
            RPWText(text = "Удальная тяга 1:" + firstSolve.specificGravityCalculatedFirst.toString(),)
            RPWText(text = "Удельная тяга 2:" + firstSolve.specificGravityCalculatedSecond.toString())
            RPWText(text = "Удельная тяга в пустоте 1:" + firstSolve.specificGravityInVoidFirst.toString(),)
            RPWText(text = "Удельная тяга в пустоте 2:" + firstSolve.specificGravityInVoidSecond.toString())
            RPWText(text = "Удельная тяга при старте:" + firstSolve.specificGravityOnStartFromEarth.toString(),)
            RPWText(text = "Средняя удельная тяга:" + firstSolve.middleSpecificGravity.toString())
            RPWText(text = "Тяговооруженность при старте 1:" + firstSolve.firstStageThrustCapacityInVoid.toString(),)
            RPWTextButton(
                onClick = { navigator.navigate(CurrentScreen.UndefinedDesign.route) },
                text = "Далее",
                modifier = Modifier
            )
        }
    }
}


