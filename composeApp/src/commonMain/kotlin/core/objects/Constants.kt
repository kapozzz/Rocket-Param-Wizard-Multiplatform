package core.objects

import kotlin.math.pow

object Constants {
//    /**
//     * Коэффициент потерь скорости
//     */
//    const val VELOCITY_LESS_COEFFICIENT = 1.2

    /**
     * Ускорение свободного падения
     */
    const val FREE_FALL_ACCELERATION = 9.81

    /**
     * Атмосферное давление на уровне Мирового океана в барах
     */
    const val ATMOSPHERIC_PRESSURE_IN_BARS = 1.01

    /**
     * Атмосферное давление на уровне Мирового океана в паскалях
     */
    const val ATMOSPHERIC_PRESSURE_IN_PASCAL = 101325

    /**
     * Кол-во ступеней
     */
    const val STAGES_COUNT = 2.0

    /**
     * Радиус земли
     */
    const val EARTH_RADIUS = 6_371_000.0

    /**
     * Масса земли
     */
    val EARTH_WEIGHT = 5.97 * 10.0.pow(24)

    /**
     * Гравитационная постоянная
     */
    val GRAVITY_CONSTANT = 6.67 * 10.0.pow(-11)

    /**
     * Эталонная начальная поперечная нагрузка на мидель ракеты
     */
    val GREAT_MID_LOAD = 12.0 * 10.0.pow(3)

}



