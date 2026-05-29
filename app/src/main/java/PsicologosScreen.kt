package com.example.menteconecta

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun PsicologosScreen(navController: NavController) {
    val context = LocalContext.current
    val azulPrincipal = Color(0xFF1A237E)
    val azulClaro = Color(0xFF2196F3)
    val fondoApp = Color(0xFFF0F7F8)

    var listaHorarios by remember { mutableStateOf(listOf<HorarioDisponible>()) }
    var cargando by remember { mutableStateOf(true) }

    DisposableEffect(Unit) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("horarios_disponibles")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temporalList = mutableListOf<HorarioDisponible>()
                for (data in snapshot.children) {
                    val id = data.child("id").getValue(String::class.java) ?: ""
                    val doctor = data.child("doctorNombre").getValue(String::class.java) ?: ""
                    val esp = data.child("especialidad").getValue(String::class.java) ?: ""
                    val fecha = data.child("fecha").getValue(String::class.java) ?: ""
                    val hora = data.child("hora").getValue(String::class.java) ?: ""
                    val duracion = data.child("duracion").getValue(String::class.java) ?: ""

                    // Filtro estricto para psicólogos
                    if (esp.equals("Psicologo", ignoreCase = true)) {
                        temporalList.add(HorarioDisponible(id, doctor, esp, fecha, hora, duracion))
                    }
                }
                listaHorarios = temporalList
                cargando = false
            }

            override fun onCancelled(error: DatabaseError) {
                cargando = false
                Toast.makeText(context, "Error al cargar horarios", Toast.LENGTH_SHORT).show()
            }
        }
        databaseRef.addValueEventListener(listener)
        onDispose { databaseRef.removeEventListener(listener) }
    }

    Column(modifier = Modifier.fillMaxSize().background(fondoApp).padding(16.dp)) {
        Text("Agenda tu cita", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = azulPrincipal)
        Text("Especialidad: Psicología", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        if (cargando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = azulClaro)
            }
        } else if (listaHorarios.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay citas de psicología disponibles.", color = Color.Gray)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(listaHorarios) { horario ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(horario.doctorNombre, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = azulPrincipal)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("📅 FECHA: ${horario.fecha}", fontSize = 14.sp)
                            Text("⏰ HORA: ${horario.hora}", fontSize = 14.sp)
                            Text("⏳ Duración: ${horario.duracion}", fontSize = 14.sp)

                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    val appointmentsRef = FirebaseDatabase.getInstance().getReference("citas_agendadas")
                                    val citaId = appointmentsRef.push().key ?: ""
                                    val datosCita = mapOf(
                                        "citaId" to citaId,
                                        "doctor" to horario.doctorNombre,
                                        "fecha" to horario.fecha,
                                        "hora" to horario.hora,
                                        "especialidad" to horario.especialidad,
                                        "pacienteNombre" to "Usuario Paciente"
                                    )
                                    appointmentsRef.child(citaId).setValue(datosCita).addOnSuccessListener {
                                        FirebaseDatabase.getInstance().getReference("horarios_disponibles").child(horario.id).removeValue()
                                        Toast.makeText(context, "¡Cita agendada con éxito!", Toast.LENGTH_LONG).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("AGENDAR", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}