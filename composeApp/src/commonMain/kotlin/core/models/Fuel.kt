package core.models

/**
Топливо:

K - коэффициент соотношения окислителя к горючему - [Безразмерное]

oxidant - окислитель.

oxidantDensity - плотность окислителя - [кг/м^3].

fuelDensity - плотность горючего - [кг/м^3].

burningPoint - температура горения - [К].

gasConstant - газовая постоянная - [Дж/кг∙°С].

adiabaticValue - показатель адиабаты - [Безразмерное].

standardSpecificGravity - стандартная удельная тяга - [c].

 */

data class Fuel(
    val name: String,
    val K: Double,
    val oxidant: Oxidant,
    val oxidantDensity: Double,
    val fuelDensity: Double,
    val burningPoint: Double,
    val gasConstant: Double,
    val adiabaticValue: Double,
    val standardSpecificGravity: Double
) {
    /**
     * Средняя плотность жидкого топлива
     */
    val middleLiquidFuelDensity: Double
        get() = ((1.0 + K) * oxidantDensity * fuelDensity) /
                (oxidantDensity + (K * fuelDensity))
}


