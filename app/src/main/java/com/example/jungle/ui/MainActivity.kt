package com.example.jungle.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.jungle.R
import com.example.jungle.databinding.ActivityMainBinding
import com.example.jungle.ui.game.GameFragment
import com.example.jungle.ui.lose.LoseFragment
import com.example.jungle.ui.menu.MenuFragment
import com.example.jungle.ui.rules.RulesFragment
import com.example.jungle.ui.settings.SettingsFragment
import com.example.jungle.ui.settings.SettingsViewModel
import com.example.jungle.ui.statistics.StatisticsFragment
import com.example.jungle.ui.win.WinFragment
import com.example.jungle.util.Navigation
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigationViewModel: NavigationViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true

        lifecycleScope.launch {
            navigationViewModel.stateNavigation.collect {
                when(it) {
                    Navigation.MENU -> replaceFragment(MenuFragment())
                    Navigation.LOSE -> replaceFragment(LoseFragment())
                    Navigation.GAME -> replaceFragment(GameFragment())
                    Navigation.RULES -> replaceFragment(RulesFragment())
                    Navigation.SETTINGS -> replaceFragment(SettingsFragment())
                    Navigation.STATISTICS -> replaceFragment(StatisticsFragment())
                    Navigation.WIN -> replaceFragment(WinFragment())

                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            settingsViewModel.stateMusic.collect {
                when (it) {
                    "on" -> {
                        if (settingsViewModel.stateSound.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }
        lifecycleScope.launch {
            settingsViewModel.stateSound.collect {
                when (it) {
                    "on" -> {
                        if (settingsViewModel.stateMusic.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}