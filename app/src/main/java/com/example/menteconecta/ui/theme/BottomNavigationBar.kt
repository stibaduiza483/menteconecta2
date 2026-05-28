package com.example.menteconecta.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.menteconecta.UserSession

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = androidx.compose.ui.graphics.Color.White
    ) {

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {
                if (UserSession.rolActivo == "especialista") {
                    navController.navigate("home_doctor") {
                        popUpTo("home_doctor") { inclusive = false }
                    }
                } else {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                    }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Pacientes") },
            label = { Text("Pacientes") },
            selected = false,
            onClick = {
                if (UserSession.rolActivo == "especialista") {
                    navController.navigate("pacientes_lista")
                } else {
                    navController.navigate("psicologos_lista")
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Alertas") },
            label = { Text("Notificaciones") },
            selected = false,
            onClick = { navController.navigate("notificaciones") }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = false,
            onClick = { navController.navigate("perfil") }
        )
    }
}