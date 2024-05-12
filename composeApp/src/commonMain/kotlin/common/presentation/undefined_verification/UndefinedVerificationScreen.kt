package common.presentation.undefined_verification

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.MutableParam
import common.presentation.components.ParametersViewer
import common.presentation.components.PickAnotherParams
import common.presentation.main.CurrentScreen
import common.presentation.util.ParamsHandler
import common.ui.theme.LocalNavigator
import core.solvers.VerificationBallisticCalculation
import kotlinx.coroutines.launch

@Composable
fun UndefinedVerificationScreen() {

    val navigator = LocalNavigator.current
    val determination =
        ParamsHandler.determination.value ?: throw IllegalStateException("Determination is null")
    val undefinedDesign = ParamsHandler.undefinedDesign.value
        ?: throw IllegalStateException("Undefined designs is null")
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    ParamsHandler.undefinedVerification.value = VerificationBallisticCalculation(
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ParametersViewer(
                    name = "Проверочный баллистический расчет (неуточнённый):",
                    Pair(
                        "Число Циолковского для первой ступени",
                        ParamsHandler.undefinedVerification.value!!.cValueFirstStage.toString()
                    ),
                    Pair(
                        "Число Циолковского для второй ступени",
                        ParamsHandler.undefinedVerification.value!!.cValueSecondStage.toString()
                    ),
                    Pair(
                        "Потери скорости на преодоление гравитационных сил",
                        ParamsHandler.undefinedVerification.value!!.velocityLessOnGravitationPowers.toString()
                    ),
                    Pair(
                        "Потери скорости на преодоление лобового сопротивления",
                        ParamsHandler.undefinedVerification.value!!.velocityLessOnFrontPowers.toString()
                    ),
                    Pair(
                        "Уравнение скорости Vk1", ParamsHandler.undefinedVerification.value!!.firstVelocityEqualization.toString()
                    ),
                    Pair(
                        "Ix1", ParamsHandler.undefinedVerification.value!!.Ix1.toString()
                    ),
                    Pair(
                        "Ip1", ParamsHandler.undefinedVerification.value!!.Ip1.toString()
                    ),
                    Pair(
                        "Ig1", ParamsHandler.undefinedVerification.value!!.Ig1.toString()
                    ),
                    Pair(
                        "Ф1", ParamsHandler.undefinedVerification.value!!.F1.toString()
                    ),
                    Pair(
                        "Ф2", ParamsHandler.undefinedVerification.value!!.F2.toString()
                    ),
                    Pair(
                        "Ф4", ParamsHandler.undefinedVerification.value!!.F4.toString()
                    ),
                    Pair(
                        "A1",
                        ParamsHandler.undefinedVerification.value!!.A1.toString()
                    ),
                    Pair(
                        "A2",
                        ParamsHandler.undefinedVerification.value!!.A2.toString()
                    ),
                    Pair(
                        "Высота hk1",
                        ParamsHandler.undefinedVerification.value!!.heightHk1.toString()
                    ),
                    Pair(
                        "Дальность lk1",
                        ParamsHandler.undefinedVerification.value!!.distanceLk1.toString()
                    ),
                    Pair(
                        "Vk2",
                        ParamsHandler.undefinedVerification.value!!.secondVelocityEqualization.toString()
                    ),
                    Pair(
                        "B2",
                        ParamsHandler.undefinedVerification.value!!.B2.toString()
                    ),
                    Pair(
                        "Число Циолковского Ц2",
                        ParamsHandler.undefinedVerification.value!!.cValueSecondWithoutUk1.toString()
                    ),
                    Pair(
                        "Высота hk2",
                        ParamsHandler.undefinedVerification.value!!.heightHk2.toString()
                    ),
                    Pair(
                        "Дальность lk2",
                        ParamsHandler.undefinedVerification.value!!.distanceLk2.toString()
                    )
                )
                PickAnotherParams(
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.F1.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                F1 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ф1"
                    ),
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.F2.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                F2 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ф2"
                    ),
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.F4.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                F4 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ф4"
                    ),
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.Ix1.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                Ix1 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ix1"
                    ),
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.Ig1.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                Ig1 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ig1"
                    ),
                    MutableParam(
                        text = mutableStateOf(ParamsHandler.undefinedVerification.value!!.Ip1.toString()),
                        onChange = {
                            val copy = ParamsHandler.undefinedVerification.value!!.copy(
                                Ip1 = it.toDouble()
                            )
                            ParamsHandler.undefinedVerification.value = copy
                        },
                        name = "Ip1"
                    ),
                    onOpenClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(scrollState.viewportSize)
                        }
                    }
                )
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
