package core.solvers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels
import kotlinx.coroutines.flow.MutableStateFlow

class SolvesState(
    val projectParams: MutableStateFlow<ProjectParams> = MutableStateFlow(ProjectParams.getDefault()),
    val fuel: MutableStateFlow<Fuel> = MutableStateFlow(Fuels.LiquidHydrogen)
) {


    val determination: MutableState<DeterminationOfEngineEfficiencyIndicators> = mutableStateOf(
        DeterminationOfEngineEfficiencyIndicators(projectParams.value, fuel.value)
    )

    val undefinedDesign: MutableState<DesignBallisticCalculation> = mutableStateOf(
        DesignBallisticCalculation(projectParams.value, fuel.value, determination.value)
    )

    val undefinedVerification: MutableState<VerificationBallisticCalculation> = mutableStateOf(
        VerificationBallisticCalculation(
            projectParams.value,
            fuel = fuel.value,
            undefinedDesign.value,
            determination.value,
        )
    )
}

@Composable
fun rememberSolvesState(): SolvesState {
    val state = remember {
        SolvesState()
    }
    return state
}