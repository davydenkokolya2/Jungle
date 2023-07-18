package com.example.jungle.ui.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.jungle.databinding.FragmentWinBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.ui.game.GameViewModel
import com.example.jungle.ui.statistics.StatisticsViewModel
import com.example.jungle.util.Navigation
import kotlinx.coroutines.launch

class WinFragment : Fragment() {

    companion object {
        fun newInstance() = WinFragment()
    }
    private val statisticsViewModel: StatisticsViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentWinBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private lateinit var viewModel: WinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWinBinding.inflate(inflater, container, false)
        binding.btnWinTryAgain.setOnClickListener {
            gameViewModel.loadState(0)
            navigationViewModel.loadState(Navigation.GAME)
        }
        lifecycleScope.launch {
            statisticsViewModel.stateStatistics.collect{
                binding.tvScore.text = it.toString()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WinViewModel::class.java)
        // TODO: Use the ViewModel
    }

}