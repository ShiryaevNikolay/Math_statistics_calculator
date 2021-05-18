package ru.shiryaev.mathstatisticscalculator

import ru.shiryaev.mathstatisticscalculator.data.RowVariationRange
import ru.shiryaev.mathstatisticscalculator.data.SampleData
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

class CalculateData(
    samples: List<Int>,
    customStep: Boolean,
    startInterval: Int,
    stepIntervalInput: Int
) {
    private val sizeSamples: Int = samples.size

    val sampleData: SampleData

    init {
        val countIntervals = (1 + 3.322 * log10(samples.size.toDouble())).toInt()
        val stepInterval = if(customStep) stepIntervalInput else (samples.maxOrNull()!! - samples.minOrNull()!!) / countIntervals

        var a = startInterval
        var b = a + stepInterval

        val listVariationRange: MutableList<RowVariationRange> = mutableListOf()

        var xAverage = 0f  // Среднее выборочное
        var accumFrequency = 0f  // Накопленная частность

        while (a <= samples.maxOrNull()!!) {
            val interval = RowVariationRange.Interval(a, b)

            var frequency = 0
            samples.forEach { value ->
                if(value >= interval.min && value < interval.max) frequency++
            }
            val relativeFrequency: Float = frequency.toFloat() / sizeSamples.toFloat()
            accumFrequency += relativeFrequency

            val xi: Float = if(stepInterval != 1) {
                (interval.max - interval.min).toFloat() / 2
            } else interval.min.toFloat()

            xAverage += xi * relativeFrequency

            // Меняем интервалы
            a = b
            b += stepInterval

            listVariationRange.add(RowVariationRange(interval, frequency, relativeFrequency, accumFrequency))
        }

        var dispersion = 0f
        var fashion = 0f
        var median = 0f
        // Найдем дисперсию, моду и медиану
        listVariationRange.forEachIndexed { _, rowVariationRange ->
            val midpointInterval: Float = if(stepInterval != 1) {
                (rowVariationRange.interval.max + rowVariationRange.interval.min - 1).toFloat() / 2
            } else rowVariationRange.interval.min.toFloat()
            dispersion += (midpointInterval - xAverage).pow(2) * rowVariationRange.relativeFrequency
        }

        // Получаем максимальную частоту вариационного ряда
        val maxFrequency = listVariationRange.maxByOrNull { it.frequency }
        if(stepInterval > 1) {
            val indexHighestFrequency = listVariationRange.indexOfFirst { it.accumulatedFrequency > 0.5 }
            val maxIntervalFrequency = listVariationRange.indexOfFirst { it == maxFrequency }
            fashion = calculateFashionInterval(listVariationRange, maxIntervalFrequency, stepInterval)
            median = calculateMedianInterval(listVariationRange, indexHighestFrequency, stepInterval)
        } else {
            fashion = if(maxFrequency != null) calculateFashion(maxFrequency) else 0f
            median = calculateMedian(listVariationRange.size, listVariationRange)
        }

        val coefficientVariation: Float = sqrt(dispersion) / xAverage * 100
        val size = (samples.maxOrNull()!! - samples.minOrNull()!! + 1).toFloat()

        sampleData = SampleData(
            xAverage,
            dispersion,
            fashion,
            median,
            size,
            coefficientVariation,
            listVariationRange
        )
    }

    private fun calculateFashionInterval(listVariationRange: List<RowVariationRange>, maxIntervalFrequency: Int, stepInterval: Int) =
        listVariationRange[maxIntervalFrequency].interval.min + stepInterval.toFloat() *
                (listVariationRange[maxIntervalFrequency].frequency - listVariationRange[maxIntervalFrequency - 1].frequency) /
                ((listVariationRange[maxIntervalFrequency].frequency - listVariationRange[maxIntervalFrequency - 1].frequency) *
                        (listVariationRange[maxIntervalFrequency].frequency - listVariationRange[maxIntervalFrequency + 1].frequency))

    private fun calculateMedianInterval(listVariationRange: List<RowVariationRange>, indexHighestFrequency: Int, stepInterval: Int): Float {
        var sumFrequency = 0
        listVariationRange.forEachIndexed { index, rowVariationRange ->
            if (index < indexHighestFrequency) {
                sumFrequency += rowVariationRange.frequency
            }
        }
        return listVariationRange[indexHighestFrequency].interval.min + stepInterval *
                ((0.5 * sizeSamples - sumFrequency) / listVariationRange[indexHighestFrequency].frequency).toFloat()
    }


    private fun calculateFashion(maxFrequency: RowVariationRange) = maxFrequency.interval.min.toFloat()

    private fun calculateMedian(countInterval: Int, listVariationRange: List<RowVariationRange>) = if(countInterval % 2 != 0) {
        listVariationRange[(countInterval / 2) + 1].interval.min.toFloat()
    } else {
        (listVariationRange[(countInterval / 2) + 1].interval.min.toFloat() + listVariationRange[(countInterval / 2)].interval.min.toFloat()) / 2
    }
}