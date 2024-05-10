package common.presentation.undefined_design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Column {
            Design(undefinedDesign)
            TextButton(
                onClick = {
                    navigator.navigate(CurrentScreen.UndefinedVerification.route)
                }
            ) {
                Text("Далее")
            }
        }
    }
}

@Composable
fun Design(
    design: DesignBallisticCalculation
) {
    Column {
        Text("Проектировочный баллистический расчет(неуточ.):")
        Text(
            text = "Угол:" + design.dependenciesParameters.angle
        )
        Text(
            text = "Угол в радианах:" + design.angleInRadians
        )
        Text(
            text = "lCoord:" + design.dependenciesParameters.lCoord
        )
        Text(
            text = "hCoord:" + design.dependenciesParameters.hCoord
        )
        Text(
            text = "Поправочный коэффициент:" + design.definedCoefficient
        )
        Text(
            text = "lCoord уточ:" + design.definedLCoord
        )
        Text(
            text = "hCoord уточ:" + design.definedhCoord
        )
        Text(
            text = "Центральный угол:" + design.centralAngle
        )
        Text(
            text = "Скорость продуктов истечения:" + design.fuelFlowRate
        )
        Text(
            text = "Безразмерная скорость в конце полёта:" + design.dimensionlessVelocityOnFinishActiveFly.toString()
        )
        Text(
            text = "Скорость в конце полёта:" + design.velocityOnFinishActiveFly
        )
        Text(
            text = "Коэффициент заполнения топливом первой ступени:" + design.reducedPropellantFillFactorForFirstStage
        )
        Text(
            text = "Коэффициент заполнения топливом второй ступени:" + design.reducedPropellantFillFactorForSecondStage
        )
        Text(
            text = "Приведенный коэффициент заполнения ракеты топливом:" + design.reducedPropellantFillFactor
        )
    }
}

@Composable
fun Verification(
    verification: VerificationBallisticCalculation
) {
    Text("Проверочный баллистический расчет:")
    Text(
        text = "Число Циолковского для первой ступени:" + verification.cValueFirstStage
    )
    Text(
        text = "Число Циолковского для второй ступени:" + verification.cValueSecondStage
    )
    Text(
        text = "Потери скорости на преодоление гравитационных сил:" + verification.velocityLessOnGravitationPowers
    )
    Text(
        text = "Потери скорости на преодоление лобового сопротивления:" + verification.velocityLessOnFrontPowers
    )
    Text(
        text = "Уравнение скорости в проекции на оси скоростной (поточной) системы координат после интегрирования по весу выгоревшего топлива µк1:" + verification.firstVelocityEqualization
    )
}