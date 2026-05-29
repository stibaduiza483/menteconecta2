package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class AlertaEspecialista(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val tiempo: String,
    val categoria: CategoriaAlerta,
    var revisada: Boolean
)

enum class CategoriaAlerta {
    CITA_MEDICA, CRITICA_PACIENTE, GENERAL_SISTEMA
}

@Composable
fun NotificacionesDos(navController: NavController) {

    var listaAlertas by remember {
        mutableStateOf(
            listOf(
                AlertaEspecialista(
                    id = 1,
                    titulo = "Nueva Consulta Solicitada",
                    descripcion = "El paciente Carlos Mendoza agendó una cita de valoración prioritaria para mañana a las 09:00 AM.",
                    tiempo = "Hace 10 min",
                    categoria = CategoriaAlerta.CITA_MEDICA,
                    revisada = false
                ),
                AlertaEspecialista(
                    id = 2,
                    titulo = "Solicitud de Reajuste",
                    descripcion = "Laura Gómez solicita revisión de su fórmula médica actual por efectos secundarios leves.",
                    tiempo = "Hace 1 hora",
                    categoria = CategoriaAlerta.CRITICA_PACIENTE,
                    revisada = false
                ),
                AlertaEspecialista(
                    id = 3,
                    titulo = "Sincronización Exitosa",
                    descripcion = "El historial clínico y notas de evolución de Andrés Martínez se han guardado en la nube.",
                    tiempo = "Hace 3 horas",
                    categoria = CategoriaAlerta.GENERAL_SISTEMA,
                    revisada = true
                )
            )
        )
    }

    Scaffold(
        bottomBar = {
            com.example.menteconecta.ui.theme.BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Notificaciones",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "Alertas y actualizaciones de tu actividad clínica",
                        fontSize = 14.sp,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }


                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                listaAlertas = listaAlertas.map { it.copy(revisada = true) }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0084FF)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Leer todo", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }


                items(listaAlertas) { alerta ->
                    ItemAlertaDoctor(
                        alerta = alerta,
                        onClic = {
                            listaAlertas = listaAlertas.map {
                                if (it.id == alerta.id) it.copy(revisada = true) else it
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun ItemAlertaDoctor(alerta: AlertaEspecialista, onClic: () -> Unit) {

    val (icono, colorIcono, fondoIcono) = when (alerta.categoria) {
        CategoriaAlerta.CITA_MEDICA -> Triple(
            Icons.Default.DateRange,
            Color(0xFF0084FF),
            Color(0xFFE0F2FE)
        )
        CategoriaAlerta.CRITICA_PACIENTE -> Triple(
            Icons.Default.Warning,
            Color(0xFFEF4444),
            Color(0xFFFEE2E2)
        )
        CategoriaAlerta.GENERAL_SISTEMA -> Triple(
            Icons.Default.Notifications,
            Color(0xFF10B981),
            Color(0xFFD1FAE5)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClic() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (alerta.revisada) Color.White else Color(0xFFF1F5F9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(fondoIcono, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icono,
                    contentDescription = null,
                    tint = colorIcono,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))


            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = alerta.titulo,
                        fontSize = 15.sp,
                        fontWeight = if (alerta.revisada) FontWeight.Medium else FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Text(
                        text = alerta.tiempo,
                        fontSize = 11.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = alerta.descripcion,
                    fontSize = 13.sp,
                    color = Color(0xFF475569),
                    lineHeight = 18.sp
                )
            }


            if (!alerta.revisada) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 6.dp)
                        .size(8.dp)
                        .background(Color(0xFF0084FF), CircleShape)
                )
            }
        }
    }
}