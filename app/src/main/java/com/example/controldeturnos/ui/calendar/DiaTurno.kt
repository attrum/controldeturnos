package com.example.controldeturnos.ui.calendar

import com.example.controldeturnos.ui.turnos.Turno

data class DiaTurno(
    val turno: Turno,
    val horasExtra: Int? = null
)
