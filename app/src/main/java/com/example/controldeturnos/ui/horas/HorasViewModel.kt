package com.example.controldeturnos.ui.horas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HorasViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val day: Int = checkNotNull(savedStateHandle["day"])

    private val _state = MutableStateFlow(
        HorasState(day = day)
    )
    val state: StateFlow<HorasState> = _state

    fun incrementarHoras() {
        _state.update {
            it.copy(horasExtra = it.horasExtra + 1)
        }
    }

    fun decrementarHoras() {
        _state.update {
            it.copy(horasExtra = (it.horasExtra - 1).coerceAtLeast(0))
        }
    }

    fun setHoras(value: Int) {
        _state.update {
            it.copy(horasExtra = value.coerceAtLeast(0))
        }
    }
}
