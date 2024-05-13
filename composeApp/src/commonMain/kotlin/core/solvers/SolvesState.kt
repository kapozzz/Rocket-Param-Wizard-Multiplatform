package core.solvers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels
import kotlinx.coroutines.flow.MutableStateFlow

data class SolvesState(
    val projectParams: MutableStateFlow<ProjectParams> = MutableStateFlow(
        ProjectParams.getDefault()
    ),
    val fuel: MutableStateFlow<Fuel> = MutableStateFlow(
        Fuels.Dimethylhydrazine
    ),
    val determination: MutableState<DeterminationOfEngineEfficiencyIndicators> = mutableStateOf(
        DeterminationOfEngineEfficiencyIndicators(projectParams.value, fuel.value)
    ),
    val undefinedDesign: MutableState<DesignBallisticCalculation> = mutableStateOf(
        DesignBallisticCalculation(projectParams.value, determination.value)
    ),
    val undefinedVerification: MutableState<VerificationBallisticCalculation> = mutableStateOf(
        VerificationBallisticCalculation(
            projectParams = projectParams.value,
            fuel = fuel.value,
            design = undefinedDesign.value,
            determination = determination.value
        )
    ),
    var definedDesign: DesignBallisticCalculation = undefinedDesign.value,
    var definedVerification: VerificationBallisticCalculation = undefinedVerification.value,
) {
    fun define() {
        var counter = 0
        while (definedVerification.deltaL >= 3.0) {
            definedDesign = DesignBallisticCalculation(
                projectParams = projectParams.value,
                determination = determination.value,
                definedFillFactor = definedVerification.definedDeltaUpr
            )
            definedVerification = VerificationBallisticCalculation(
                projectParams = projectParams.value,
                fuel = fuel.value,
                determination = determination.value,
                design = definedDesign
            )
            counter++
        }
        println("counter - $counter")
    }
}

@Composable
fun rememberSolvesState(): SolvesState {
    val state = remember {
        SolvesState()
    }
    return state
}