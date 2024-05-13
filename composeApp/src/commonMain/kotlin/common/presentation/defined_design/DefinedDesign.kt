package common.presentation.defined_design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.ParametersViewer
import common.presentation.main.CurrentScreen
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState

@Composable
fun DefinedDesign() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val navigator = LocalNavigator.current
        val solvesState = LocalSolvesState.current
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
                with(solvesState.definedDesign) {
                    ParametersViewer(
                        name = "Проектировочный баллистический расчет(уточнённый)",
                        Pair("Угол", dependenciesParameters.angle.toString()),
                        Pair("Угол в радианах", angleInRadians.toString()),
                        Pair("lCoord", dependenciesParameters.lCoord.toString()),
                        Pair("hCoord", dependenciesParameters.hCoord.toString()),
                        Pair("Поправочный коэффициент", definedCoefficient.toString()),
                        Pair("lCoord уточ.", definedLCoord.toString()),
                        Pair("hCoord уточ.", definedhCoord.toString()),
                        Pair("Центральный угол", centralAngle.toString()),
                        Pair("Скорость продуктов истечения:", fuelFlowRate.toString()),
                        Pair(
                            "Безразмерная скорость в конце полёта",
                            dimensionlessVelocityOnFinishActiveFly.toString()
                        ),
                        Pair(
                            "Скорость в конце полёта",
                            velocityOnFinishActiveFly.toString()
                        ),
                        Pair(
                            "Коэффициент заполнения топливом первой ступени",
                            reducedPropellantFillFactorForFirstStage.toString()
                        ),
                        Pair(
                            "Коэффициент заполнения топливом второй ступени",
                            reducedPropellantFillFactorForSecondStage.toString()
                        ),
                        Pair(
                            "Приведенный коэффициент заполнения ракеты топливом",
                            reducedPropellantFillFactor.toString()
                        )
                    )
                }
                IconButton(
                    {
                        navigator.navigate(CurrentScreen.DefinedVerification.route)
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
}