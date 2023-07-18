package com.example.jungle.ui.lose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.jungle.databinding.FragmentLoseBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.ui.statistics.StatisticsViewModel
import com.example.jungle.util.Navigation

class LoseFragment : Fragment() {

    companion object {
        fun newInstance() = LoseFragment()
    }
    private lateinit var binding: FragmentLoseBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private lateinit var viewModel: LoseViewModel
    private val statisticsViewModel: StatisticsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoseBinding.inflate(inflater, container, false)
        binding.btnTryAgain.setOnClickListener {
            navigationViewModel.loadState(Navigation.GAME)
        }
        statisticsViewModel.loadState(0)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}