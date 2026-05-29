package com.example.menteconecta

import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun CrearHorarioDoctor(navController: NavController) {
    val context = LocalContext.current
    val azulPrincipal = Color(0xFF1A237E)
    val azulClaro = Color(0xFF2196F3)
    val fondoApp = Color(0xFFF0F7F8)

    // Estados de control del formulario
    var fechaSeleccionada by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("Psicologo") }
    var cargando by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            // Barra de navegación con iconos del paquete básico (Core)
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Ya estamos aquí */ },
                    icon = { Icon(Icons.Default.List, contentDescription = "Agenda") },
                    label = { Text("Agenda") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Perfil (Próximamente)", Toast.LENGTH_SHORT).show()
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fondoApp)
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "CONFIGURAR HORARIO",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = azulPrincipal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 1. Calendario
                item {
                    Text(
                        text = "Selecciona el día disponible:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                CalendarView(ctx).apply {
                                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                                        val meses = listOf(
                                            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                                            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
                                        )
                                        val nombreMes = meses.getOrElse(month) { "" }
                                        fechaSeleccionada = "$dayOfMonth de $nombreMes de $year"
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )
                    }
                }

                // 2. Formulario de Horas y Especialidad
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

                            Text("Tipo de servicio:", fontWeight = FontWeight.Bold, color = azulPrincipal)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                val esPsicologo = especialidad == "Psicologo"
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            if (esPsicologo) azulClaro else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .border(1.dp, azulClaro, RoundedCornerShape(8.dp))
                                        .clickable { especialidad = "Psicologo" }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Psicología",
                                        color = if (esPsicologo) Color.White else azulClaro,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }

                                val esPsiquiatra = especialidad == "Psiquiatra"
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            if (esPsiquiatra) azulClaro else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .border(1.dp, azulClaro, RoundedCornerShape(8.dp))
                                        .clickable { especialidad = "Psiquiatra" }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Psiquiatría",
                                        color = if (esPsiquiatra) Color.White else azulClaro,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }

                            if (fechaSeleccionada.isNotEmpty()) {
                                Text(
                                    text = "Fecha seleccionada: $fechaSeleccionada",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = azulClaro
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0xFFE0E0E0))
                            )

                            OutlinedTextField(
                                value = horaInicio,
                                onValueChange = { horaInicio = it },
                                label = { Text("Hora Inicio (ej. 10:30am)") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            OutlinedTextField(
                                value = horaFin,
                                onValueChange = { horaFin = it },
                                label = { Text("Hora Fin (ej. 11:30am)") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                }

                // 3. Botón de Guardar en Firebase
                item {
                    Button(
                        onClick = {
                            if (fechaSeleccionada.isNotEmpty() && horaInicio.isNotEmpty() && horaFin.isNotEmpty()) {
                                cargando = true

                                val databaseRef = FirebaseDatabase.getInstance().getReference("horarios_disponibles")
                                val nuevoHorarioId = databaseRef.push().key ?: ""

                                val nuevoHorario = mapOf(
                                    "id" to nuevoHorarioId,
                                    "doctorNombre" to "Dr. Iván Gutiérrez",
                                    "especialidad" to especialidad,
                                    "fecha" to fechaSeleccionada,
                                    "hora" to "$horaInicio - $horaFin",
                                    "duracion" to "1 hora"
                                )

                                databaseRef.child(nuevoHorarioId).setValue(nuevoHorario)
                                    .addOnSuccessListener {
                                        cargando = false
                                        Toast.makeText(context, "Horario añadido a la agenda", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    }
                                    .addOnFailureListener {
                                        cargando = false
                                        Toast.makeText(context, "Error al conectar con Firebase", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(context, "Por favor selecciona un día en el calendario y llena las horas", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
                        enabled = !cargando
                    ) {
                        if (cargando) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                        } else {
                            Text("PUBLICAR DISPONIBILIDAD", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}