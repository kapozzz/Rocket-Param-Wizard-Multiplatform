package common.presentation.undefined_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.presentation.util.ParamsHandler
import core.solvers.VerificationBallisticCalculation

@Composable
fun UndefinedVerificationScreen() {

    val determination = ParamsHandler.determination.value ?: throw IllegalStateException("Determination is null")
    val undefinedDesign = ParamsHandler.undefinedDesign.value ?: throw IllegalStateException("Undefined designs is null")

    val undefinedVerification = VerificationBallisticCalculation(
        projectParams = ParamsHandler.projectParams.value,
        fuel = ParamsHandler.fuel.value,
        designBallisticCalculation = undefinedDesign,
        determinationOfEngineEfficiencyIndicators = determination
    )


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Проверочный баллистический расчет:")
            Text(
                text = "Число Циолковского для первой ступени:" + undefinedVerification.cValueFirstStage
            )
            Text(
                text = "Число Циолковского для второй ступени:" + undefinedVerification.cValueSecondStage
            )
            Text(
                text = "Потери скорости на преодоление гравитационных сил:" + undefinedVerification.velocityLessOnGravitationPowers
            )
            Text(
                text = "Потери скорости на преодоление лобового сопротивления:" + undefinedVerification.velocityLessOnFrontPowers
            )
            Text(
                text = "Уравнение скорости в проекции на оси скоростной (поточной) системы координат после интегрирования по весу выгоревшего топлива µк1:" + undefinedVerification.firstVelocityEqualization
            )
        }
    }
}
