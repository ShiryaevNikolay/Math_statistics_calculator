package ru.shiryaev.mathstatisticscalculator.data

import java.io.Serializable

data class SampleData(
    val average: Float,
    val variance: Float,
    val fashion: Float,
    val median: Float,
    val size: Float,
    val coefficientVariation: Float,
    val variationRange: List<RowVariationRange>
): Serializable
