package core.solvers

import core.models.ProjectParams
import core.objects.Constants
import java.lang.Math.pow
import kotlin.math.pow

class DeterminationOfEngineEfficiencyIndicators(
    private val projectParams: ProjectParams
) {

    /**
     * Удельная тяга на расчетном режиме для первой ступени.
     */
    val specificGravityCalculatedFirst by lazy {
        with(projectParams) {
            0.95 * fuel.standardSpecificGravity +
                    21.0 +
                    0.76 * pressureInTheCombustionChambersOfEngines.first -
                    0.003 * pressureInTheCombustionChambersOfEngines.first.pow(2.0) -
                    70.0 * engineNozzleShearPressure.first +
                    25.0 * engineNozzleShearPressure.first.pow(2.0)
        }
    }

    /**
     * Удельная тяга на расчетном режиме для второй ступени.
     */
    val specificGravityCalculatedSecond by lazy {
        with(projectParams) {
            0.95 * fuel.standardSpecificGravity +
                    21.0 +
                    0.76 * pressureInTheCombustionChambersOfEngines.second -
                    0.003 * pressureInTheCombustionChambersOfEngines.second.pow(2.0) -
                    70.0 * engineNozzleShearPressure.second +
                    25.0 * engineNozzleShearPressure.second.pow(2.0)
        }
    }


    /**
     * Удельная тяга в пустоте для первой ступени.
     */
    val specificGravityInVoidFirst by lazy {
        with(projectParams) {
            specificGravityCalculatedFirst +
                    (((fuel.gasConstant * fuel.burningPoint) /
                            (specificGravityCalculatedFirst * Constants.FREE_FALL_ACCELERATION.pow(2.0))) *
                            (engineNozzleShearPressure.first / pressureInTheCombustionChambersOfEngines.first).pow(
                                (fuel.adiabaticValue - 1.0) / fuel.adiabaticValue
                            ))
        }
    }

    /**
     * Удельная тяга в пустоте для второй ступени.
     */
    val specificGravityInVoidSecond by lazy {
        with(projectParams) {
            specificGravityCalculatedSecond +
                    (((fuel.gasConstant * fuel.burningPoint) /
                            (specificGravityCalculatedSecond * Constants.FREE_FALL_ACCELERATION.pow(2.0))) *
                            (engineNozzleShearPressure.second / pressureInTheCombustionChambersOfEngines.second).pow(
                                (fuel.adiabaticValue - 1.0) / fuel.adiabaticValue
                            ))
        }
    }

    /**
     * Удельная тяга при старте с поверхности Земли
     */
    val specificGravityOnStartFromEarth by lazy {
        with(projectParams) {
            specificGravityInVoidFirst -
                    (((fuel.gasConstant * fuel.burningPoint) /
                            (specificGravityCalculatedFirst * Constants.FREE_FALL_ACCELERATION.pow(2.0))) *
                            (engineNozzleShearPressure.first / pressureInTheCombustionChambersOfEngines.first).pow(
                                (fuel.adiabaticValue - 1.0) / fuel.adiabaticValue
                            ) * (Constants.ATMOSPHERIC_PRESSURE_IN_BARS / engineNozzleShearPressure.first))
        }
    }

    /**
     * Средняя удельная тяга
     */
    val middleSpecificGravity by lazy {
        (1.0 / ((2.0 * Constants.STAGES_COUNT) - 1.0)) *
                (((specificGravityOnStartFromEarth + specificGravityInVoidFirst) / 2.0) +
                        (2.0 * (specificGravityInVoidFirst + specificGravityInVoidSecond)))
    }

    /**
     *  Тяговооруженность первой ступени
     */
    val firstStageThrustCapacityInVoid by lazy {
        with(projectParams) {
            initialThrustCapacityOfTheRocket.first * (specificGravityOnStartFromEarth / specificGravityInVoidFirst)
        }
    }

}


