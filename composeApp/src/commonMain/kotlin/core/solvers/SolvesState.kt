package core.solvers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels

data class SolvesState(
    val projectParams: MutableState<ProjectParams> = mutableStateOf(
        ProjectParams.getDefault()
    ),
    val fuel: MutableState<Fuel> = mutableStateOf(
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
    var defCounter = 0
    // Вычислить всё заново
    fun rebuild() {
        determination.value = DeterminationOfEngineEfficiencyIndicators(projectParams.value, fuel.value)
        undefinedDesign.value = DesignBallisticCalculation(projectParams.value, determination.value)
        undefinedVerification.value = VerificationBallisticCalculation(
            projectParams = projectParams.value,
            fuel = fuel.value,
            design = undefinedDesign.value,
            determination = determination.value
        )
        definedDesign = undefinedDesign.value
        definedVerification = undefinedVerification.value
    }
    // Уточняем значения
    fun define() {
        defCounter = 0
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
            defCounter++
        }
    }
}

@Composable
fun rememberSolvesState(): SolvesState {
    val state = remember {
        SolvesState()
    }
    return state
}