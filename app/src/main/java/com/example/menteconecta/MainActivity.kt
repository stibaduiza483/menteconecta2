package com.example.menteconecta

import NotificacionesUno
import PerfilScreen
import QueEsMenteConecta
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.menteconecta.ui.theme.BottomNavigationBar
import com.example.menteconecta.ui.theme.MenteconectaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenteconectaTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                remember {
                    UserSession.rolActivo = "paciente"
                    UserSession.rolActivo
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute != "login") {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {

                            composable("login") { LoginScreen(navController) }


                            composable("home") { HomeScreen(navController) }
                            composable("notificaciones") { NotificacionesUno(navController) }
                            composable("perfil") { PerfilScreen(navController) }
                            composable("que_es_menteconecta") { QueEsMenteConecta(navController) }
                            composable("calendario") { CalendarioScreen(navController) }
                            composable("formulas") { FormulasScreen(navController) }
                            composable("especialista") { EspecialistaScreen(navController) }
                            composable("psicologos_lista") { PsicologosScreen(navController) }
                            composable("psiquiatras_lista") { PsiquiatrasScreen(navController) }


                            composable("historia_clinica_paciente") {
                                HistoriaClinicaScreen(navController)
                            }


                            composable("home_doctor") { HomeDoctorScreen(navController) }
                            composable("pacientes_lista") { PacientesListaScreen(navController) }


                            composable("historia_clinica_doctor/{pacienteNombre}") { backStackEntry ->
                                val nombre = backStackEntry.arguments?.getString("pacienteNombre") ?: "Paciente"
                                HistoriaClinicaDoctorScreen(navController, nombre)
                            }
                        }
                    }
                }
            }
        }
    }
}