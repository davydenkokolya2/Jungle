package com.example.jungle.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _stateSound = MutableStateFlow<String>("on")
    val stateSound: StateFlow<String?> = _stateSound

    fun loadStateSound(sound: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSound.emit(sound)
        }
    }

    private val _stateMusic = MutableStateFlow<String>("on")
    val stateMusic: StateFlow<String?> = _stateMusic

    fun loadStateMusic(music: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMusic.emit(music)
        }
    }
    private val _stateScore = MutableStateFlow<Int>(0)
    val stateScore: StateFlow<Int?> = _stateScore

    fun loadState(score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateScore.emit(score)
        }
    }

}