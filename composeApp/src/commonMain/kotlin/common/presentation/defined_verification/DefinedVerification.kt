package common.presentation.defined_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.MutableParam
import common.presentation.components.ParametersViewer
import common.presentation.components.PickAnotherParams
import common.presentation.main.CurrentScreen
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import kotlinx.coroutines.launch

@Composable
fun DefinedVerificationScreen() {

    val navigator = LocalNavigator.current
    val solvesState = LocalSolvesState.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                with(solvesState.definedVerification) {
                    ParametersViewer(
                        name = "Проверочный баллистический расчет (уточнённый):",
                        Pair(
                            "Число Циолковского для первой ступени",
                            cValueFirstStage.toString()
                        ),
                        Pair(
                            "Число Циолковского для второй ступени",
                            cValueSecondStage.toString()
                        ),
                        Pair(
                            "Потери скорости на преодоление лобового сопротивления",
                            velocityLessOnFrontPowers.toString()
                        ),
                        Pair(
                            "Уравнение скорости Vk1", firstVelocityEqualization.toString()
                        ),
                        Pair(
                            "Ix1", Ix1.toString()
                        ),
                        Pair(
                            "Ip1", Ip1.toString()
                        ),
                        Pair(
                            "Ig1", Ig1.toString()
                        ),
                        Pair(
                            "Ф1", F1.toString()
                        ),
                        Pair(
                            "Ф2", F2.toString()
                        ),
                        Pair(
                            "Ф4", F4.toString()
                        ),
                        Pair(
                            "A1",
                            A1.toString()
                        ),
                        Pair(
                            "A2",
                            A2.toString()
                        ),
                        Pair(
                            "Высота hk1",
                            heightHk1.toString()
                        ),
                        Pair(
                            "Дальность lk1",
                            distanceLk1.toString()
                        ),
                        Pair(
                            "Vk2",
                            secondVelocityEqualization.toString()
                        ),
                        Pair(
                            "B2",
                            B2.toString()
                        ),
                        Pair(
                            "Число Циолковского Ц2",
                            cValueSecondWithoutUk1.toString()
                        ),
                        Pair(
                            "Высота hk2",
                            heightHk2.toString()
                        ),
                        Pair(
                            "Дальность lk2",
                            distanceLk2.toString()
                        ),
                        Pair(
                            "c",
                            c.toString()
                        ),
                        Pair(
                            "b",
                            b.toString()
                        ),
                        Pair(
                            "a",
                            a.toString()
                        ),
                        Pair(
                            "Центральный угол бетта",
                            centralAngleBeta.toString()
                        ),
                        Pair(
                            "Пассивная дальность полёта",
                            passiveFlyDistance.toString()
                        ),
                        Pair(
                            "Полная дальность полёта",
                            totalFlyDistance.toString()
                        ),
                        Pair(
                            "vk",
                            vk.toString()
                        ),
                        Pair(
                            "delta L",
                            deltaL.toString()
                        ),
                        Pair(
                            "delta L уточнённое",
                            definedDeltaL.toString()
                        ),
                        Pair(
                            "Vk2/Uпр",
                            expDefinedVk2onDefinedUpr.toString()
                        ),
                        Pair(
                            "DeltaL/Vk2",
                            expDefinedDeltaLonDefinedVk2.toString()
                        ),
                        Pair(
                            "deltaUpr",
                            deltaUpr.toString()
                        ),
                        Pair(
                            "definedDeltaUpr",
                            definedDeltaUpr.toString()
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }

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