package ru.shiryaev.mathstatisticscalculator.data

import java.io.Serializable

data class SampleData(
    val average: Float,
    val variance: Float,
    val median: Float,
    val fashion: Float,
    val variationRange: List<RowVariationRange>
): Serializable
