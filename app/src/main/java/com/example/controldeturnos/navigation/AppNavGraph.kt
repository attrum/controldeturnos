package com.example.controldeturnos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.controldeturnos.ui.calendar.CalendarScreen
import com.example.controldeturnos.ui.calendar.DiaTurno
import com.example.controldeturnos.ui.horas.HorasScreen
import com.example.controldeturnos.ui.turnos.Turno
import com.example.controldeturnos.ui.turnos.TurnosScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    // ✅ TIPO CORRECTO
    val turnosPorDia = remember {
        mutableStateMapOf<Int, DiaTurno>()
    }

    NavHost(
        navController = navController,
        startDestination = "calendar"
    ) {

        composable("calendar") {
            CalendarScreen(
                turnosPorDia = turnosPorDia,
                onDayClick = { day ->
                    navController.navigate("turnos/$day")
                },
                onDayLongClick = { day ->
                    navController.navigate("horas/$day")
                }
            )
        }

        composable(
            route = "turnos/{day}",
            arguments = listOf(navArgument("day") { type = NavType.IntType })
        ) { backStack ->
            val day = backStack.arguments!!.getInt("day")

            TurnosScreen(day) { turno ->
                turnosPorDia[day] = DiaTurno(turno = turno)
                navController.popBackStack()
            }
        }

        composable(
            route = "horas/{day}",
            arguments = listOf(navArgument("day") { type = NavType.IntType })
        ) { backStack ->
            val day = backStack.arguments!!.getInt("day")

            val diaTurno = turnosPorDia[day]

            // ❌ No permitir horas en Libre o Vacaciones
            if (
                diaTurno != null &&
                diaTurno.turno != Turno.LIBRE &&
                diaTurno.turno != Turno.VACACIONES
            ) {
                HorasScreen(day) { horas ->
                    turnosPorDia[day] =
                        diaTurno.copy(horasExtra = horas)
                    navController.popBackStack()
                }
            } else {
                navController.popBackStack()
            }
        }
    }
}
