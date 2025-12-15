package com.example.controldeturnos.ui.horas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.controldeturnos.ui.shared.TurnosSharedViewModel

@Composable
fun HorasScreen(
    day: Int,
    sharedViewModel: TurnosSharedViewModel,
    onDone: () -> Unit
) {
    val turnosPorDia by sharedViewModel
        .turnosPorDia
        .collectAsStateWithLifecycle()

    val diaTurno = turnosPorDia[day] ?: return

    HorasContent(
        horasExtra = diaTurno.horasExtra,
        onIncrementar = {
            sharedViewModel.setHoras(day, diaTurno.horasExtra + 1)
        },
        onDecrementar = {
            sharedViewModel.setHoras(
                day,
                (diaTurno.horasExtra - 1).coerceAtLeast(0)
            )
        }
    )
}
