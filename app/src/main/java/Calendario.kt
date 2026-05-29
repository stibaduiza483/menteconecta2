package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendario(navController: NavController) {
    val datePickerState = rememberDatePickerState()
    var mensajeConfirmacion by remember { mutableStateOf("") }

    val misCitas = remember { mutableStateListOf<Cita>() }
    var citaSeleccionada by remember { mutableStateOf<Cita?>(null) }
    var mostrarDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("citas")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                misCitas.clear()
                for (postSnapshot in snapshot.children) {
                    val cita = postSnapshot.getValue(Cita::class.java)
                    if (cita != null) misCitas.add(cita)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F7F8))
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "AGENDAR CITA", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A237E))
            Spacer(modifier = Modifier.height(15.dp))

            DatePicker(
                state = datePickerState,
                modifier = Modifier.background(Color.White),
                title = { Text(text = "Selecciona una fecha", modifier = Modifier.padding(horizontal = 16.dp)) }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val fechaFormateada = sdf.format(Date(selectedDateMillis))

                        val databaseRef = FirebaseDatabase.getInstance().getReference("citas")
                        val citaId = databaseRef.push().key ?: UUID.randomUUID().toString()

                        val nuevaCita = Cita(
                            id = citaId,
                            pacienteNombre = "Saray Gómez",
                            doctorNombre = "Dr. Camilo Ríos",
                            fecha = fechaFormateada,
                            hora = "11:00 AM",
                            motivo = "Valoración del estado anímico"
                        )

                        databaseRef.child(citaId).setValue(nuevaCita)
                            .addOnSuccessListener { mensajeConfirmacion = "¡Cita guardada en Firebase!" }
                            .addOnFailureListener { mensajeConfirmacion = "Error al conectar." }
                    } else {
                        mensajeConfirmacion = "Por favor, selecciona una fecha primero."
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Confirmar Cita", color = Color.White)
            }

            if (mensajeConfirmacion.isNotEmpty()) {
                Text(text = mensajeConfirmacion, color = Color(0xFF2E7D32), fontWeight = FontWeight.Medium, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "MIS CITAS PROGRAMADAS", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A237E), modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(misCitas) { cita ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                            citaSeleccionada = cita
                            mostrarDialog = true
                        },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(text = "Especialista: ${cita.doctorNombre}", fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1), fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Fecha: ${cita.fecha} — Hora: ${cita.hora} hrs", fontSize = 14.sp, color = Color.DarkGray)
                        }
                    }
                }
            }
        }
    }

    if (mostrarDialog && citaSeleccionada != null) {
        DetalleCitaDialogPacienteLocal(
            cita = citaSeleccionada!!,
            onDismiss = { mostrarDialog = false },
            onVerFicha = {
                mostrarDialog = false
                navController.navigate("historia_clinica_paciente")
            }
        )
    }
}

@Composable
fun DetalleCitaDialogPacienteLocal(cita: Cita, onDismiss: () -> Unit, onVerFicha: () -> Unit) {
    val context = LocalContext.current
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, null, tint = Color.Gray) }
                }
                Text(text = "Detalles de tu Cita", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Doctor: ${cita.doctorNombre}", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${cita.fecha} - ${cita.hora} hrs", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tu Nota/Motivo:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Text(cita.motivo, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = onVerFicha, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))) {
                    Icon(Icons.Default.Info, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("VER MI HISTORIAL")
                }
            }
        }
    }
}