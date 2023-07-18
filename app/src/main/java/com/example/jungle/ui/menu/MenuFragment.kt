package com.example.jungle.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jungle.databinding.FragmentMenuBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.util.Navigation

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.btnPlay.setOnClickListener {
            navigationViewModel.loadState(Navigation.RULES)
        }
        binding.btnSettings.setOnClickListener {
            navigationViewModel.loadState(Navigation.SETTINGS)
        }
        binding.btnStatistics.setOnClickListener {
            navigationViewModel.loadState(Navigation.STATISTICS)
        }
        return binding.root
    }
}