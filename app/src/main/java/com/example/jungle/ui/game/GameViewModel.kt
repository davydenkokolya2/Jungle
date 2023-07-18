package com.example.jungle.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _stateGame = MutableStateFlow<Int>(0)
    val stateGame: StateFlow<Int> = _stateGame

    fun loadState(game: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateGame.emit(game)
        }
    }
}