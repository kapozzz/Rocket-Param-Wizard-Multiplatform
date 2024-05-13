package core.objects

/**
 * Функция для линейной интерполяции
 */
fun linearInterpolate(
    left: Double,
    right: Double,
    target: Double,
    targetLeft: Double,
    targetRight: Double
): Double {
    val ratio = (target - left) / (right - left)
    return if (targetLeft == targetRight) targetLeft else targetLeft + ratio * (targetRight - targetLeft)
}

// я написал это сам, но честно говоря уже сложно понять как и почему это работает
object DependentConstants {

    fun getIg(uk: Double, ok: Double): Double {
        val okIndex = okList.indexOfFirst { it >= ok }
        val ukIndex = ukListForIg.indexOfFirst { it >= uk }
        return when {
            okList[okIndex] == ok && ukListForIg[ukIndex] == uk -> {
                table[ukIndex][okIndex]
            }
            okList[okIndex] == ok && ukListForIg[ukIndex] > uk -> {
                if (ukIndex == 0) {
                    table[ukIndex][okIndex]
                } else {
                    linearInterpolate(
                        ukListForIg[ukIndex - 1],
                        ukListForIg[ukIndex],
                        uk,
                        table[ukIndex - 1][okIndex],
                        table[ukIndex][okIndex]
                    )
                }
            }
            ukListForIg[ukIndex] == uk && okList[okIndex] > uk -> {
                linearInterpolate(
                    okList[okIndex - 1],
                    okList[okIndex],
                    ok,
                    table[ukIndex][okIndex - 1],
                    table[ukIndex][okIndex]
                )
            }
            ukListForIg[ukIndex] > uk && okList[okIndex] > ok -> {
                val first = linearInterpolate(
                    okList[okIndex - 1],
                    okList[okIndex],
                    ok,
                    table[ukIndex - 1][okIndex - 1],
                    table[ukIndex - 1][okIndex]
                )
                val second = linearInterpolate(
                    okList[okIndex - 1],
                    okList[okIndex],
                    ok,
                    table[ukIndex][okIndex - 1],
                    table[ukIndex][okIndex]
                )
                linearInterpolate(
                    ukListForIg[ukIndex - 1],
                    ukListForIg[ukIndex],
                    uk,
                    first,
                    second
                )
            }
            else -> {
                throw IllegalArgumentException("Wrong Ig input")
            }
        }
    }

    private val ukListForIg = listOf(
        0.1, 0.2, 0.3, 0.4, 0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95
    )

    private val okList = listOf(
        20.0, 25.0, 30.0, 35.0, 40.0, 45.0
    )

    private val table = listOf(
        listOf(0.1, 0.1, 0.1, 0.1, 0.1, 0.1),
        listOf(0.189, 0.191, 0.192, 0.194, 0.195, 0.196),
        listOf(0.26, 0.266, 0.271, 0.275, 0.28, 0.283),
        listOf(0.312, 0.324, 0.335, 0.345, 0.354, 0.372),
        listOf(0.352, 0.371, 0.388, 0.405, 0.422, 0.436),
        listOf(0.369, 0.392, 0.414, 0.436, 0.454, 0.471),
        listOf(0.386, 0.413, 0.438, 0.463, 0.486, 0.506),
        listOf(0.404, 0.434, 0.464, 0.491, 0.518, 0.542),
        listOf(0.421, 0.455, 0.488, 0.520, 0.550, 0.577),
        listOf(0.438, 0.477, 0.513, 0.548, 0.582, 0.612),
        listOf(0.455, 0.498, 0.538, 0.577, 0.614, 0.645),
        listOf(0.472, 0.519, 0.563, 0.606, 0.646, 0.683),
        listOf(0.488, 0.540, 0.588, 0.634, 0.678, 0.718),
        listOf(0.505, 0.561, 0.613, 0.663, 0.710, 0.754),
    )

    fun getIp1(uk1: Double): Double {
        val left = ip1data[if (uk1 <= ip1data.first().first) 0 else ip1data.indexOfFirst { it.first <= uk1 }]
        val right = ip1data[if (uk1 >= ip1data.last().first) ip1data.lastIndex else ip1data.indexOfFirst { it.first >= uk1 }]
        return linearInterpolate(left.first, right.first, uk1, left.second, right.second)
    }

    private val ip1data = listOf(
        0.0 to 0.0,
        0.1 to 0.14,
        0.2 to 0.26,
        0.3 to 0.33,
        0.4 to 0.36,
        0.5 to 0.37,
        0.6 to 0.38,
    )

    fun getIx1(uk1: Double): Double {
        val left = ix1data[ix1data.indexOfFirst { it.first <= uk1 }]
        val right = ix1data[ix1data.indexOfFirst { it.first >= uk1 }]
        return linearInterpolate(left.first, right.first, uk1, left.second, right.second)
    }

