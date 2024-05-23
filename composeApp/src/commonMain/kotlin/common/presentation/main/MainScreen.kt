package common.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.models.ProjectParams
import common.presentation.main.components.FuelPicker
import common.presentation.main.components.Head
import common.presentation.main.components.ParametersPicker
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState
import common.ui.theme.LocalTheme
import core.objects.Fuels
import kotlin.math.pow

@Composable
fun MainScreen() {

    val navigator = LocalNavigator.current
    val solvesState = LocalSolvesState.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Head(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp)
            )
            TextButton(
                onClick = {
                    solvesState.projectParams.value = ProjectParams.getDefault()
                    solvesState.fuel.value = Fuels.LiquidHydrogen
                    navigator.navigate(AppScreen.Determination.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        color = LocalTheme.current.colors.onBackground,
                        width = 1.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(LocalTheme.current.colors.hint)
            ) {
                Text(
                    text = "Использовать стандартные значения (вариант автора)",
                    color = LocalTheme.current.colors.onBackground,
                    style = LocalTheme.current.typo.regular
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = "Собственный вариант",
                color = LocalTheme.current.colors.onBackground,
                style = LocalTheme.current.typo.regular,
                textAlign = TextAlign.Start
            )
            FuelPicker(
                solvesState.fuel.value,
                onFuelClick = { newFuel ->
                    solvesState.fuel.value = newFuel
                }
            )
            ParametersPicker(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                onParamsChange = {
                    solvesState.projectParams.value = it
                    solvesState.rebuild()
                    navigator.navigate(AppScreen.Determination.route)
                },
                projectParams = solvesState.projectParams.value
            )
        }
    }
}