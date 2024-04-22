package common.core.base.models

import common.core.base.objects.Fuels
import java.lang.Math.pow

/**
 *  Проектные параметры:
 *
 *  fuel - топливо.
 *
 *  maxFlyDistance - максимальная дальность полёта [м].
 *
 *  payloadWeight - масса полезной нагрузки [кг].
 *
 *  initialThrustCapacityOfTheRocket - начальная тяговооруженность [Безразмерная].
 *
 *  engineNozzleShearPressure - давление на срезе сопла двигателя [бар].
 *
 *  pressureInTheCombustionChambersOfEngines - давление в камерах сгорания двигателей [бар].
 *
 *  theRatioOfTheRelativeFuelWeightsOfTheStages - коэффициент соотношения относительных весов топлива ступеней [Безразмерная].
 *
 *  initialTransverseLoadOnTheMidsection - начальная поперечная нагрузка на мидель [кг/м^2].
 *
 *  relativeLengthOfTheRocket - относительная длина ракеты [Безразмерная].
 *
 */

data class ProjectParams(
    val fuel: Fuel,
    val maxFlyDistance: Double,
    val payloadWeight: Double,
    val initialThrustCapacityOfTheRocket: Pair<Double, Double>,
    val engineNozzleShearPressure: Pair<Double, Double>,
    val pressureInTheCombustionChambersOfEngines: Pair<Double, Double>,
    val theRatioOfTheRelativeFuelWeightsOfTheStages: Double,
    val initialTransverseLoadOnTheMidsection: Double,
    val relativeLengthOfTheRocket: Double
) {

    companion object {
        fun getDefault(): ProjectParams = ProjectParams(
            fuel = Fuels.Dimethylhydrazine,
            initialThrustCapacityOfTheRocket = 0.58 to 0.76,
            pressureInTheCombustionChambersOfEngines = 112.0 to 98.0,
            maxFlyDistance = 90000.0,
            payloadWeight = 6000.0,
            engineNozzleShearPressure = 0.575 to 0.15,
            theRatioOfTheRelativeFuelWeightsOfTheStages = 1.25,
            initialTransverseLoadOnTheMidsection = 14.0 * 10 * 10 * 10,
            relativeLengthOfTheRocket = 10.0
        )
    }
}

















