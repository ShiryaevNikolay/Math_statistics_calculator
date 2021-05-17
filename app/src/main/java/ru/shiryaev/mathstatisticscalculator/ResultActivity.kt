package ru.shiryaev.mathstatisticscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.shiryaev.mathstatisticscalculator.adapters.ResultSampleViewPagerAdapter
import ru.shiryaev.mathstatisticscalculator.data.SampleData
import ru.shiryaev.mathstatisticscalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private var sampleData: SampleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        sampleData = intent.getSerializableExtra("SAMPLE_DATA") as SampleData

        val viewPagerAdapter = ResultSampleViewPagerAdapter(
            sampleData!!,
            supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = viewPagerAdapter
    }
}