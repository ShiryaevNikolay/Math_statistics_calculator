package ru.shiryaev.mathstatisticscalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.shiryaev.mathstatisticscalculator.R
import ru.shiryaev.mathstatisticscalculator.adapters.VariationsDataAdapter
import ru.shiryaev.mathstatisticscalculator.data.SampleData
import ru.shiryaev.mathstatisticscalculator.databinding.FrSampleResultBinding

class ResultSample : Fragment() {

    private var _binding: FrSampleResultBinding? = null

    private val binding get() = _binding!!

    private var samplesData: SampleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            samplesData = it.getSerializable("SAMPLE_DATA") as SampleData
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrSampleResultBinding.inflate(inflater, container, false)

        with (binding) {
            averageTv.text = getString(R.string.average, samplesData?.average)
            dispersionTv.text = getString(R.string.dispersion, samplesData?.variance)
            fashionTv.text = getString(R.string.fashion, samplesData?.fashion)
            medianTv.text = getString(R.string.median, samplesData?.median)
            variationRv.layoutManager = LinearLayoutManager(requireContext())
            variationRv.adapter = VariationsDataAdapter(samplesData?.variationRange ?: emptyList())
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(samplesData: SampleData) =
            ResultSample().apply {
                arguments = Bundle().apply {
                    putSerializable("SAMPLE_DATA", samplesData)
                }
            }
    }
}