package com.example.menteconecta

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MenteConectaNavGraph(navController: androidx.navigation.NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("notificaciones") { NotificacionesUno(navController) }
        composable("perfil") { PerfilScreen(navController) }
        composable("que_es_menteconecta") { QueEsMenteConecta(navController) }
    }
}