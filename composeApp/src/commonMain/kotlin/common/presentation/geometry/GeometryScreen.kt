package common.presentation.geometry

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.MutableParam
import common.presentation.components.ParametersViewer
import common.presentation.components.PickAnotherParams
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import core.solvers.LastStep

@Composable
fun GeometryScreen() {

    val solvesState = LocalSolvesState.current
    val navigator = LocalNavigator.current
    val scrollState = rememberScrollState()


    val f = remember {
        mutableStateOf("15.0")
    }
    val k = remember {
        mutableStateOf("1.25")
    }
    solvesState.define()
    val geometry = remember {
        mutableStateOf(
            LastStep(
                solvesState,
                15.0,
                1.25
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top
        ) {
            PickAnotherParams(
                MutableParam(
                    name = "φ",
                    text = f,
                    onChange = {
                        geometry.value = geometry.value.copy(
                            f = it.toDouble()
                        )
                        f.value = it
                    }
                ),
                MutableParam(
                    name = "k",
                    text = k,
                    onChange = {
                        geometry.value = geometry.value.copy(
                            k = it.toDouble()
                        )
                        k.value = it
                    }
                ),
            )
            ParametersViewer(
                name = "Определение тяговых характеристик",
                Pair(
                    "Секундный расход массы топлива первой ступени",
                    "${geometry.value.m1Point}[кг/с]"
                ),
                Pair(
                    "Секундный расход массы топлива второй ступени",
                    "${geometry.value.m2point}[кг/с]"
                ),
                Pair("Время полета первой ступени ракеты", "${geometry.value.tk1}[с]"),
                Pair("Время полета второй ступени ракеты", "${geometry.value.tk2}[с]"),
                Pair("Тяга двигателей ступеней на Земле", "${geometry.value.P01}[с]"),
            )
            ParametersViewer(
                name = "Общие параметры",
                Pair("Диаметр ракеты", "${geometry.value.dm}[м]"),
                Pair(
                    "Длина головной части",
                    "${geometry.value.lgh}[м]"
                ),
                Pair("Приближенное значение длины ракеты", "${geometry.value.lprib}[м]"),
                Pair("φ", "${f.value}[град]"),
                Pair("k", k.value),
                Pair("Длина соплового аппарата первой ступени", "${geometry.value.lc1}[м]"),
                Pair("Длина двигательной установки первой ступени", "${geometry.value.ldy1}[м]"),
                Pair("Длина соплового аппарата второй ступени", "${geometry.value.lc2}[м]"),
                Pair("Длина двигательной установки второй ступени", "${geometry.value.ldy2}[м]"),
            )
            ParametersViewer(
                name = "Определение геометрических параметров 1-ступень",
                Pair(
                    "Объем бака окислителя первой ступени",
                    "${geometry.value.Vok1}[м^3]"
                ),
                Pair(
                    "Объем бака горючего первой ступени",
                    "${geometry.value.Vbk1}[м^3]"
                ),
                Pair(
                    "Длина бака окислителя ракетного блока первой ступени",
                    "${geometry.value.lbok1}[м]"
                ),
                Pair(
                    "Длина бака горючего ракетного блока первой ступени",
                    "${geometry.value.lbg1}[м]"
                ),
                Pair(
                    "Длина приборного отсека ракетного блока первой ступени",
                    "${geometry.value.lpo1}[м]"
                ),
                Pair(
                    "Длина хвостового отсека ракетного блока первой ступени",
                    "${geometry.value.lxo1}[м]"
                ),
                Pair(
                    "Длина первой ступени",
                    "${geometry.value.l1}[м]"
                ),
            )
            ParametersViewer(
                name = "Определение геометрических параметров 2-ступени",
                Pair(
                    "Объем бака окислителя второй ступени",
                    "${geometry.value.Vok2}[м^3]"
                ),
                Pair(
                    "Объем бака горючего второй ступени",
                    "${geometry.value.Vbk2}[м^3]"
                ),
                Pair(
                    "Длина бака окислителя ракетного блока второй ступени",
                    "${geometry.value.lbok2}[м]"
                ),
                Pair(
                    "Длина бака горючего ракетного блока второй ступени",
                    "${geometry.value.lbg2}[м]"
                ),
                Pair(
                    "Длина приборного отсека ракетного блока второй ступени",
                    "${geometry.value.lpo2}[м]"
                ),
                Pair(
                    "Длина хвостового отсека ракетного блока второй ступени",
                    "${geometry.value.lxo2}[м]"
                ),
                Pair(
                    "Длина второй ступени",
                    "${geometry.value.l2}[м]"
                ),
            )
            ParametersViewer(
                name = "Результат",
                Pair("Длина ракеты", "${geometry.value.rocketLength}[м]"),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
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