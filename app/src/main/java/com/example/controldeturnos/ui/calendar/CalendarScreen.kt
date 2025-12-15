package com.example.controldeturnos.ui.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.controldeturnos.ui.shared.TurnosSharedViewModel


@Composable
fun CalendarScreen(
    sharedViewModel: TurnosSharedViewModel,
    onDayClick: (Int) -> Unit,
    onDayLongClick: (Int) -> Unit
) {
    val turnosPorDia by sharedViewModel
        .turnosPorDia
        .collectAsStateWithLifecycle()

    CalendarContent(
        turnosPorDia = turnosPorDia,
        onDayClick = onDayClick,
        onDayLongClick = onDayLongClick
    )
}