    private val ix1data = listOf(
        0.0 to 0.0,
        0.1 to 1.0,
        0.2 to 9.0,
        0.3 to 24.0,
        0.4 to 40.0,
        0.5 to 46.0,
        0.6 to 47.0,
        0.7 to 48.0,
        0.8 to 48.0
    )

    // Поиск F1
    fun getF1(uk: Double, ok: Double): Double {
        // Наибольший градус
        val rightDegrees = degreesListForF1.indexOfFirst { it >= ok }
        // Наименьший градус
        val leftDegrees = degreesListForF1.lastIndex - degreesListForF1.reversed().indexOfFirst { it <= ok }
        // Наибольший uk
        val rightUk = ukListForF1.indexOfFirst { it >= uk }
        // Наименьший uk
        val leftUk = ukListForF1.lastIndex - ukListForF1.reversed().indexOfFirst { it <= uk }
        // Интерполяция значения из "наименьшего графика"
        val first = linearInterpolate(
            left = ukListForF1[leftUk], // х0
            right = ukListForF1[rightUk], // х1
            target = uk, // искомый x
            targetLeft = dataF1[leftDegrees][leftUk].second,
            targetRight = dataF1[leftDegrees][rightUk].second
        )
        // Интерполяция значения из "наибольшего графика"
        val second = linearInterpolate(
            left = ukListForF1[leftUk],
            right = ukListForF1[rightUk],
            target = uk,
            targetLeft = dataF1[rightDegrees][leftUk].second,
            targetRight = dataF1[rightDegrees][rightUk].second
        )
        // Интерполяция между значениями полученными из графиков
        val third = linearInterpolate(
            left = degreesListForF1[leftDegrees],
            right = degreesListForF1[rightDegrees],
            target = ok,
            targetLeft = first,
            targetRight = second
        )
        return third
    }

    private val degreesListForF1 = listOf(10.0, 20.0, 30.0, 40.0, 50.0, 70.0)
    private val ukListForF1 = listOf(0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7)

    private val dataF1 = listOf(
        // 10°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00419,
            0.2 to 0.01857,
            0.3 to 0.0346,
            0.4 to 0.0554,
            0.5 to 0.07619,
            0.6 to 0.10018,
            0.7 to 0.12574,
        ),

