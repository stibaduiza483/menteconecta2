package com.example.menteconecta

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.menteconecta.models.Cita
import com.example.menteconecta.ui.theme.BottomNavigationBar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


data class HorarioDisponible(
    val id: String = "",
    val doctorNombre: String = "",
    val especialidad: String = "",
    val fecha: String = "",
    val hora: String = "",
    val duracion: String = "1 hora"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaCitaScreen(navController: NavController, tipoEspecialista: String) {
    val fondo = Color(0xFFF0F7F8)
    val context = LocalContext.current
    val horariosList = remember { mutableStateListOf<HorarioDisponible>() }
    var cargando by remember { mutableStateOf(true) }


    LaunchedEffect(tipoEspecialista) {
        val ref = FirebaseDatabase.getInstance().getReference("horarios_disponibles")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                horariosList.clear()
                if (snapshot.exists()) {
                    snapshot.children.forEach { child ->
                        val horario = child.getValue(HorarioDisponible::class.java)
                        if (horario != null && horario.especialidad.equals(tipoEspecialista, ignoreCase = true)) {
                            horariosList.add(horario.copy(id = child.key ?: ""))
                        }
                    }
                } else {

                    if (tipoEspecialista.equals("Psiquiatra", ignoreCase = true)) {
                        horariosList.add(HorarioDisponible("1", "Andres Martinez", "Psiquiatra", "Viernes 7 de noviembre", "10:30am"))
                        horariosList.add(HorarioDisponible("2", "Andres Martinez", "Psiquiatra", "Viernes 31 de octubre", "11:30am"))
                        horariosList.add(HorarioDisponible("3", "Linda Perez", "Psiquiatra", "Viernes 31 de octubre", "3:30pm"))
                    } else {
                        horariosList.add(HorarioDisponible("4", "LAURA GOMEZ", "Psicologa Clinica", "Viernes 7 de noviembre", "11:30am"))
                        horariosList.add(HorarioDisponible("5", "LAURA GOMEZ", "Psicologa Clinica", "Martes 11 de noviembre", "11:30am"))
                        horariosList.add(HorarioDisponible("6", "Margarita Paez", "Psicologa Clinica", "Viernes 7 de noviembre", "10:30am"))
                    }
                }
                cargando = false
            }

            override fun onCancelled(error: DatabaseError) {
                cargando = false
            }
        })
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fondo)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Agenda tu cita",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (cargando) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF2196F3))
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(horariosList) { horario ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {

                                        val citasRef = FirebaseDatabase.getInstance().getReference("citas")
                                        val nuevaCitaId = citasRef.push().key ?: ""

                                        val nuevaCita = Cita(
                                            id = nuevaCitaId,
                                            idPaciente = "paciente_demo_id",
                                            pacienteNombre = "Paciente de Prueba",
                                            idDoctor = horario.id,
                                            doctorNombre = horario.doctorNombre,
                                            especialidad = horario.especialidad,
                                            fecha = horario.fecha,
                                            hora = horario.hora,
                                            motivo = "Consulta inicial",
                                            estado = "pendiente"
                                        )

                                        citasRef.child(nuevaCitaId).setValue(nuevaCita)
                                            .addOnSuccessListener {
                                                Toast.makeText(context, "¡Cita agendada exitosamente!", Toast.LENGTH_LONG).show()
                                                navController.navigate("home") {
                                                    popUpTo("home") { inclusive = false }
                                                }
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(context, "Error al agendar", Toast.LENGTH_SHORT).show()
                                            }
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(75.dp)
                                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = horario.doctorNombre.take(2).uppercase(),
                                            fontWeight = FontWeight.Bold,
                                            color = Color.DarkGray,
                                            fontSize = 18.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = horario.doctorNombre.uppercase(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = horario.especialidad,
                                            fontSize = 13.sp,
                                            color = Color.Gray,
                                            modifier = Modifier.padding(bottom = 6.dp)
                                        )
                                        Text(
                                            text = "FECHA: ${horario.fecha}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = "HORA: ${horario.hora}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = "Duración: ${horario.duracion}",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}