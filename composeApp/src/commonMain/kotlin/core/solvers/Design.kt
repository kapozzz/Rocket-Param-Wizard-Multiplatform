package core.solvers

import core.models.ProjectParams
import core.objects.Constants
import core.objects.tables.linearInterpolate
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.tan

/**
 *  flyDistance - дальность полёта [м]
 *
 *  lCoord - координата [м]
 *
 *  hCoord - координата [м]
 *
 *  angle - угол наклона вектора скорости к местному горизноту [град]
 *
 */
data class DependenciesParametersColumn(
    val flyDistance: Double,
    val lCoord: Double,
    val hCoord: Double,
    val angle: Double
)

class Design(
    private val projectParams: ProjectParams,
    private val determination: Determination,
    private val definedFillFactor: Double? = null
) {

    val dependenciesParameters: DependenciesParametersColumn =
        DependenciesParameters.getParameters(projectParams.maxFlyDistance)

    /**
     * Поправочный коэффициент
     */
    val definedCoefficient by lazy {
        projectParams.initialThrustCapacityOfTheRocket.second / 0.5
    }

    /**
     * Уточненное lCoord
     */
    val definedLCoord by lazy {
        definedCoefficient * dependenciesParameters.lCoord
    }

    /**
     * Уточненное hCoord
     */
    val definedhCoord by lazy {
        definedCoefficient * dependenciesParameters.hCoord
    }

    /**
     * Центральный угол
     */
    val centralAngle by lazy {
        (projectParams.maxFlyDistance - definedLCoord) / Constants.EARTH_RADIUS
    }

    /**
     * Угол в радианах
     */
    val angleInRadians by lazy {
        (dependenciesParameters.angle * PI) / 180
    }

    /**
     * Безразмерная скорость в конце активного участка полета
     */
    val dimensionlessVelocityOnFinishActiveFly by lazy {
        (2 * Constants.EARTH_RADIUS * (1 + tan(angleInRadians).pow(2)) * tan(
            centralAngle / 2
        ).pow(2)) / ((((2 * Constants.EARTH_RADIUS) + definedhCoord) * tan(
            centralAngle / 2
        ).pow(
            2
        )) + ((2 * Constants.EARTH_RADIUS) * tan(angleInRadians) * tan(centralAngle / 2)) + definedhCoord)
    }

    /**
     * Скорость в конце активного участка полета
     */
    val velocityOnFinishActiveFly by lazy {
        sqrt(
            (Constants.GRAVITY_CONSTANT * Constants.EARTH_WEIGHT * dimensionlessVelocityOnFinishActiveFly)
                    / (Constants.EARTH_RADIUS + definedhCoord)
        )
    }

    /**
     * Скорость истечения топлива
     */
    val fuelFlowRate by lazy {
        Constants.FREE_FALL_ACCELERATION * determination.middleSpecificGravity
    }

    val Kv by lazy {
        linearInterpolate(5000000.0, 14000000.0 , projectParams.maxFlyDistance, 1.25, 1.15)
    }

    /**
     * Коэффициент заполнения ракеты топливом
     */
    val reducedPropellantFillFactor by lazy {
        definedFillFactor
            ?: (1 - exp(-1 * ((Kv * velocityOnFinishActiveFly) / (Constants.FREE_FALL_ACCELERATION * determination.middleSpecificGravity))))
    }

    /**
     * Коэффициент заполнения ракеты топливом для первой ступени
     */
    val reducedPropellantFillFactorForFirstStage by lazy {
        val x = projectParams.theRatioOfTheRelativeFuelWeightsOfTheStages
        ((1 + x) / (2 * x)) - sqrt(((1 + x) / (2 * x)).pow(2) - (reducedPropellantFillFactor / x))
    }

    /**
     * Коэффициент заполнения ракеты топливом для второй ступени
     */
    val reducedPropellantFillFactorForSecondStage by lazy {
        projectParams.theRatioOfTheRelativeFuelWeightsOfTheStages * reducedPropellantFillFactorForFirstStage
    }

    /**
     * Идеальная скорость
     */
    val greatVelocity by lazy {
        (fuelFlowRate * ln(1 / (1 - reducedPropellantFillFactor))) - velocityOnFinishActiveFly * (1 - Kv)
    }

    fun result(): List<String> {
        return listOf(
            "Коэффициент заполнения ракеты топливом: $reducedPropellantFillFactor",
            "Коэффициент заполения ракеты топливом для первой ступени: $reducedPropellantFillFactorForFirstStage",
            "Коэффициент заполнения ракеты топливом для второй ступени: $reducedPropellantFillFactorForSecondStage",
            "Идеальная скорость: ${greatVelocity}"
        )
    }
}

object DependenciesParameters {
    fun getParameters(distance: Double): DependenciesParametersColumn {
        var param: DependenciesParametersColumn? = parameters.find { it.flyDistance == distance }
        if (param == null) {
            var left: DependenciesParametersColumn? = null
            var right: DependenciesParametersColumn? = null
            for (index in 0 until parameters.lastIndex) {
                if (parameters[index].flyDistance < distance && parameters[index + 1].flyDistance > distance) {
                    left = parameters[index]
                    right = parameters[index + 1]
                }
            }
            if (left == null || right == null) throw IllegalStateException("Неверная дальность полёта")
            param = DependenciesParametersColumn(
                flyDistance = distance,
                lCoord = (left.lCoord + right.lCoord) / 2,
                hCoord = (left.hCoord + right.hCoord) / 2,
                angle = (left.angle + right.angle) / 2,
            )
        }
        return param
    }

    private val parameters: Array<DependenciesParametersColumn> = arrayOf(
        DependenciesParametersColumn(
            flyDistance = 2000000.0,
            angle = 39.0,
            hCoord = 90000.0,
            lCoord = 110000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 4000000.0,
            angle = 35.0,
            hCoord = 135000.0,
            lCoord = 195000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 6000000.0,
            angle = 30.0,
            hCoord = 165000.0,
            lCoord = 285000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 8000000.0,
            angle = 28.0,
            hCoord = 195000.0,
            lCoord = 365000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 10000000.0,
            angle = 25.0,
            hCoord = 225000.0,
            lCoord = 480000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 12000000.0,
            angle = 23.0,
            hCoord = 250000.0,
            lCoord = 590000.0
        ),
        DependenciesParametersColumn(
            flyDistance = 14000000.0,
            angle = 20.0,
            hCoord = 270000.0,
            lCoord = 740000.0
        )
    )
}

