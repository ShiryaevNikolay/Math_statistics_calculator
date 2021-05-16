package ru.shiryaev.mathstatisticscalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.shiryaev.mathstatisticscalculator.databinding.FrTestingStatisticalHypothesesBinding

class TestingStatisticalHypotheses : Fragment() {

    private var _binding: FrTestingStatisticalHypothesesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrTestingStatisticalHypothesesBinding.inflate(inflater, container, false)
        return binding.root
    }
}