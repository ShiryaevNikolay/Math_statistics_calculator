package ru.shiryaev.mathstatisticscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import ru.shiryaev.mathstatisticscalculator.adapters.ViewPagerAdapter
import ru.shiryaev.mathstatisticscalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mVpAdapter: ViewPagerAdapter

    private val titles = listOf(
        "Описательная статистика",
        "Точечная оценка",
        "Проверка гипотез"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mVpAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = mVpAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}