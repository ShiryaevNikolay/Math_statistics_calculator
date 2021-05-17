package ru.shiryaev.mathstatisticscalculator.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.shiryaev.mathstatisticscalculator.CalculateData
import ru.shiryaev.mathstatisticscalculator.ResultActivity
import ru.shiryaev.mathstatisticscalculator.data.SampleData
import ru.shiryaev.mathstatisticscalculator.databinding.FrDescriptiveStatisticsBinding

class DescriptiveStatisticsFragment : Fragment() {

    private var _binding: FrDescriptiveStatisticsBinding? = null
    private val binding get() = _binding!!
    private var textInput: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrDescriptiveStatisticsBinding.inflate(inflater, container, false)

        binding.stepIntervalCv.minCounterValue = 1

        binding.sampleInputTf.onTextChanged = { text ->
            textInput = text
        }

        binding.calculateBtn.setOnClickListener {
            val samples: List<Int> = if (textInput.isNotEmpty()) {
                textInput.split(' ').map { it.toInt() }
            } else emptyList()

            if (samples.isNotEmpty()) {
                val startInterval = samples.minOrNull() ?: 0
                val stepIntervalInput = binding.stepIntervalCv.counter
                val data = CalculateData(samples, binding.customStepCb.isChecked, startInterval, stepIntervalInput)
                navigateToResultActivity(data.sampleData)
            } else {
                showSnackBar()
            }
        }

        return binding.root
    }

    private fun navigateToResultActivity(
        samplesData: SampleData
    ) {
        val intentToResultActivity = Intent(requireActivity(), ResultActivity::class.java).apply {
            putExtra("SAMPLE_DATA", samplesData)
        }
        startActivity(intentToResultActivity)
    }

    private fun showSnackBar() {
        Snackbar.make(
            binding.root,
            "Выборка не удовлетворяет условию",
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.WHITE).show()
    }
}