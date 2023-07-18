package com.example.jungle.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.jungle.databinding.FragmentSettingsBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.util.Navigation

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        binding.switchMusic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                settingsViewModel.loadStateMusic("on")
            else
                settingsViewModel.loadStateMusic("off")
        }
        binding.switchSound.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                settingsViewModel.loadStateSound("on")
            else
                settingsViewModel.loadStateSound("off")
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}