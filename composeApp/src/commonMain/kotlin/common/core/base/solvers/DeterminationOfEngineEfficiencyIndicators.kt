package common.core.base.solvers

import common.core.base.ATMOSPHERIC_PRESSURE_IN_BARS
import common.core.base.FREE_FALL_ACCELERATION
import common.core.base.STAGES_COUNT
import common.core.base.models.ProjectParams
import java.lang.Math.pow

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
                    0.003 * pow(pressureInTheCombustionChambersOfEngines.first, 2.0) -
                    70.0 * engineNozzleShearPressure.first +
                    25.0 * pow(engineNozzleShearPressure.first, 2.0)
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
                    0.003 * pow(pressureInTheCombustionChambersOfEngines.second, 2.0) -
                    70.0 * engineNozzleShearPressure.second +
                    25.0 * pow(engineNozzleShearPressure.second, 2.0)
        }
    }


    /**
     * Удельная тяга в пустоте для первой ступени.
     */
    val specificGravityInVoidFirst by lazy {
        with(projectParams) {
            specificGravityCalculatedFirst +
                    (((fuel.gasConstant * fuel.burningPoint) /
                            (specificGravityCalculatedFirst * pow(FREE_FALL_ACCELERATION, 2.0))) *
                            pow(
                                engineNozzleShearPressure.first / pressureInTheCombustionChambersOfEngines.first,
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
                            (specificGravityCalculatedSecond * pow(FREE_FALL_ACCELERATION, 2.0))) *
                            pow(
                                engineNozzleShearPressure.second / pressureInTheCombustionChambersOfEngines.second,
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
                            (specificGravityCalculatedFirst * pow(FREE_FALL_ACCELERATION, 2.0))) *
                            pow(
                                engineNozzleShearPressure.first / pressureInTheCombustionChambersOfEngines.first,
                                (fuel.adiabaticValue - 1.0) / fuel.adiabaticValue
                            ) * (ATMOSPHERIC_PRESSURE_IN_BARS / engineNozzleShearPressure.first))
        }
    }

    /**
     * Средняя удельная тяга
     */
    val middleSpecificGravity by lazy {
        (1.0 / ((2.0 * STAGES_COUNT) - 1.0)) *
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


