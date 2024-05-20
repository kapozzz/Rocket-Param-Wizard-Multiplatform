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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.ParametersViewer
import common.presentation.main.AppScreen
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState

@Composable
fun DefinedVerificationScreen() {

    val navigator = LocalNavigator.current
    val solvesState = LocalSolvesState.current
    val scrollState = rememberScrollState()

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
                        Pair("Ф1", F1.toString()),
                        Pair("Ф2", F2.toString()),
                        Pair("Ф4", F4.toString()),
                        Pair("Скорость в конце активного участка полета (1-я ступень)", "${firstVelocityEqualization}[м/c]"),
                        Pair("Число Ц1(uk1)", cValueFirstStage.toString()),
                        Pair("Ig1", Ig1.toString()),
                        Pair("Ip1", "${Ip1}[м/с^2]"),
                        Pair("Ix1", "${Ix1}[м/c]"),
                        Pair("Потери скорости на преодоление лобового сопротивления", "${velocityLessOnFrontPowers}[м/c]"),
                        Pair("Высота hk1", "${heightHk1}[м]"),
                        Pair("Дальность lk1", "${distanceLk1}[м]"),
                        Pair("Число Ц2(uk1)", cValueSecondStage.toString()),
                        Pair("A1", A1.toString()),
                        Pair("Скорость в конце активного участка полета (2-я ступень)", secondVelocityEqualization.toString()),
                        Pair("Высота hk2", "${heightHk2}[м]"),
                        Pair("Дальность lk2", "${distanceLk2}[м]"),
                        Pair("A2", A2.toString()),
                        Pair("B2", B2.toString()),
                        Pair("Число Циолковского", cValueSecondWithoutUk1.toString()),
                        Pair("Полная дальность полёта", "${totalFlyDistance}[м]"),
                        Pair("Пассивная дальность полёта", "${passiveFlyDistance}[м]"),
                        Pair("Центральный угол", "${centralAngleBeta}[рад]"),
                        Pair("a", a.toString()),
                        Pair("b", b.toString()),
                        Pair("c", c.toString()),
                        Pair("Безразмерная скорость в конце активного участка полета", vk.toString()),
                        Pair("Разница между заданной и полученной дальностями полета ракеты", "${deltaL}[%]"),
                        Pair("Разница между заданной и полученной дальностями полета ракеты", "${definedDeltaL}[м]"),
                        Pair("Vk2/Uпр", expDefinedVk2onDefinedUpr.toString()),
                        Pair("DeltaL/Vk2", expDefinedDeltaLonDefinedVk2.toString()),
                        Pair("deltaUpr", deltaUpr.toString()),
                        Pair("Уточненное значение  приведенного коэффициента заполнения ракеты топливом", definedDeltaUpr.toString())
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
                    navigator.navigate(AppScreen.MassAnalyze.route)
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