package ru.shiryaev.mathstatisticscalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.shiryaev.mathstatisticscalculator.databinding.FrPointEstimateOfDistributionParametersBinding

class PointEstimateFragment : Fragment() {

    private var _binding: FrPointEstimateOfDistributionParametersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrPointEstimateOfDistributionParametersBinding.inflate(inflater, container, false)
        return binding.root
    }
}