package common.presentation.undefined_design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import common.presentation.main.CurrentScreen
import common.presentation.util.ParamsHandler
import common.ui.theme.LocalNavigator
import core.solvers.DesignBallisticCalculation
import core.solvers.VerificationBallisticCalculation

@Composable
fun UndefinedDesignScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val navigator = LocalNavigator.current
        ParamsHandler.undefinedDesign.value = DesignBallisticCalculation(
            projectParams = ParamsHandler.projectParams.value,
            fuel = ParamsHandler.fuel.value,
            determinationOfEngineEfficiencyIndicators = ParamsHandler.determination.value
                ?: throw IllegalStateException("Null determination stage")
        )
        val undefinedDesign = ParamsHandler.undefinedDesign.value
            ?: throw IllegalStateException("Null undefined design stage")
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
                    name = "Проектировочный баллистический расчет(неуточнённый)",
                    Pair("Угол", undefinedDesign.dependenciesParameters.angle.toString()),
                    Pair("Угол в радианах", undefinedDesign.angleInRadians.toString()),
                    Pair("lCoord", undefinedDesign.dependenciesParameters.lCoord.toString()),
                    Pair("hCoord", undefinedDesign.dependenciesParameters.hCoord.toString()),
                    Pair("Поправочный коэффициент", undefinedDesign.definedCoefficient.toString()),
                    Pair("lCoord уточ.", undefinedDesign.definedLCoord.toString()),
                    Pair("hCoord уточ.", undefinedDesign.definedhCoord.toString()),
                    Pair("Центральный угол", undefinedDesign.centralAngle.toString()),
                    Pair("Скорость продуктов истечения:", undefinedDesign.fuelFlowRate.toString()),
                    Pair(
                        "Безразмерная скорость в конце полёта",
                        undefinedDesign.dimensionlessVelocityOnFinishActiveFly.toString()
                    ),
                    Pair(
                        "Скорость в конце полёта",
                        undefinedDesign.velocityOnFinishActiveFly.toString()
                    ),
                    Pair(
                        "Коэффициент заполнения топливом первой ступени",
                        undefinedDesign.reducedPropellantFillFactorForFirstStage.toString()
                    ),
                    Pair(
                        "Коэффициент заполнения топливом второй ступени",
                        undefinedDesign.reducedPropellantFillFactorForSecondStage.toString()
                    ),
                    Pair(
                        "Приведенный коэффициент заполнения ракеты топливом",
                        undefinedDesign.reducedPropellantFillFactor.toString()
                    )
                )
                IconButton(
                    {
                        navigator.navigate(CurrentScreen.UndefinedVerification.route)
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
