package common.presentation.undefined_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import core.solvers.VerificationBallisticCalculation

@Composable
fun UndefinedVerificationScreen() {

    val navigator = LocalNavigator.current
    val determination =
        ParamsHandler.determination.value ?: throw IllegalStateException("Determination is null")
    val undefinedDesign = ParamsHandler.undefinedDesign.value
        ?: throw IllegalStateException("Undefined designs is null")

    val undefinedVerification = VerificationBallisticCalculation(
        projectParams = ParamsHandler.projectParams.value,
        fuel = ParamsHandler.fuel.value,
        designBallisticCalculation = undefinedDesign,
        determinationOfEngineEfficiencyIndicators = determination
    )
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
                name = "Проверочный баллистический расчет (неуточнённый):",
                Pair(
                    "Число Циолковского для первой ступени",
                    undefinedVerification.cValueFirstStage.toString()
                ),
                Pair(
                    "Число Циолковского для второй ступени",
                    undefinedVerification.cValueSecondStage.toString()
                ),
                Pair(
                    "Потери скорости на преодоление гравитационных сил",
                    undefinedVerification.velocityLessOnGravitationPowers.toString()
                ),
                Pair(
                    "Потери скорости на преодоление лобового сопротивления",
                    undefinedVerification.velocityLessOnFrontPowers.toString()
                ),
                Pair(
                    "Уравнение скорости Vk1", undefinedVerification.firstVelocityEqualization.toString()
                ),
                Pair(
                    "A1",
                    undefinedVerification.A1.toString()
                ),
                Pair(
                    "A2",
                    undefinedVerification.A2.toString()
                ),
                Pair(
                    "Высота hk1",
                    undefinedVerification.heightHk1.toString()
                ),
                Pair(
                    "Дальность lk1",
                    undefinedVerification.distanceLk1.toString()
                ),
                Pair(
                    "Vk2",
                    undefinedVerification.secondVelocityEqualization.toString()
                ),
                Pair(
                    "B2",
                    undefinedVerification.B2.toString()
                ),
                Pair(
                    "Число Циолковского Ц2",
                    undefinedVerification.cValueSecondWithoutUk1.toString()
                ),
                Pair(
                    "Высота hk2",
                    undefinedVerification.heightHk2.toString()
                ),
                Pair(
                    "Дальность lk2",
                    undefinedVerification.distanceLk2.toString()
                )
            )
            IconButton(
                {
                    navigator.navigate(CurrentScreen.DefinedDesign.route)
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
                    navigator.navigate(CurrentScreen.UndefinedDesign.route)
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
