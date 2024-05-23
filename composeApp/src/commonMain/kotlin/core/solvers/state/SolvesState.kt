package core.solvers.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import core.models.Fuel
import core.models.ProjectParams
import core.objects.Fuels
import core.solvers.Design
import core.solvers.Determination
import core.solvers.MassAnalyze
import core.solvers.Verification
import core.solvers.use.MassAnalyzer

data class SolvesState(
    val projectParams: MutableState<ProjectParams> = mutableStateOf(
        ProjectParams.getDefault()
    ),
    val fuel: MutableState<Fuel> = mutableStateOf(
        Fuels.Dimethylhydrazine
    ),
    val determination: MutableState<Determination> = mutableStateOf(
        Determination(projectParams.value, fuel.value)
    ),
    val undefinedDesign: MutableState<Design> = mutableStateOf(
        Design(projectParams.value, determination.value)
    ),
    val undefinedVerification: MutableState<Verification> = mutableStateOf(
        Verification(
            projectParams = projectParams.value,
            fuel = fuel.value,
            design = undefinedDesign.value,
            determination = determination.value
        )
    ),
    var definedDesign: Design = undefinedDesign.value,
    var definedVerification: Verification = undefinedVerification.value,
) {

    val massAnalyzer: MutableState<MassAnalyzer> = mutableStateOf(
        MassAnalyzer(
            solvesState = this,
            firstInFirstStage = 30.0,
            secondInFirstStage = 140.0,
            firstInSecondStage = 14.0,
            secondInSecondStage = 40.0
        )
    )

    var defCounter = 0

    // Вычислить всё заново
    fun rebuild() {
        determination.value = Determination(projectParams.value, fuel.value)
        undefinedDesign.value = Design(projectParams.value, determination.value)
        undefinedVerification.value = Verification(
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
            definedDesign = Design(
                projectParams = projectParams.value,
                determination = determination.value,
                definedFillFactor = definedVerification.definedDeltaUpr
            )
            definedVerification = Verification(
                projectParams = projectParams.value,
                fuel = fuel.value,
                determination = determination.value,
                design = definedDesign
            )
            defCounter++
        }
        println(defCounter)
    }
}

@Composable
fun rememberSolvesState(): SolvesState {
    val state = remember {
        SolvesState()
    }
    return state
}