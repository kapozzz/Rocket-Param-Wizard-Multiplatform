package core.solvers

import core.models.Fuel
import core.models.ProjectParams
import core.objects.Constants
import core.objects.tables.Tables
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

data class Verification(
    private val projectParams: ProjectParams,
    private val fuel: Fuel,
    private val determination: Determination,
    private val design: Design,
    var F1: Double = Tables.getF1(
        design.reducedPropellantFillFactorForFirstStage,
        design.dependenciesParameters.angle
    ),
    var F2: Double = Tables.getF2(
        design.reducedPropellantFillFactorForFirstStage,
        design.dependenciesParameters.angle
    ),
    var F4: Double = Tables.getF4(
        design.reducedPropellantFillFactorForFirstStage,
        design.dependenciesParameters.angle
    ),
    var Ig1: Double = Tables.getIg(
        design.reducedPropellantFillFactorForFirstStage,
        design.dependenciesParameters.angle
    ),
    var Ip1: Double = Tables.getIp1(
        design.reducedPropellantFillFactorForFirstStage
    ),
    var Ix1: Double = Tables.getIx1(design.reducedPropellantFillFactorForFirstStage)
) {
    /**
     * Число Циолковского для первой ступени
     */
    val cValueFirstStage by lazy {
        ln(1 / (1 - design.reducedPropellantFillFactorForFirstStage))
    }

    /**
     * Число Циолковского для второй ступени
     */
    val cValueSecondStage by lazy {
        ln(1 / (1 - design.reducedPropellantFillFactorForSecondStage))
    }

    /**
     * Потери скорости на преодоление лобового сопротивления
     */
    val velocityLessOnFrontPowers by lazy {
        (Ix1 /
                (projectParams.initialThrustCapacityOfTheRocket.first *
                        cbrt(
                            sin(
                                design.angleInRadians
                            ).pow(2)
                        )
                        )) *
                (Constants.GREAT_MID_LOAD / projectParams.initialTransverseLoadOnTheMidsection)
    }

    /**
     * Уравнение скорости в проекции на оси скоростной (поточной) системы координат после интегрирования по весу выгоревшего топлива µк1
     */
    val firstVelocityEqualization by lazy {
        (Constants.FREE_FALL_ACCELERATION * determination.specificGravityInVoidFirst * cValueFirstStage) -
                (Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.first * determination.specificGravityOnStartFromEarth *
                        Ig1) - (determination.specificGravityOnStartFromEarth * Ip1) - velocityLessOnFrontPowers
    }

    /**
     * A1
     */

    val A1 by lazy {
        Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.first * determination.specificGravityOnStartFromEarth.pow(
            2
        )
    }

    /**
     * A2
     */
    val A2 by lazy {
        Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.second * determination.specificGravityInVoidSecond.pow(
            2
        )
    }

    /**
     * Высота hк1
     */
    val heightHk1 by lazy {
            A1 * (F1 - (0.5 * projectParams.initialThrustCapacityOfTheRocket.first * Ig1.pow(2)))
    }

    /**
     * Дальность полёта lk1
     */
    val distanceLk1 by lazy {
        A1 * (F2 - projectParams.initialThrustCapacityOfTheRocket.first * F4)
    }

    /**
     * Vk2
     */
    val secondVelocityEqualization by lazy {
        firstVelocityEqualization + Constants.FREE_FALL_ACCELERATION * determination.specificGravityInVoidSecond *
                (cValueSecondStage - projectParams.initialThrustCapacityOfTheRocket.second * design.reducedPropellantFillFactorForSecondStage *
                        sin(design.angleInRadians))
    }

    /**
     * B2
     */
    val B2 by lazy {
        (design.reducedPropellantFillFactorForSecondStage * firstVelocityEqualization / Constants.FREE_FALL_ACCELERATION / determination.specificGravityInVoidSecond) +
                cValueSecondWithoutUk1 - (0.5 * projectParams.initialThrustCapacityOfTheRocket.second * design.reducedPropellantFillFactorForSecondStage.pow(
            2
        ) *
                sin(design.angleInRadians))
    }

    /**
     * Число Циолковского Ц2
     */
    val cValueSecondWithoutUk1 by lazy {
        design.reducedPropellantFillFactorForSecondStage + (1 - design.reducedPropellantFillFactorForSecondStage) *
                ln(1 - design.reducedPropellantFillFactorForSecondStage)
    }

    /**
     * Высота hk2
     */
    val heightHk2 by lazy {
        heightHk1 + (A2 * B2 * sin(design.angleInRadians))
    }

    /**
     * Дальность lk2
     */
    val distanceLk2 by lazy {
        distanceLk1 + (A2 * B2 * cos(design.angleInRadians))
    }

    /**
     * vk
     */
    val vk by lazy {
        (secondVelocityEqualization.pow(2) * (Constants.EARTH_RADIUS + heightHk2)) / (Constants.GRAVITY_CONSTANT * Constants.EARTH_WEIGHT)
    }

    /**
     * c
     */
    val c by lazy {
        vk * heightHk2
    }

    /**
     * a
     */
    val a by lazy {
        (2 * Constants.EARTH_RADIUS * (1 + tan(design.angleInRadians).pow(2) - vk)) - vk * heightHk2
    }

    /**
     * b
     */
    val b by lazy {
        vk * Constants.EARTH_RADIUS * tan(design.angleInRadians)
    }

    /**
     * Центральный угол
     */
    val centralAngleBeta by lazy {
        2 * atan((b + sqrt(b.pow(2) + (a * c))) / a)
    }

    /**
     * Пассивная дальность полёта
     */
    val passiveFlyDistance by lazy {
        Constants.EARTH_RADIUS * centralAngleBeta
    }

    // TODO откуда 0.001?
    /**
     * Полная дальность полёта ракеты
     */
    val totalFlyDistance by lazy {
        passiveFlyDistance + distanceLk2
    }

    // %
    val deltaL by lazy {
        (abs(projectParams.maxFlyDistance - totalFlyDistance) / projectParams.maxFlyDistance) * 100
    }

    val definedDeltaL by lazy {
        (projectParams.maxFlyDistance - totalFlyDistance)
    }

    val expDefinedVk2onDefinedUpr by lazy {
        (Constants.FREE_FALL_ACCELERATION * determination.middleSpecificGravity) / (1 - design.reducedPropellantFillFactor)
    }

    val expDefinedDeltaLonDefinedVk2 by lazy {
        (Constants.EARTH_RADIUS / (tan(design.angleInRadians) + (heightHk2 * (tan(
            centralAngleBeta / 2
        ).pow(-1))) / Constants.EARTH_RADIUS)) * (4 * sin(centralAngleBeta / 2).pow(2)) / (vk * secondVelocityEqualization * cos(
            design.angleInRadians
        ).pow(2))
    }

    val deltaUpr by lazy {
        definedDeltaL / (expDefinedDeltaLonDefinedVk2 * expDefinedVk2onDefinedUpr)
    }

    val definedDeltaUpr by lazy {
        design.reducedPropellantFillFactor + deltaUpr
    }
}