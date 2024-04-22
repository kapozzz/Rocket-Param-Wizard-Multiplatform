package common.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.core.base.models.ProjectParams
import common.ui.theme.LocalTheme

private data class StringParams(
    val maxFlyDistance: MutableState<String> = mutableStateOf(""),
    val payloadWeight: MutableState<String> = mutableStateOf(""),
    val initialThrustCapacityOfTheRocketFirst: MutableState<String> = mutableStateOf(""),
    val initialThrustCapacityOfTheRocketSecond: MutableState<String> = mutableStateOf(""),
    val engineNozzleShearPressureFirst: MutableState<String> = mutableStateOf(""),
    val engineNozzleShearPressureSecond: MutableState<String> = mutableStateOf(""),
    val pressureInTheCombustionChambersOfEnginesFirst: MutableState<String> = mutableStateOf(""),
    val pressureInTheCombustionChambersOfEnginesSecond: MutableState<String> = mutableStateOf(""),
    val theRatioOfTheRelativeFuelWeightsOfTheStages: MutableState<String> = mutableStateOf(""),
    val initialTransverseLoadOnTheMidsection: MutableState<String> = mutableStateOf(""),
    val relativeLengthOfTheRocket: MutableState<String> = mutableStateOf(""),
)

private fun StringParams.hasEmptyFields(): Boolean {
    return listOf(
        maxFlyDistance,
        payloadWeight,
        initialThrustCapacityOfTheRocketFirst,
        initialThrustCapacityOfTheRocketSecond,
        engineNozzleShearPressureFirst,
        engineNozzleShearPressureSecond,
        pressureInTheCombustionChambersOfEnginesFirst,
        pressureInTheCombustionChambersOfEnginesSecond,
        theRatioOfTheRelativeFuelWeightsOfTheStages,
        initialTransverseLoadOnTheMidsection,
        relativeLengthOfTheRocket
    ).any {
        it.value.isBlank() || it.value.all { char -> char == '.' }
    }
}

@Composable
fun ParametersPicker(
    modifier: Modifier = Modifier,
    projectParams: ProjectParams,
    onParamsChange: (params: ProjectParams) -> Unit
) {

    val params by remember {
        mutableStateOf(StringParams())
    }

    val isCorrect = remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ParamsField(
            name = "Дальность полёта [м]",
            param = params.maxFlyDistance.value,
            onParamChange = {
                params.maxFlyDistance.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Масса полезной нагрузки [кг]",
            param = params.payloadWeight.value,
            onParamChange = {
                params.payloadWeight.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Начальная тяговооруженность первой ступени",
            param = params.initialThrustCapacityOfTheRocketFirst.value,
            onParamChange = {
                params.initialThrustCapacityOfTheRocketFirst.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Начальная тяговооруженность для второй ступени",
            param = params.initialThrustCapacityOfTheRocketSecond.value,
            onParamChange = {
                params.initialThrustCapacityOfTheRocketSecond.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Давление на срезе сопла двигателя первой ступени [бар]",
            param = params.engineNozzleShearPressureFirst.value,
            onParamChange = {
                params.engineNozzleShearPressureFirst.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Давление на срезе сопла двигателя второй ступени [бар]",
            param = params.engineNozzleShearPressureSecond.value,
            onParamChange = {
                params.engineNozzleShearPressureSecond.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Давление в камере сгорания первой ступени [бар]",
            param = params.pressureInTheCombustionChambersOfEnginesFirst.value,
            onParamChange = {
                params.pressureInTheCombustionChambersOfEnginesFirst.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Давление в камере сгорания второй ступени [бар]",
            param = params.pressureInTheCombustionChambersOfEnginesSecond.value,
            onParamChange = {
                params.pressureInTheCombustionChambersOfEnginesSecond.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Коэффициент соотношения относительных весов топлива ступеней",
            param = params.theRatioOfTheRelativeFuelWeightsOfTheStages.value,
            onParamChange = {
                params.theRatioOfTheRelativeFuelWeightsOfTheStages.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Начальная поперечная нагрузка на мидель [кг/м^2]",
            param = params.initialTransverseLoadOnTheMidsection.value,
            onParamChange = {
                params.initialTransverseLoadOnTheMidsection.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        ParamsField(
            name = "Относительная длина ракеты",
            param = params.relativeLengthOfTheRocket.value,
            onParamChange = {
                params.relativeLengthOfTheRocket.value = it
                isCorrect.value = !params.hasEmptyFields()
            }
        )

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(
                    color = Color.Gray,
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(if (isCorrect.value) LocalTheme.current.colors.primary else LocalTheme.current.colors.hint),
            onClick = {
                onParamsChange(
                    projectParams.copy(
                        maxFlyDistance = params.maxFlyDistance.value.toDouble(),
                        payloadWeight = params.payloadWeight.value.toDouble(),
                        initialThrustCapacityOfTheRocket = Pair(
                            first = params.initialThrustCapacityOfTheRocketFirst.value.toDouble(),
                            second = params.initialThrustCapacityOfTheRocketSecond.value.toDouble()
                        ),
                        engineNozzleShearPressure = Pair(
                            first = params.engineNozzleShearPressureFirst.value.toDouble(),
                            second = params.engineNozzleShearPressureSecond.value.toDouble()
                        ),
                        pressureInTheCombustionChambersOfEngines = Pair(
                            first = params.pressureInTheCombustionChambersOfEnginesFirst.value.toDouble(),
                            second = params.pressureInTheCombustionChambersOfEnginesSecond.value.toDouble()
                        ),
                        theRatioOfTheRelativeFuelWeightsOfTheStages = params.theRatioOfTheRelativeFuelWeightsOfTheStages.value.toDouble(),
                        initialTransverseLoadOnTheMidsection = params.initialTransverseLoadOnTheMidsection.value.toDouble(),
                        relativeLengthOfTheRocket = params.relativeLengthOfTheRocket.value.toDouble()
                    )
                )
            },
            enabled = isCorrect.value
        ) {
            Text(
                text = "Расчёт",
                style = LocalTheme.current.typo.regular,
                color = if (!isCorrect.value) LocalTheme.current.colors.onHint
                else LocalTheme.current.colors.onPrimary
            )
        }
    }
}

@Composable
private fun ParamsField(
    name: String,
    param: String,
    onParamChange: (param: String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        value = param,
        onValueChange = {
            val input = it.replace(',', '.')
            if (input.matches("^\\d*\\.?\\d*\$".toRegex()) || input.isBlank()) {
                onParamChange(input)
            }
        },
        textStyle = LocalTheme.current.typo.hint,
        label = {
            Text(
                text = name
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = LocalTheme.current.colors.primary,
            focusedLabelColor = LocalTheme.current.colors.onBackground,
            backgroundColor = LocalTheme.current.colors.background
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

