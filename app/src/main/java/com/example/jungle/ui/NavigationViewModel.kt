package com.example.jungle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jungle.util.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _stateNavigation = MutableStateFlow<Navigation?>(Navigation.MENU)
    val stateNavigation: StateFlow<Navigation?> = _stateNavigation

    fun loadState(navigation: Navigation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateNavigation.emit(navigation)
        }
    }
}