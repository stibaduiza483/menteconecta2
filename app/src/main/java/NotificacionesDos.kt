package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class NotificacionCita(
    val id: String,
    val nombrePaciente: String,
    val tiempoAgo: String,
    val mensaje: String,
    val leido: Boolean
)

@Composable
fun NotificacionesDos(navController: NavController) {
    val azulPrincipal = Color(0xFF1A237E)
    val azulClaro = Color(0xFF2196F3)
    val fondoApp = Color(0xFFF0F7F8)

    // Lista basada exactamente en tu maqueta visual
    val listaNotificaciones = remember {
        listOf(
            NotificacionCita("1", "Paula Paez", "1d", "Ha agendado una cita contigo", false),
            NotificacionCita("2", "Lucia Ramirez", "1d", "Ha agendado una cita contigo", false),
            NotificacionCita("3", "Miguel Torres", "5d", "Ha agendado una cita contigo", false),
            NotificacionCita("4", "Saray Medina", "5d", "Ha agendado una cita contigo", false),
            NotificacionCita("5", "Danna Murcia", "5d", "Ha agendado una cita contigo", true),
            NotificacionCita("6", "Nikolai Bracho", "5d", "Ha agendado una cita contigo", true),
            NotificacionCita("7", "Rocio Rozo", "5d", "Ha agendado una cita contigo", true),
            NotificacionCita("8", "Cristina Suarez", "5d", "Ha agendado una cita contigo", true),
            NotificacionCita("9", "Pilar Rodriguez", "5d", "Ha agendado una cita contigo", true),
            NotificacionCita("10", "Luisa Mendez", "5d", "Ha agendado una cita contigo", true)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoApp)
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        // Título principal
        Text(
            text = "Notificaciones citas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Botón/Filtro "Todo"
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
            modifier = Modifier.height(32.dp)
        ) {
            Text("Todo", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Listado scrolleable
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listaNotificaciones) { notificacion ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Punto rojo indicator de No Leído
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(if (!notificacion.leido) Color.Red else Color.Transparent, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Avatar circular gris (marcador de posición para la foto)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.LightGray.copy(alpha = 0.6f), CircleShape)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    // Textos de la notificación
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = notificacion.nombrePaciente,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                            Text(
                                text = notificacion.tiempoAgo,
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                        Text(
                            text = notificacion.mensaje,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}