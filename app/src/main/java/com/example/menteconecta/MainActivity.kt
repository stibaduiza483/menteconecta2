package com.example.menteconecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.menteconecta.ui.theme.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()


                    val navBackStackEntry = navController.currentBackStackEntryAsState()
                    val rutaActual = navBackStackEntry.value?.destination?.route

                    Scaffold(
                        bottomBar = {

                            if (rutaActual != "login" && rutaActual != null) {
                                BottomNavigationBar(navController)
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "login",
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable("login") {
                                LoginScreen(navController)
                            }
                            composable("home_paciente") {
                                HomeScreen(navController)
                            }
                            composable("home_doctor") {
                                HomeDoctorScreen(navController)
                            }
                            composable("asignacion_citas") {
                                EscogeEspecialistaScreen(navController)
                            }

                            composable("agenda_cita/{tipoEspecialista}") { backStackEntry ->
                                val tipo = backStackEntry.arguments?.getString("tipoEspecialista") ?: ""
                                AgendaCitaScreen(navController, tipoEspecialista = tipo)
                            }

                            composable("calendario") {
                                Calendario(navController)
                            }
                            composable("calendario_doctor") {
                                CalendarioDoctor(navController)
                            }
                            composable("crear_horario") {
                                CrearHorarioDoctor(navController)
                            }
                            composable("notificaciones_doctor") {
                                NotificacionesDos(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}