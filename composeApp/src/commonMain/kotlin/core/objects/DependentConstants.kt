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
    require(left <= right) { "left must be less than or equal to right" }
    require(targetLeft <= targetRight) { "targetLeft must be less than or equal to targetRight" }

    val ratio = (target - left) / (right - left)
    return targetLeft + ratio * (targetRight - targetLeft)
}

/**
 * Значения из графиков и таблиц
 */
object DependentConstants {

    fun getIg(uk: Double, ok: Double): Double {
        val okIndex = okList.indexOfFirst { it >= ok }
        val ukIndex = ukList.indexOfFirst { it >= uk }
        return when {
            okList[okIndex] == ok && ukList[ukIndex] == uk -> {
                table[ukIndex][okIndex]
            }

            okList[okIndex] == ok && ukList[ukIndex] > uk -> {
                linearInterpolate(
                    ukList[ukIndex - 1],
                    ukList[ukIndex],
                    uk,
                    table[ukIndex - 1][okIndex],
                    table[ukIndex][okIndex]
                )
            }

            ukList[ukIndex] == uk && okList[okIndex] > uk -> {
                linearInterpolate(
                    okList[okIndex - 1],
                    okList[okIndex],
                    ok,
                    table[ukIndex][okIndex - 1],
                    table[ukIndex][okIndex]
                )
            }

            ukList[ukIndex] > uk && okList[okIndex] > ok -> {
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
                    ukList[ukIndex - 1],
                    ukList[ukIndex],
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

    private val ukList = listOf(
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
        val (x0, y0) = ip1data.first()
        val (x1, y1) = ip1data.last()
        return linearInterpolate(x0, x1, uk1, y0, y1)
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
        val (x0, y0) = ix1data.first()
        val (x1, y1) = ix1data.last()
        return linearInterpolate(x0, x1, uk1, y0, y1)
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


}