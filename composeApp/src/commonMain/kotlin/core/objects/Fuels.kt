package core.objects

import core.models.Fuel

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
        adiabaticValue = 1.23,
        standardSpecificGravity = 268.0
    )
}



