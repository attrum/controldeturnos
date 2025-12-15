package com.example.controldeturnos.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controldeturnos.ui.turnos.Turno
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    turnosPorDia: Map<Int, DiaTurno>,
    onDayClick: (Int) -> Unit,
    onDayLongClick: (Int) -> Unit
) {
    val yearMonth = YearMonth.now()
    val days = (1..yearMonth.lengthOfMonth()).toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "${yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${yearMonth.year}"
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            // 📅 El calendario ocupa el 70% de la pantalla
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(days) { day ->
                        val diaTurno = turnosPorDia[day]

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .aspectRatio(1f)
                                .background(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = MaterialTheme.shapes.small
                                )
                                .combinedClickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = LocalIndication.current,
                                    onClick = { onDayClick(day) },
                                    onLongClick = { onDayLongClick(day) }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            // Día del mes
                            Text(
                                text = day.toString(),
                                style = MaterialTheme.typography.titleMedium
                            )

                            // Turno + horas (si existen)
                            if (diaTurno != null) {
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = formatTurno(diaTurno),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/* ---------------------------------------------------
   Formateo de turno y horas
   --------------------------------------------------- */

private fun formatTurno(diaTurno: DiaTurno): String {
    val turno = diaTurno.turno
    val horas = diaTurno.horasExtra

    return when (turno) {
        Turno.LIBRE,
        Turno.VACACIONES -> turno.abreviatura

        else -> {
            when {
                horas == null || horas == 0 ->
                    turno.abreviatura

                horas > 0 ->
                    "${turno.abreviatura} +$horas"

                else ->
                    "$horas ${turno.abreviatura}"
            }
        }
    }
}
