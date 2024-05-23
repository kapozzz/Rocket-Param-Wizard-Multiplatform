package core.solvers

import core.models.Fuel
import core.objects.Constants
import core.objects.Fuels
import core.objects.tables.Tables
import kotlin.math.abs
import kotlin.math.pow

data class MassAnalyze(
    val mass: Double,
    private val thrustCapacityInVoid: Double,
    private val fillFactor: Double,
    private val fuel: Fuel,
    private val previousMass: Double
) {

    /**
     * Конструктивный коэффициент
     */
    val N by lazy {
        Tables.getNi(mass)
    }

    /**
     * Коэффициент «удельного веса» двигателей
     */
    val b by lazy {
        Tables.getBi(specificGravity)
    }

    /**
     * Коэффициент, характеризующий степень совершенства системы питания двигателя
     */
    val alphaFromTable by lazy {
        Tables.getAlpha(omega)
    }

    /**
     * Коэффициент, характеризующий отношение средней плотности конструкции топливного отсека к средней плотности топлива
     */
    val alpha by lazy {
        density / (((1 + fuel.K)*fuel.oxidantDensity*fuel.fuelDensity)/(fuel.oxidantDensity+(fuel.K*fuel.fuelDensity)))
    }

    /**
     * Средняя плотность конструкции топливного отсека
     */
    val density by lazy {
        Tables.getMiddleDensity(omega)
    }

    /**
     * Рабочий запас топлива
     */
    val omega by lazy {
        mass * fillFactor
    }

    /**
     * Тяга двигателей ступеней в пустоте
     */
    val specificGravity by lazy {
        (mass * Constants.FREE_FALL_ACCELERATION) / thrustCapacityInVoid
    }

    /**
     * Введенный коэффициент
     */
    val definedK by lazy {
        alpha + alphaFromTable
    }

    /**
     * Введенный коэффициент
     */
    val definedN by lazy {
        N + (b / thrustCapacityInVoid)
    }

    /**
     * Расчетное значение массы
     */
    val calculatedMass by lazy {
        previousMass/(1 - definedN - fillFactor*(1 + definedK))
    }

    /**
     * Разница между заданным значением массы и получаемым расчетным путем
     */
    val different by lazy {
       (mass - calculatedMass)
    }

    companion object {
        fun getDefault(): MassAnalyze {
            return MassAnalyze(
                mass = 0.0,
                thrustCapacityInVoid = 0.0,
                fillFactor = 0.0,
                previousMass = 0.0,
                fuel = Fuels.Dimethylhydrazine,
            )
        }
    }
}