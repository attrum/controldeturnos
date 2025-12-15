package com.example.controldeturnos.ui.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.controldeturnos.ui.calendar.DiaTurno
import com.example.controldeturnos.ui.turnos.Turno

class TurnosSharedViewModel : ViewModel() {

    private val _turnosPorDia =
        MutableStateFlow<Map<Int, DiaTurno>>(emptyMap())

    val turnosPorDia: StateFlow<Map<Int, DiaTurno>> = _turnosPorDia

    fun setTurno(day: Int, turno: Turno) {
        _turnosPorDia.update { current ->
            current + (day to DiaTurno(turno = turno))
        }
    }

    fun setHoras(day: Int, horas: Int) {
        _turnosPorDia.update { current ->
            val actual = current[day] ?: return@update current
            current + (day to actual.copy(horasExtra = horas))
        }
    }
}
