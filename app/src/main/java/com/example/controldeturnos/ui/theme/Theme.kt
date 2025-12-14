package com.example.controldeturnos.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ControlDeTurnosTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}
