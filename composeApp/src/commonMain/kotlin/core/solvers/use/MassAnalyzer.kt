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

    init {
        start()
    }

    private fun calculate(
        start: Double,
        end: Double,
        previousMass: Double,
        fillFactor: Double,
        thrustCapacityInVoid: Double,
        fillFactorSecondStage: Double
    ): Pair<MassAnalyze, MassAnalyze> {
        var _start = start
        var _end = end
        val step = 1.0 // шаг 1 тонна
        var a: MassAnalyze? = null
        var b: MassAnalyze? = null
        fun initialize() {
            a = MassAnalyze(
                mass = _start,
                thrustCapacityInVoid = thrustCapacityInVoid,
                fillFactor = fillFactor,
                fuel = solvesState.fuel.value,
                previousMass = previousMass,
            )
            b = MassAnalyze(
                mass = _end,
                thrustCapacityInVoid = thrustCapacityInVoid,
                fillFactor = fillFactor,
                fuel = solvesState.fuel.value,
                previousMass = previousMass,
            )
        }
        do {
            initialize()
            if (a!!.different > 0) _start -= step
            if (b!!.different < 0) _end += step
        } while (a!!.different > 0 || b!!.different < 0)
        return Pair(a!!, b!!)
    }

    private fun start() {
        val a = calculate(
            start = firstInSecondStage,
            end = secondInSecondStage,
            previousMass = solvesState.projectParams.value.payloadWeight * 10.0.pow(-3),
            fillFactor = solvesState.definedDesign.reducedPropellantFillFactorForSecondStage,
            thrustCapacityInVoid = solvesState.projectParams.value.initialThrustCapacityOfTheRocket.second,
            fillFactorSecondStage = solvesState.definedDesign.reducedPropellantFillFactorForSecondStage
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
            fillFactorSecondStage = solvesState.definedDesign.reducedPropellantFillFactorForSecondStage
        )
        third = b.first
        fourth = b.second
        definedMassSecond =
            third.mass + abs(third.different) * (fourth.mass - third.mass) / (fourth.different - third.different)
    }
}