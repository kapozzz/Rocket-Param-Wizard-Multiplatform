package core.solvers

import core.models.Fuel
import core.models.ProjectParams
import core.objects.Constants
import core.objects.DependentConstants
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin


class VerificationBallisticCalculation(
    private val projectParams: ProjectParams,
    private val fuel: Fuel,
    private val designBallisticCalculation: DesignBallisticCalculation,
    private val determinationOfEngineEfficiencyIndicators: DeterminationOfEngineEfficiencyIndicators
) {

    /**
     * Число Циолковского для первой ступени
     */
    val cValueFirstStage by lazy {
        ln(1 / (1 - designBallisticCalculation.reducedPropellantFillFactorForFirstStage))
    }

    /**
     * Число Циолковского для второй ступени
     */
    val cValueSecondStage by lazy {
        ln(1 / (1 - designBallisticCalculation.reducedPropellantFillFactorForSecondStage))
    }

    /**
     *  Потери скорости на преодоление гравитационных сил
     */
    val velocityLessOnGravitationPowers by lazy {
        DependentConstants.getIg(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
            designBallisticCalculation.dependenciesParameters.angle
        )
    }

    /**
     * Потери скорости на преодоление лобового сопротивления
     */
    val velocityLessOnFrontPowers by lazy {
        (DependentConstants.getIx1(designBallisticCalculation.reducedPropellantFillFactorForFirstStage) /
                (projectParams.initialThrustCapacityOfTheRocket.first * cbrt(
                    sin(
                        designBallisticCalculation.angleInRadians
                    ).pow(2)
                ))) *
                (Constants.GREAT_MID_LOAD / projectParams.initialTransverseLoadOnTheMidsection)
    }

    /**
     * Уравнение скорости в проекции на оси скоростной (поточной) системы координат после интегрирования по весу выгоревшего топлива µк1
     */
    val firstVelocityEqualization by lazy {
        Constants.FREE_FALL_ACCELERATION * determinationOfEngineEfficiencyIndicators.specificGravityInVoidFirst * cValueFirstStage -
                Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.first * determinationOfEngineEfficiencyIndicators.specificGravityOnStartFromEarth *
                DependentConstants.getIg(
                    designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
                    designBallisticCalculation.dependenciesParameters.angle
                ) - determinationOfEngineEfficiencyIndicators.specificGravityOnStartFromEarth * DependentConstants.getIp1(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage
        ) - velocityLessOnFrontPowers
    }

    /**
     * A1
     */

    val A1 by lazy {
        Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.first * determinationOfEngineEfficiencyIndicators.specificGravityOnStartFromEarth.pow(
            2
        )
    }

    /**
     * A2
     */
    val A2 by lazy {
        Constants.FREE_FALL_ACCELERATION * projectParams.initialThrustCapacityOfTheRocket.second * determinationOfEngineEfficiencyIndicators.specificGravityInVoidSecond.pow(
            2
        )
    }

    /**
     * Высота hк1
     */
    val heightHk1 by lazy {
        val F1 = DependentConstants.getF1(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
            designBallisticCalculation.dependenciesParameters.angle
        )
        val Ig = DependentConstants.getIg(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
            designBallisticCalculation.dependenciesParameters.angle
        )
        A1 * (F1 - (0.5 * projectParams.initialThrustCapacityOfTheRocket.first * Ig.pow(2)))
    }

    /**
     * Дальность полёта lk1
     */
    val distanceLk1 by lazy {
        val F2 = DependentConstants.getF2(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
            designBallisticCalculation.dependenciesParameters.angle
        )
        val F4 = DependentConstants.getF4(
            designBallisticCalculation.reducedPropellantFillFactorForFirstStage,
            designBallisticCalculation.dependenciesParameters.angle
        )
        A1 * (F2 - projectParams.initialThrustCapacityOfTheRocket.first * F4)
    }

    // угол в радианах
    /**
     * Vk2
     */
    val secondVelocityEqualization by lazy {
        firstVelocityEqualization + Constants.FREE_FALL_ACCELERATION * determinationOfEngineEfficiencyIndicators.specificGravityInVoidSecond *
                (cValueSecondStage - projectParams.initialThrustCapacityOfTheRocket.second * designBallisticCalculation.reducedPropellantFillFactorForSecondStage *
                        sin(designBallisticCalculation.angleInRadians))
    }

    // угол в радианах
    /**
     * B2
     */
    val B2 by lazy {
        designBallisticCalculation.reducedPropellantFillFactorForSecondStage * (firstVelocityEqualization / (Constants.FREE_FALL_ACCELERATION * determinationOfEngineEfficiencyIndicators.specificGravityInVoidSecond)) +
                cValueSecondWithoutUk1 - 0.5 * projectParams.initialThrustCapacityOfTheRocket.second * designBallisticCalculation.reducedPropellantFillFactorForSecondStage.pow(
            2
        ) *
                sin(designBallisticCalculation.angleInRadians)
    }

    /**
     * Число Циолковского Ц2
     */
    val cValueSecondWithoutUk1 by lazy {
        designBallisticCalculation.reducedPropellantFillFactorForSecondStage + (1 - designBallisticCalculation.reducedPropellantFillFactorForSecondStage) *
                ln(1 - designBallisticCalculation.reducedPropellantFillFactorForSecondStage)
    }

    /**
     * Высота hk2
     */
    val heightHk2 by lazy {
        heightHk1 + A2 * B2 * sin(designBallisticCalculation.angleInRadians)
    }

    /**
     * Дальность lk2
     */
    val distanceLk2 by lazy {
        distanceLk1 + A2 * B2 * cos(designBallisticCalculation.angleInRadians)
    }
}