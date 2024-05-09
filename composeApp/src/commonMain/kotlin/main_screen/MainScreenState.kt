package main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels

data class MainScreenState(
    val fuel: MutableState<Fuel> = mutableStateOf(Fuels.Paraffin),
    val projectParams: MutableState<ProjectParams> = mutableStateOf(ProjectParams.getDefault())
)

sealed class CurrentScreen() {

    data object Main: CurrentScreen()

    data object Results: CurrentScreen()

}