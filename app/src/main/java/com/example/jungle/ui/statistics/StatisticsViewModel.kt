package com.example.jungle.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor() : ViewModel() {
    private val _stateStatistics = MutableStateFlow<Int>(0)
    val stateStatistics : StateFlow<Int> = _stateStatistics

    fun loadState(statistics: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateStatistics.emit(statistics)
        }
    }
}