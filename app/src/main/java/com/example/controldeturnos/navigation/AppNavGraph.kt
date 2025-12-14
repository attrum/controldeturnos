package com.example.controldeturnos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.controldeturnos.ui.calendar.CalendarScreen
import com.example.controldeturnos.ui.horas.HorasScreen
import com.example.controldeturnos.ui.turnos.TurnosScreen

sealed class Screen(val route: String) {
    object Calendar : Screen("calendar")
    object Turnos : Screen("turnos/{day}") {
        fun create(day: Int) = "turnos/$day"
    }
    object Horas : Screen("horas/{day}") {
        fun create(day: Int) = "horas/$day"
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Calendar.route
    ) {
        composable(Screen.Calendar.route) {
            CalendarScreen(
                onDayClick = { day ->
                    navController.navigate(Screen.Turnos.create(day))
                },
                onDayLongClick = { day ->
                    navController.navigate(Screen.Horas.create(day))
                }
            )
        }

        composable(Screen.Turnos.route) {
            TurnosScreen()
        }

        composable(Screen.Horas.route) {
            HorasScreen()
        }
    }
}
