package com.example.controldeturnos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.controldeturnos.ui.calendar.CalendarScreen
import com.example.controldeturnos.ui.horas.HorasScreen
import com.example.controldeturnos.ui.turnos.TurnosScreen
import com.example.controldeturnos.ui.shared.TurnosSharedViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    // ✅ ViewModel compartido entre Calendar, Turnos y Horas
    val sharedViewModel: TurnosSharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "calendar"
    ) {

        composable("calendar") {
            CalendarScreen(
                sharedViewModel = sharedViewModel,
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

            TurnosScreen(
                day = day,
                sharedViewModel = sharedViewModel,
                onDone = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "horas/{day}",
            arguments = listOf(navArgument("day") { type = NavType.IntType })
        ) { backStack ->
            val day = backStack.arguments!!.getInt("day")

            HorasScreen(
                day = day,
                sharedViewModel = sharedViewModel,
                onDone = {
                    navController.popBackStack()
                }
            )
        }
    }
}

