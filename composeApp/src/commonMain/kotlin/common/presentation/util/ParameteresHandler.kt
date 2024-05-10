package common.presentation.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels
import core.solvers.DesignBallisticCalculation
import core.solvers.DeterminationOfEngineEfficiencyIndicators
import core.solvers.VerificationBallisticCalculation

object ParamsHandler {
    var projectParams: MutableState<ProjectParams> = mutableStateOf(ProjectParams.getDefault())
    var fuel: MutableState<Fuel> = mutableStateOf(Fuels.Dimethylhydrazine)
    var determination: MutableState<DeterminationOfEngineEfficiencyIndicators?> = mutableStateOf(null)
    var undefinedDesign: MutableState<DesignBallisticCalculation?> = mutableStateOf(null)
    var definedDesign: MutableState<DesignBallisticCalculation?> = mutableStateOf(null)
    var undefinedVerification: MutableState<VerificationBallisticCalculation?> = mutableStateOf(null)
    var definedVerification: MutableState<VerificationBallisticCalculation?> = mutableStateOf(null)
}