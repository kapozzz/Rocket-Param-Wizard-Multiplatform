package core.solvers

import core.objects.Constants
import core.solvers.state.SolvesState
import core.solvers.use.MassOfEachPart
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.tan

data class LastStep(
    val solvesState: SolvesState,
    val f: Double,
    val k: Double,
) {

    val fInRadians by lazy {
        (f * PI) / 180
    }

    /**
     * Секундный расход массы топлива первой ступени
     */
    val m1Point by lazy {
        solvesState.massAnalyzer.value.firstStageResult!!.specificGravityInVoid * 1000 /
                (solvesState.determination.value.specificGravityInVoidFirst * Constants.FREE_FALL_ACCELERATION)
    }

    /**
     * Секундный расход массы топлива второй ступени
     */
    val m2point by lazy {
        solvesState.massAnalyzer.value.secondStageResult!!.specificGravityInVoid * 1000 /
                (solvesState.determination.value.specificGravityInVoidSecond * Constants.FREE_FALL_ACCELERATION)
    }

    /**
     * Время полета первой ступени ракеты
     */
    val tk1 by lazy {
        solvesState.massAnalyzer.value.firstStageResult!!.omegaGasProcess * 1000 / m1Point
    }

    /**
     * Время полета второй ступени ракеты
     */
    val tk2 by lazy {
        solvesState.massAnalyzer.value.secondStageResult!!.omegaGasProcess * 1000 / m2point
    }

    /**
     * Тяга двигателей ступеней на Земле
     */
    val P01 by lazy {
        solvesState.massAnalyzer.value.firstStageResult!!.m0 * Constants.FREE_FALL_ACCELERATION / solvesState.projectParams.value.initialThrustCapacityOfTheRocket.first
    }

    /**
     * Объем бака окислителя первой ступени
     */
    val Vok1 by lazy {
        solvesState.massAnalyzer.value.firstStageResult!!.omegaOxidant * 1000 / solvesState.fuel.value.oxidantDensity
    }

    /**
     * Объем бака горючего первой ступени
     */
    val Vbk1 by lazy {
        solvesState.massAnalyzer.value.firstStageResult!!.omegaFuel * 1000 / solvesState.fuel.value.fuelDensity
    }

    /**
     * Объем бака окислителя второй ступени
     */
    val Vok2 by lazy {
        solvesState.massAnalyzer.value.secondStageResult!!.omegaOxidant * 1000 / solvesState.fuel.value.oxidantDensity
    }

    /**
     * Объем бака горючего для ступени
     */
    val Vbk2 by lazy {
        solvesState.massAnalyzer.value.secondStageResult!!.omegaFuel * 1000 / solvesState.fuel.value.fuelDensity
    }

    /**
     * Диаметр ракеты
     */
    val dm by lazy {
        (4 * solvesState.massAnalyzer.value.firstStageResult!!.m0 * 1000 / (PI * solvesState.projectParams.value.initialTransverseLoadOnTheMidsection)).pow(
            0.5
        )
    }

    /**
     * Приближенное значение длины ракеты
     */
    val lprib by lazy {
        dm * solvesState.projectParams.value.relativeLengthOfTheRocket
    }


    /**
     * Длина головной части
     */
    val lgh by lazy {
        dm / (2 * tan(fInRadians))
    }

    /**
     * Длина бака горючего ракетного блока первой ступени
     */
    val lbg1 by lazy {
        4 * Vbk1 / (PI * dm.pow(2))
    }

    /**
     * Длина бака окислителя ракетного блока первой ступени
     */
    val lbok1 by lazy {
        4 * Vok1 / (PI * dm.pow(2))
    }

    /**
     * Длина приборного отсека ракетного блока первой ступени
     */
    val lpo1 by lazy {
        k / dm
    }

    /**
     * Длина хвостового отсека ракетного блока первой ступени
     */
    val lxo1 by lazy {
        ldy1 + 0.05
    }

    /**
     * Длина первой ступени
     */
    val l1 by lazy {
        lbok1 + lbg1 + lpo1 + lxo1
    }

    /**
     * Длина бака окислителя ракетного блока второй ступени
     */
    val lbok2 by lazy {
        4 * Vok2 / (PI * dm.pow(2))
    }

    /**
     * Длина бака горючего ракетного блока второй ступени
     */
    val lbg2 by lazy {
        4 * Vbk2 / (PI * dm.pow(2))
    }

    /**
     * Длина приборного отсека ракетного блока второй ступени
     */
    val lpo2 by lazy {
        k / dm
    }

    /**
     * Длина хвостового отсека ракетного блока второй ступени
     */
    val lxo2 by lazy {
        ldy2 + 0.05
    }

    /**
     * длина соплового аппарата первой ступени, м
     */
    val lc1 by lazy {
        7 * sqrt(
            ((solvesState.massAnalyzer.value.firstStageResult!!.specificGravityInVoid * 1000)
                    / (4 * solvesState.projectParams.value.engineNozzleShearPressure.first * solvesState.determination.value.specificGravityInVoidFirst * 100000))
        )
    }

    /**
     * длина двигательной установки первой ступени, м
     */
    val ldy1 by lazy {
        2 * lc1
    }

    /**
     * длина соплового аппарата второй ступени, м
     */
    val lc2 by lazy {
        7 * sqrt(
            ((solvesState.massAnalyzer.value.secondStageResult!!.specificGravityInVoid * 1000)
                    / (4 * solvesState.projectParams.value.engineNozzleShearPressure.second * solvesState.determination.value.specificGravityInVoidSecond * 100000))
        )
    }

    /**
     * длина двигательной установки второй ступени, м
     */
    val ldy2 by lazy {
        2 * lc2
    }

    /**
     * Длина второй ступени
     */
    val l2 by lazy {
        lbok2 + lbg2 + lpo2 + lxo2
    }

    /**
     * Длина ракеты
     */
    val rocketLength by lazy {
        lgh + l1 + l2
    }

}