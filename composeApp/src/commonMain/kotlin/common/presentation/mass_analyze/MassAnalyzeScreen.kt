package common.presentation.mass_analyze

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.presentation.components.ParametersViewer
import common.presentation.main.AppScreen
import common.presentation.main.components.ParamsField
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import common.ui.theme.LocalTheme
import core.solvers.use.MassAnalyzer

@Composable
fun MassAnalyzeScreen() {

    val navigator = LocalNavigator.current
    val solvesState = LocalSolvesState.current
    val scrollState = rememberScrollState()

    val secondHigh = remember { mutableStateOf(42.0) }
    val secondLow = remember { mutableStateOf(8.0) }
    val firstHigh = remember { mutableStateOf(130.0) }
    val firstLow = remember { mutableStateOf(14.0) }

    val analyzer = remember {
        mutableStateOf(
            MassAnalyzer(
                solvesState = solvesState,
                firstInFirstStage = 14.0,
                secondInFirstStage = 130.0,
                firstInSecondStage = 8.0,
                secondInSecondStage = 42.0
            )
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Массовый анализ",
                    style = LocalTheme.current.typo.title,
                    color = LocalTheme.current.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = "2 ступень",
                    style = LocalTheme.current.typo.title,
                    color = LocalTheme.current.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                ParamsField(
                    name = "Нижнее значение [т]",
                    param = secondLow.value.toString(),
                    onParamChange = {
                        secondLow.value = it.toDouble()
                        analyzer.value = analyzer.value.copy(
                            firstInSecondStage = it.toDouble()
                        )
                    }
                )
                ParamsField(
                    name = "Верхнее значение [т]",
                    param = secondHigh.value.toString(),
                    onParamChange = {
                        secondHigh.value = it.toDouble()
                        analyzer.value = analyzer.value.copy(
                            secondInSecondStage = it.toDouble()
                        )
                    }
                )
                with(analyzer.value.first) {
                    ParametersViewer(
                        "1",
                        Pair("Заданное значение массы второй ступени", "${mass}[т]"),
                        Pair("Конструктивный коэффициент ", "$N"),
                        Pair("Коэффициент «удельного веса» двигателей", "$b"),
                        Pair(
                            "Коэффициент, характеризующий степень совершенства системы питания двигателя",
                            "$alphaFromTable"
                        ),
                        Pair(
                            "Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива",
                            "$alpha"
                        ),
                        Pair(
                            "Средняя плотность конструкции топливного отсека",
                            "${density}[кг/м^3]"
                        ),
                        Pair("Введенный коэффициент K2", "$definedK"),
                        Pair("Рабочий запас топлива", "${omega}[т]"),
                        Pair("Тяга двигателей ступеней в пустоте", "${specificGravity}[кН]"),
                        Pair("Введенный коэффициент N2", "$definedN"),
                        Pair("Расчетное значение массы", "${calculatedMass}[т]"),
                        Pair(
                            "Разница между заданным значением массы и получаемым расчетным путем",
                            "${different}[т]"
                        ),
                    )
                }
                with(analyzer.value.second) {
                    ParametersViewer(
                        "2",
                        Pair("Заданное значение массы второй ступени", "${mass}[т]"),
                        Pair("Конструктивный коэффициент ", "$N"),
                        Pair("Коэффициент «удельного веса» двигателей", "$b"),
                        Pair(
                            "Коэффициент, характеризующий степень совершенства системы питания двигателя",
                            "$alphaFromTable"
                        ),
                        Pair(
                            "Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива",
                            "$alpha"
                        ),
                        Pair(
                            "Средняя плотность конструкции топливного отсека",
                            "${density}[кг/м^3]"
                        ),
                        Pair("Введенный коэффициент K2", "$definedK"),
                        Pair("Рабочий запас топлива", "${omega}[т]"),
                        Pair("Тяга двигателей ступеней в пустоте", "${specificGravity}[т]"),
                        Pair("Введенный коэффициент N2", "$definedN"),
                        Pair("Расчетное значение массы", "${calculatedMass}[т]"),
                        Pair(
                            "Разница между заданным значением массы и получаемым расчетным путем",
                            "${different}[т]"
                        ),
                    )
                }
                Text(
                    text = "Истинное значение массы второй ступени: ${analyzer.value.definedMassFirst}[т]",
                    style = LocalTheme.current.typo.regularBold,
                    color = LocalTheme.current.colors.onBackground,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 22.dp)
                )
                Text(
                    text = "1 ступень",
                    style = LocalTheme.current.typo.title,
                    color = LocalTheme.current.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                ParamsField(
                    name = "Нижнее значение [т]",
                    param = firstLow.value.toString(),
                    onParamChange = {
                        firstLow.value = it.toDouble()
                        analyzer.value = analyzer.value.copy(
                            firstInFirstStage = it.toDouble()
                        )
                    }
                )
                ParamsField(
                    name = "Верхнее значение [т]",
                    param = firstHigh.value.toString(),
                    onParamChange = {
                        firstHigh.value = it.toDouble()
                        analyzer.value = analyzer.value.copy(
                            secondInFirstStage = it.toDouble()
                        )
                    }
                )
                with(analyzer.value.third) {
                    ParametersViewer(
                        "1",
                        Pair("Заданное значение массы ступени", "${mass}[т]"),
                        Pair("Конструктивный коэффициент ", "$N"),
                        Pair("Коэффициент «удельного веса» двигателей", "$b"),
                        Pair(
                            "Коэффициент, характеризующий степень совершенства системы питания двигателя",
                            "$alphaFromTable"
                        ),
                        Pair(
                            "Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива",
                            "$alpha"
                        ),
                        Pair(
                            "Средняя плотность конструкции топливного отсека",
                            "${density}[кг/м^3]"
                        ),
                        Pair("Введенный коэффициент K1", "$definedK"),
                        Pair("Рабочий запас топлива", "${omega}[т]"),
                        Pair("Тяга двигателей ступеней в пустоте", "${specificGravity}[кН]"),
                        Pair("Введенный коэффициент N1", "$definedN"),
                        Pair("Расчетное значение массы", "${calculatedMass}[т]"),
                        Pair(
                            "Разница между заданным значением массы и получаемым расчетным путем",
                            "${different}[т]"
                        ),
                    )
                }
                with(analyzer.value.fourth) {
                    ParametersViewer(
                        "2",
                        Pair("Заданное значение массы второй ступени", "${mass}[т]"),
                        Pair("Конструктивный коэффициент ", "$N"),
                        Pair("Коэффициент «удельного веса» двигателей", "$b"),
                        Pair(
                            "Коэффициент, характеризующий степень совершенства системы питания двигателя",
                            "$alphaFromTable"
                        ),
                        Pair(
                            "Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива",
                            "$alpha"
                        ),
                        Pair(
                            "Средняя плотность конструкции топливного отсека",
                            "${density}[кг/м^3]"
                        ),
                        Pair("Введенный коэффициент K1", "$definedK"),
                        Pair("Рабочий запас топлива", "${omega}[т]"),
                        Pair("Тяга двигателей ступеней в пустоте", "${specificGravity}[кН]"),
                        Pair("Введенный коэффициент N1", "$definedN"),
                        Pair("Расчетное значение массы", "${calculatedMass}[т]"),
                        Pair(
                            "Разница между заданным значением массы и получаемым расчетным путем",
                            "${different}[т]"
                        ),
                    )
                }
                Text(
                    text = "Истинное значение массы второй ступени: ${analyzer.value.definedMassSecond}[т]",
                    style = LocalTheme.current.typo.regularBold,
                    color = LocalTheme.current.colors.onBackground,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 22.dp)
                )
            }
//            IconButton(
//                {
//                    navigator.navigate(AppScreen.UndefinedVerification.route)
//                },
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(16.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = null
//                )
//            }
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















