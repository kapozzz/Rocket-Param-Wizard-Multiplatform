package core.solvers.use

import core.solvers.MassAnalyze
import core.solvers.state.SolvesState
import kotlin.math.abs
import kotlin.math.pow

data class MassAnalyzer(
    private val solvesState: SolvesState,
    val firstInFirstStage: Double,
    val secondInFirstStage: Double,
    val firstInSecondStage: Double,
    val secondInSecondStage: Double,
    var second: MassAnalyze = MassAnalyze.getDefault(),
    var first: MassAnalyze = MassAnalyze.getDefault(),
    var third: MassAnalyze = MassAnalyze.getDefault(),
    var fourth: MassAnalyze = MassAnalyze.getDefault(),
) {

    var definedMassFirst = 0.0
    var definedMassSecond = 0.0
    var firstStageResult: MassOfEachPart? = null
    var secondStageResult: MassOfEachPart? = null

    init {
        start()
    }

    private fun calculate(
        start: Double,
        end: Double,
        previousMass: Double,
        fillFactor: Double,
        thrustCapacityInVoid: Double,
    ): Pair<MassAnalyze, MassAnalyze> {
        val a = MassAnalyze(
            mass = start,
            thrustCapacityInVoid = thrustCapacityInVoid,
            fillFactor = fillFactor,
            fuel = solvesState.fuel.value,
            previousMass = previousMass,
        )
        val b = MassAnalyze(
            mass = end,
            thrustCapacityInVoid = thrustCapacityInVoid,
            fillFactor = fillFactor,
            fuel = solvesState.fuel.value,
            previousMass = previousMass,
        )
        return Pair(a, b)
    }

    private fun start() {
        val a = calculate(
            start = firstInSecondStage,
            end = secondInSecondStage,
            previousMass = solvesState.projectParams.value.payloadWeight * 10.0.pow(-3),
            fillFactor = solvesState.definedDesign.reducedPropellantFillFactorForSecondStage,
            thrustCapacityInVoid = solvesState.projectParams.value.initialThrustCapacityOfTheRocket.second,
        )
        first = a.first
        second = a.second
        definedMassFirst =
            first.mass + abs(first.different) * (second.mass - first.mass) / (second.different - first.different)
        val b = calculate(
            start = firstInFirstStage,
            end = secondInFirstStage,
            previousMass = definedMassFirst,
            fillFactor = solvesState.definedDesign.reducedPropellantFillFactorForFirstStage,
            thrustCapacityInVoid = solvesState.determination.value.firstStageThrustCapacityInVoid,
        )
        third = b.first
        fourth = b.second
        definedMassSecond =
            third.mass + abs(third.different) * (fourth.mass - third.mass) / (fourth.different - third.different)
        val m02 =
            first.mass + abs(first.different) * (second.mass - first.mass) /
                    (second.different - first.different)
        secondStageResult = MassOfEachPart(
            m0 = m02,
            payloadWeight = solvesState.projectParams.value.payloadWeight * 10.0.pow(-3),
            definedDesign = solvesState.definedDesign,
            initialThrustCapacityOfTheRocket = solvesState.projectParams.value.initialThrustCapacityOfTheRocket.second,
            fuel = solvesState.fuel.value,
            reducedPropellantFillFactor = solvesState.definedDesign.reducedPropellantFillFactorForSecondStage
        )
        val m01 =
            third.mass + abs(third.different)*(fourth.mass-third.mass)/(fourth.different-third.different)
        firstStageResult = MassOfEachPart(
            m0 = m01,
            payloadWeight = m02,
            definedDesign = solvesState.definedDesign,
            initialThrustCapacityOfTheRocket = solvesState.determination.value.firstStageThrustCapacityInVoid,
            fuel = solvesState.fuel.value,
            reducedPropellantFillFactor = solvesState.definedDesign.reducedPropellantFillFactorForFirstStage
        )
    }
}