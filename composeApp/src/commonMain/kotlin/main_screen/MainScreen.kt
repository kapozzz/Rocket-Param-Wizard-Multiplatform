package main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.models.ProjectParams
import common.presentation.FuelPicker
import common.presentation.Head
import common.presentation.ParametersPicker

@Composable
fun MainScreen(
    state: MainScreenState,
    onComputeClick: (ProjectParams) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Min),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Head(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp))
        FuelPicker(
            state.fuel.value,
            onFuelClick = { newFuel ->
                state.fuel.value = newFuel
            }
        )
        ParametersPicker(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                ),
            onParamsChange = {
               onComputeClick(it)
            },
            projectParams = state.projectParams.value
        )
    }
}