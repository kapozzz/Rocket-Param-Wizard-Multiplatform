package common.core.base.objects

import common.core.base.models.Fuel
import common.core.base.models.Oxidant

object Fuels {

    val Paraffin = Fuel(
        name = "Керосин",
        K = 2.9,
        oxidant = Oxidants.LiquidOxygen,
        oxidantDensity = 1142.0,
        fuelDensity = 800.0,
        burningPoint = 3550.0,
        gasConstant = 378.0,
        adiabaticValue = 1.21,
        standardSpecificGravity = 280.0
    )

    val LiquidHydrogen = Fuel(
        name = "Жидкий водород",
        K = 4.76,
        oxidant = Oxidants.LiquidOxygen,
        oxidantDensity = 1142.0,
        fuelDensity = 71.0,
        burningPoint = 3270.0,
        gasConstant = 520.0,
        adiabaticValue = 1.22,
        standardSpecificGravity = 335.0
    )

    val Dimethylhydrazine = Fuel(
        name = "НДМГ",
        K = 2.8,
        oxidant = Oxidants.NitrousOxide,
        oxidantDensity = 1450.0,
        fuelDensity = 808.0,
        burningPoint = 3360.0,
        gasConstant = 380.0,
        adiabaticValue = 2.8,
        standardSpecificGravity = 268.0
    )


}

object Oxidants {

    val LiquidOxygen = Oxidant(
        name = "Жидкий кислород"
    )

    val NitrousOxide = Oxidant(
        name = "Четырехокись азота"
    )

}

