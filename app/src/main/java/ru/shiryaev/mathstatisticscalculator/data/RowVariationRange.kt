package ru.shiryaev.mathstatisticscalculator.data

import java.io.Serializable

data class RowVariationRange(
    val interval: Interval,
    val frequency: Int,
    val relativeFrequency: Float,
    val accumulatedFrequency: Float
): Serializable {
    data class Interval(val min: Int, val max: Int): Serializable
}
