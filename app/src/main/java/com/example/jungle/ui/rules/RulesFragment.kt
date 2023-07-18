package com.example.jungle.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jungle.databinding.FragmentRulesBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.util.Navigation

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        binding.btnBackToMenu.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        binding.btnPlayRules.setOnClickListener {
            navigationViewModel.loadState(Navigation.GAME)
        }
        return binding.root
    }
}