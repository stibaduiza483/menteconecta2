package com.example.menteconecta

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        try {
            FirebaseApp.initializeApp(this)
            Log.d("APP_DEBUG", "Firebase inicializado con éxito")
        } catch (e: Exception) {
            Log.e("APP_DEBUG", "Error iniciando Firebase: ${e.message}")
        }

        setContent {
            val navController = rememberNavController()

            Scaffold { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(route = "login") { LoginScreen(navController) }


                    composable(route = "home_paciente") { HomeScreen(navController) }
                    composable(route = "especialista") { EspecialistasScreen(navController) }
                    composable(route = "notificaciones_paciente") { NotificacionesUno(navController) }
                    composable(route = "perfil") { PerfilScreen(navController) }
                    composable(route = "calendario") { Calendario(navController) }
                    composable(route = "formulas") { FormulasScreen(navController) }
                    composable(route = "psicologos_lista") { PsicologosScreen(navController) }
                    composable(route = "psiquiatras_lista") { PsiquiatrasScreen(navController) }
                    composable(route = "historia_clinica_paciente") { HistoriaClinica(navController) }
                    composable(route = "que_es_menteconecta") { QueEsMenteConecta(navController) }


                    composable(route = "home_doctor") { HomeDoctorScreen(navController) }
                    composable(route = "calendario_doctor") { CalendarioDoctor(navController) }
                    composable(route = "pacientes_lista") { PacientesListaScreen(navController) }
                    composable(route = "formulas_doctor") { FormulasDoctorScreen(navController) }
                    composable(route = "perfil_doctor") { PerfilDoctorScreen(navController) }

                    composable(route = "historia_clinica_doctor/{pacienteNombre}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("pacienteNombre") ?: "Paciente"
                        HistoriaClinicaDoctorScreen(navController = navController, pacienteNombre = nombre)
                    }

                    composable(route = "notificaciones") { NotificacionesDos(navController) }
                    composable(route = "notificaciones_dos") { NotificacionesDos(navController) }
                }
            }
        }
    }
}