        // 20°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00420,
            0.2 to 0.01857,
            0.3 to 0.03936,
            0.4 to 0.06492,
            0.5 to 0.07774,
            0.6 to 0.1305,
            0.7 to 0.17218,
        ),

        // 30°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00420,
            0.2 to 0.02178,
            0.3 to 0.03936,
            0.4 to 0.07619,
            0.5 to 0.11777,
            0.6 to 0.15935,
            0.7 to 0.2089,
        ),

        // 40°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00420,
            0.2 to 0.02178,
            0.3 to 0.04578,
            0.4 to 0.08736,
            0.5 to 0.1305,
            0.6 to 0.18656,
            0.7 to 0.24893,
        ),

        // 50°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00420,
            0.2 to 0.02178,
            0.3 to 0.04578,
            0.4 to 0.09377,
            0.5 to 0.15,
            0.6 to 0.2207,
            0.7 to 0.30334,
        ),

        // 70°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00420,
            0.2 to 0.02178,
            0.3 to 0.05374,
            0.4 to 0.10339,
            0.5 to 0.16732,
            0.6 to 0.23611,
            0.7 to 0.34327,
        )
    )

    fun getF2(uk: Double, ok: Double): Double {
        // Наименьший граудс
        val rightDegrees = degreesListForF2.lastIndex - degreesListForF2.reversed().indexOfFirst { it <= ok }
        // Наибольший градус
        val leftDegrees = degreesListForF2.indexOfFirst { it >= ok }
        val rightUk = ukListForF2.indexOfFirst { it >= uk }
        val leftUk = ukListForF2.lastIndex - ukListForF2.reversed().indexOfFirst { it <= uk }
        // Для наиб
        val first = linearInterpolate(
            left = ukListForF2[leftUk],
            right = ukListForF2[rightUk],
            target = uk,
            targetLeft = dataF2[leftDegrees][leftUk].second,
            targetRight = dataF2[leftDegrees][rightUk].second
        )
        // Для наим
        val second = linearInterpolate(
            left = ukListForF2[leftUk],
            right = ukListForF2[rightUk],
            target = uk,
            targetLeft = dataF2[rightDegrees][leftUk].second,
            targetRight = dataF2[rightDegrees][rightUk].second
        )
        val third = linearInterpolate(
            left = degreesListForF2[rightDegrees],
            right = degreesListForF2[leftDegrees],
            target = ok,
            targetLeft = second,
            targetRight = first
        )
        return third
    }

    private val degreesListForF2 = listOf(10.0, 30.0, 40.0, 50.0, 60.0, 70.0)
    private val ukListForF2 = listOf(0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7)

    private val dataF2 = listOf(
        // 10°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00521,
            0.2 to 0.01646,
            0.3 to 0.04387,
            0.4 to 0.09389,
            0.5 to 0.15996,
            0.6 to 0.24218,
            0.7 to 0.35817,
        ),

        // 30°
        listOf(
            0.0 to 0.0,
            0.1 to 0.003,
            0.2 to 0.01167,
            0.3 to 0.03428,
            0.4 to 0.07617,
            0.5 to 0.13578,
            0.6 to 0.20508,
            0.7 to 0.31461,
        ),

        // 40°
        listOf(
            0.0 to 0.0,
            0.1 to 0.003,
            0.2 to 0.0071,
            0.3 to 0.02782,
            0.4 to 0.06325,
            0.5 to 0.11807,
            0.6 to 0.18247,
            0.7 to 0.27272,
        ),

        // 50°
        listOf(
            0.0 to 0.0,
            0.1 to 0.003,
            0.2 to 0.007,
            0.3 to 0.02292,
            0.4 to 0.05356,
            0.5 to 0.09546,
            0.6 to 0.1535,
            0.7 to 0.23249,
        ),

        // 60°
        listOf(
            0.0 to 0.0,
            0.1 to 0.003,
            0.2 to 0.00687,
            0.3 to 0.01646,
            0.4 to 0.04064,
            0.5 to 0.07617,
            0.6 to 0.12286,
            0.7 to 0.17768,
        ),

        // 70°
        listOf(
            0.0 to 0.0,
            0.1 to 0.003,
            0.2 to 0.00364,
            0.3 to 0.0101,
            0.4 to 0.02782,
            0.5 to 0.05356,
            0.6 to 0.08743,
            0.7 to 0.13412,
        )
    )

    fun getF4(uk: Double, ok: Double): Double {
        // находим меньший градус
        val rightDegrees = degreesListForF4.lastIndex - degreesListForF4.reversed().indexOfFirst { it <= ok }
        // находим больший градус
        val leftDegrees = degreesListForF4.indexOfFirst { it >= ok }
        val rightUk = ukListForF4.indexOfFirst { it >= uk }
        val leftUk = ukListForF4.lastIndex - ukListForF4.reversed().indexOfFirst { it <= uk }
        // интерполяция для большего градуса
        val first = linearInterpolate(
            left = ukListForF4[leftUk],
            right = ukListForF4[rightUk],
            target = uk,
            targetLeft = dataF4[leftDegrees][leftUk].second,
            targetRight = dataF4[leftDegrees][rightUk].second
        )
        // интерполяция для меньшего градуса
        val second = linearInterpolate(
            left = ukListForF4[leftUk],
            right = ukListForF4[rightUk],
            target = uk,
            targetLeft = dataF4[rightDegrees][leftUk].second,
            targetRight = dataF4[rightDegrees][rightUk].second
        )
        if (ok in 10.0 .. 30.0) {
            val result = if (uk <= 0.5) {
                linearInterpolate(
                    left = degreesListForF4[rightDegrees],
                    right = degreesListForF4[leftDegrees],
                    target = ok,
                    targetLeft = first,
                    targetRight = second
                )
            } else {
                linearInterpolate(
                    left = degreesListForF4[rightDegrees],
                    right = degreesListForF4[leftDegrees],
                    target = ok,
                    targetLeft = second,
                    targetRight = first
                )
            }
            return result
        }
        val third = linearInterpolate(
            left = degreesListForF4[rightDegrees],
            right = degreesListForF4[leftDegrees],
            target = ok,
            targetLeft = second,
            targetRight = first
        )
        return third
    }

    private val degreesListForF4 = listOf(10.0, 30.0, 50.0, 70.0)
    private val ukListForF4 = listOf(0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7)

    private val dataF4 = listOf(

        // 10°
        listOf(
            0.0 to 0.0,
            0.1 to 0.00185,
            0.2 to 0.01121,
            0.3 to 0.02997,
            0.4 to 0.0552,
            0.5 to 0.08621,
            0.6 to 0.11935,
            0.7 to 0.1576,
        ),

        // 30°
        listOf(
            0.0 to 0.0,
            0.1 to 0.001,
            0.2 to 0.00831,
            0.3 to 0.02635,
            0.4 to 0.05013,
            0.5 to 0.08653,
            0.6 to 0.12526,
            0.7 to 0.17057,
        ),

        // 50°
        listOf(
            0.0 to 0.0,
            0.1 to 0.001,
            0.2 to 0.00619,
            0.3 to 0.02273,
            0.4 to 0.04439,
            0.5 to 0.06243,
            0.6 to 0.10063,
            0.7 to 0.14028,
        ),

        // 70°
        listOf(
            0.0 to 0.0,
            0.1 to 0.001,
            0.2 to 0.00257,
            0.3 to 0.00831,
            0.4 to 0.0201,
            0.5 to 0.03716,
            0.6 to 0.05592,
            0.7 to 0.08042,
        ),
    )



}