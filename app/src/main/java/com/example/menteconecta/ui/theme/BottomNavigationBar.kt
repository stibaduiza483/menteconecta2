package com.example.menteconecta.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry.value?.destination?.route


    val rutasDelDoctor = listOf(
        "home_doctor",
        "pacientes_lista",
        "formulas_doctor",
        "historia_clinica_doctor/{pacienteNombre}",
        "notificaciones_doctor",
        "perfil_doctor",
        "calendario_doctor"
    )

    val esDoctor = rutasDelDoctor.contains(rutaActual)

    NavigationBar(
        containerColor = Color.White
    ) {
        if (esDoctor) {
            // ---- MENÚ DEL DOCTOR ----
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                label = { Text("Inicio") },
                selected = rutaActual == "home_doctor",
                onClick = { navController.navigate("home_doctor") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Group, contentDescription = "Pacientes") },
                label = { Text("Pacientes") },
                selected = rutaActual == "pacientes_lista",
                onClick = { navController.navigate("pacientes_lista") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Notifications, contentDescription = "Alertas") },
                label = { Text("Notificaciones") },
                selected = rutaActual == "notificaciones_doctor", // Corregido e igualado
                onClick = { navController.navigate("notificaciones_doctor") { launchSingleTop = true } }, // Redirección corregida
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                label = { Text("Perfil") },
                selected = rutaActual == "perfil_doctor",
                onClick = { navController.navigate("perfil_doctor") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
        } else {

            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                label = { Text("Inicio") },
                selected = rutaActual == "home_paciente",
                onClick = { navController.navigate("home_paciente") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Citas") },
                label = { Text("Calendario") },
                selected = rutaActual == "calendario",
                onClick = { navController.navigate("calendario") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Description, contentDescription = "Recetas") },
                label = { Text("Fórmulas") },
                selected = rutaActual == "formulas",
                onClick = { navController.navigate("formulas") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                label = { Text("Perfil") },
                selected = rutaActual == "perfil",
                onClick = { navController.navigate("perfil") { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF0084FF))
            )
        }
    }
}