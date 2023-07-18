package com.example.jungle.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.jungle.databinding.FragmentStatisticsBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.util.Navigation
import kotlinx.coroutines.launch

class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }
    private val statisticsViewModel: StatisticsViewModel by activityViewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private lateinit var binding: FragmentStatisticsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        binding.btnBackFromStatistics.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        lifecycleScope.launch {
            statisticsViewModel.stateStatistics.collect {
                binding.tvYouScore.text = it.toString()
            }
        }
        return binding.root
    }


}