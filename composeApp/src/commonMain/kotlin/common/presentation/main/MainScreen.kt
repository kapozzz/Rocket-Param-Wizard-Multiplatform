package common.presentation.main

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
import common.presentation.main.components.FuelPicker
import common.presentation.main.components.Head
import common.presentation.main.components.ParametersPicker
import common.presentation.util.ParamsHandler
import common.ui.theme.LocalNavigator

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val navigator = LocalNavigator.current

    Column(
        modifier = modifier.width(IntrinsicSize.Min),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Head(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp))
        FuelPicker(
            ParamsHandler.fuel.value,
            onFuelClick = { newFuel ->
                ParamsHandler.fuel.value = newFuel
            }
        )
        ParametersPicker(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                ),
            onParamsChange = {
                ParamsHandler.projectParams.value = it
                navigator.navigate(CurrentScreen.Determination.route)
            },
            projectParams = ParamsHandler.projectParams.value
        )
    }
}