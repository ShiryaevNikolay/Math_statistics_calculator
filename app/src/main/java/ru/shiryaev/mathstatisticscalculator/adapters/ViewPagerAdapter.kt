package ru.shiryaev.mathstatisticscalculator.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.shiryaev.mathstatisticscalculator.fragments.DescriptiveStatisticsFragment
import ru.shiryaev.mathstatisticscalculator.fragments.PointEstimateFragment
import ru.shiryaev.mathstatisticscalculator.fragments.TestingStatisticalHypotheses

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val pages = listOf(
        DescriptiveStatisticsFragment(),
        PointEstimateFragment(),
        TestingStatisticalHypotheses()
    )

    override fun createFragment(position: Int) = pages[position]

    override fun getItemCount() = pages.size
}