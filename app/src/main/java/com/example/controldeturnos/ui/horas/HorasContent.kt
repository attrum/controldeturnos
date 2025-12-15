package com.example.controldeturnos.ui.horas

import androidx.compose.runtime.Composable
import androidx.compose.material3.*

import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row


@Composable
fun HorasContent(
    horasExtra: Int,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit
) {
    Column {

        Text(
            text = "Horas extra: $horasExtra",
            style = MaterialTheme.typography.headlineMedium
        )

        Row {
            Button(onClick = onDecrementar) {
                Text("-")
            }
            Button(onClick = onIncrementar) {
                Text("+")
            }
        }
    }
}
