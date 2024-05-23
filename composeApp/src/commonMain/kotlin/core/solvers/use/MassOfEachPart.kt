package core.solvers.use

import core.models.Fuel
import core.objects.Constants
import core.objects.tables.Tables
import core.solvers.Design

class MassOfEachPart(
    val m0: Double,
    val payloadWeight: Double,
    val definedDesign: Design,
    val initialThrustCapacityOfTheRocket: Double,
    val reducedPropellantFillFactor: Double,
    val fuel: Fuel
) {

    /**
     * Масса ракетного блока второй ступени
     */
    val massOfRocketBlock by lazy {
        m0 - payloadWeight
    }

    /**
     * Тяга двигателей в пустоте
     */
    val specificGravityInVoid by lazy {
        m0 * Constants.FREE_FALL_ACCELERATION / initialThrustCapacityOfTheRocket
    }

    /**
     * Рабочий запас топлива
     */
    val omega by lazy {
        m0 * reducedPropellantFillFactor
    }

    // тут может быть ошибка
    /**
     * Коэффициент, характеризующий степень совершенства системы питания двигателя
     */
    val alphaOmega by lazy {
        Tables.getAlpha(omega)
    }

    /**
     * Неиспользованный запас топлива
     */
    val deltaOmega by lazy {
        omega * alphaOmega
    }

    /**
     * Масса заправки топливом второй ступени
     */
    val omegaGasProcess by lazy {
        omega + deltaOmega
    }

    /**
     * Масса окислителя ступени
     */
    val omegaOxidant by lazy {
        omegaGasProcess * fuel.K / (1 + fuel.K)
    }

    /**
     * Масса горючего ступени
     */
    val omegaFuel by lazy {
        omegaGasProcess - omegaOxidant
    }

    /**
     * Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива
     */
    val alphaTo by lazy {
        middleDensity / fuel.middleLiquidFuelDensity
    }

    /**
     * Средняя плотность конструкции топливного отсека
     */
    val middleDensity by lazy {
        Tables.getMiddleDensity(omega)
    }

    /**
     * Масса топливного отсека второй ступени
     */
    val massOfFuelPart by lazy {
        omegaGasProcess * alphaTo
    }

    /**
     * Конструктивный коэффициент N
     */
    val Ni by lazy {
        Tables.getNi(m0)
    }

    /**
     * Коэффициент «удельного веса» двигателей
     */
    val bi by lazy {
        Tables.getBi(specificGravityInVoid)
    }

    /**
     * Масса двигательной установки
     */
    val massOfEngine by lazy {
        (bi / initialThrustCapacityOfTheRocket) * m0
    }

    /**
     * Масса хвостового и приборного отсеков второй ступени
     */
    val massOfDevicesAndTailPart by lazy {
        Ni * m0
    }

    /**
     * Масса сухой конструкции ступени
     */
    val massOfDryConstruction by lazy {
        m0 - omegaGasProcess - payloadWeight
    }

    val definedN by lazy {
        Ni + bi / initialThrustCapacityOfTheRocket
    }

    val definedK by lazy {
        alphaOmega + alphaTo
    }
}