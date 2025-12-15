package com.example.controldeturnos.ui.horas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorasScreen(
    day: Int,
    onHorasSelected: (Int) -> Unit
) {

    var horas by remember { mutableStateOf(0) }

    val minHoras = -7
    val maxHoras = 17

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Horas extra – Día $day") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Selecciona horas",
                style = MaterialTheme.typography.titleMedium
            )

            // Valor actual
            Text(
                text = horas.toString(),
                style = MaterialTheme.typography.displaySmall
            )

            // Controles
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        if (horas > minHoras) horas--
                    },
                    enabled = horas > minHoras
                ) {
                    Text("-")
                }

                Button(
                    onClick = {
                        if (horas < maxHoras) horas++
                    },
                    enabled = horas < maxHoras
                ) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Confirmar
            Button(
                onClick = { onHorasSelected(horas) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar")
            }
        }
    }
}
