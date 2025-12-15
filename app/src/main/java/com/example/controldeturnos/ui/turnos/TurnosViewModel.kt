package com.example.controldeturnos.ui.turnos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TurnosViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val day: Int = checkNotNull(savedStateHandle["day"])

    private val _state = MutableStateFlow(
        TurnosState(day = day)
    )
    val state: StateFlow<TurnosState> = _state

    fun onTurnoSelected(turno: Turno) {
        _state.update {
            it.copy(selectedTurno = turno)
        }
    }
}
