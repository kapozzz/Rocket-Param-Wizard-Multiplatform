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
import common.presentation.main.AppScreen
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
                        Pair("Угол наклона", "${dependenciesParameters.angle}°"),
                        Pair("Угол наколна в радианах", "${angleInRadians}[рад]"),
                        Pair("l координата", "${dependenciesParameters.lCoord}[м]"),
                        Pair("h координата", "${dependenciesParameters.hCoord}[м]"),
                        Pair("Поправочный коэффициент", "$definedCoefficient"),
                        Pair("l координата(уточ.)", "${definedLCoord}[м]"),
                        Pair("h координата(уточ.)", "${definedhCoord}[м]"),
                        Pair("Центральный угол", "${centralAngle}[рад]"),
                        Pair("Скорость продуктов истечения", "${fuelFlowRate}[м/c]"),
                        Pair("Безразмерная скорость в конце полёта", "$dimensionlessVelocityOnFinishActiveFly"),
                        Pair("Скорость в конце полёта", "${velocityOnFinishActiveFly}[м/c]"),
                        Pair("Коэффициент заполнения топливом первой ступени", reducedPropellantFillFactorForFirstStage.toString()),
                        Pair("Коэффициент заполнения топливом второй ступени", reducedPropellantFillFactorForSecondStage.toString()),
                        Pair("Приведенный коэффициент заполнения ракеты топливом", reducedPropellantFillFactor.toString())
                    )
                }
                IconButton(
                    {
                        navigator.navigate(AppScreen.DefinedVerification.route)
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