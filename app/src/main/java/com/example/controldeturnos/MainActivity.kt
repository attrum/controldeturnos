package com.example.controldeturnos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.controldeturnos.navigation.AppNavGraph
import com.example.controldeturnos.ui.theme.ControlDeTurnosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlDeTurnosTheme {
                AppNavGraph()
            }
        }
    }
}
