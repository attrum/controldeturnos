package com.example.controldeturnos.ui.calendar

import java.time.LocalDate

data class CalendarState(
    val selectedDay: Int? = null,
    val diasConTurno: Set<Int> = emptySet()
)
