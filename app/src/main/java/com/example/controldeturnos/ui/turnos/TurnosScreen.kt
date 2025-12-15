package com.example.controldeturnos.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnosScreen(
    day: Int,
    onTurnoSelected: (Turno) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Turnos – Día $day") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Selecciona un turno",
                style = MaterialTheme.typography.titleMedium
            )

            Turno.values().forEach { turno ->
                ElevatedButton(
                    onClick = { onTurnoSelected(turno) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(turno.abreviatura)
                }
            }
        }
    }
}
