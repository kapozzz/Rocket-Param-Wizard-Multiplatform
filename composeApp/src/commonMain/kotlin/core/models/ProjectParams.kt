package core.models

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
            initialThrustCapacityOfTheRocket = 0.55 to 0.7,
            pressureInTheCombustionChambersOfEngines = 80.0 to 50.0,
            maxFlyDistance = 6000000.0,
            payloadWeight = 3000.0,
            engineNozzleShearPressure = 0.45 to 0.1,
            theRatioOfTheRelativeFuelWeightsOfTheStages = 1.1,
            initialTransverseLoadOnTheMidsection = 16000.0,
            relativeLengthOfTheRocket = 11.0
        )
    }
}

















