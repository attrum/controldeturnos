package com.example.controldeturnos.ui.calendar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalendarViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state

    fun onDayClicked(day: Int) {
        _state.update {
            it.copy(selectedDay = day)
        }
    }

    fun marcarDiaConTurno(day: Int) {
        _state.update {
            it.copy(diasConTurno = it.diasConTurno + day)
        }
    }
}
