package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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

@Composable
fun HistoriaClinicaDoctorScreen(navController: NavController, pacienteNombre: String) {

    var mostrarFormulario by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F9FC))
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Panel Clínico Especialista",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Gestión de Paciente: $pacienteNombre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }


                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Evolución y Diagnóstico",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A237E),
                                fontSize = 16.sp
                            )
                            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), thickness = 0.5.dp)
                            Text(
                                text = "El paciente manifiesta episodios moderados de ansiedad. Se sugiere mantener control farmacológico complementario y seguimiento en terapia.",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                lineHeight = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                item {
                    Button(
                        onClick = { mostrarFormulario = !mostrarFormulario },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (mostrarFormulario) Color.Gray else Color(0xFF0084FF)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (mostrarFormulario) "Ocultar Módulo de Fórmulas" else "Formular Medicamento",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                if (mostrarFormulario) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, Color(0xFF0084FF), RoundedCornerShape(4.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {


                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Column {
                                        Text("MENTECONECTA", fontWeight = FontWeight.ExtraBold, color = Color(0xFF0033CC), fontSize = 18.sp)
                                        Text("Salud Mental", fontSize = 11.sp, color = Color.Gray)
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        // Pone automáticamente el nombre del paciente actual seleccionado
                                        Text("Paciente: $pacienteNombre", fontSize = 10.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text("Fecha: 28/05/2026", fontSize = 10.sp, color = Color.DarkGray)
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                                HorizontalDivider(color = Color(0xFF0084FF), thickness = 2.dp)
                                Spacer(modifier = Modifier.height(10.dp))


                                Text("PATIENT INFORMATION", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                Spacer(modifier = Modifier.height(8.dp))

                                FilaTextoDobleLocal("First Name:", pacienteNombre.substringBefore(" "), "Last Name:", pacienteNombre.substringAfter(" ", ""))
                                FilaTextoDobleLocal("Birth Date:", "12 / 08 / 1998", "Address:", "Calle 100 #15-23")
                                FilaTextoDobleLocal("City:", "Bogotá", "State:", "Cundinamarca")


                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Gender:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
                                    Row(modifier = Modifier.weight(3f), verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(selected = true, onClick = {}, colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0084FF)))
                                        Text("Male", fontSize = 10.sp, color = Color.DarkGray)
                                        Spacer(modifier = Modifier.size(16.dp))
                                        RadioButton(selected = false, onClick = {}, colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray))
                                        Text("Female", fontSize = 10.sp, color = Color.DarkGray)
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                                HorizontalDivider(color = Color.Black, thickness = 1.5.dp)
                                Spacer(modifier = Modifier.height(10.dp))


                                Text("FORMULA", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                Spacer(modifier = Modifier.height(8.dp))


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, Color.Black)
                                        .background(Color(0xFFF2F2F2))
                                        .padding(6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("MEDICAMENTO", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1.2f))
                                    Text("INSTRUCCIONES DE CONSUMO", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(2.2f))
                                    Text("CANTIDAD", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.8f))
                                }


                                FilaMedicamentoLocal("Sertralina 50mg", "Forma de consumo: Oral, tomar dos capsulas antes de ir a dormir", "60 capsulas")
                                FilaMedicamentoLocal("Venlafaxina XR", "Forma de consumo: Oral, Tomar una capsula despues de cada comida", "90 capsulas")

                                Spacer(modifier = Modifier.height(16.dp))


                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = { /* Acción para añadir medicamento */ },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0084FF)),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Text("Añadir Fila", color = Color.White, fontSize = 11.sp)
                                    }
                                    Button(
                                        onClick = { /* Guardar e imprimir fórmula */ },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Text("Guardar Fórmula", color = Color.White, fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FilaTextoDobleLocal(label1: String, val1: String, label2: String, val2: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(label1, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Text(val1, fontSize = 10.sp, color = Color.DarkGray)
        }
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(label2, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Text(val2, fontSize = 10.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun FilaMedicamentoLocal(med: String, inst: String, cant: String) {
    Row(
        modifier = Modifier.fillMaxWidth().border(0.5.dp, Color.Black).padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(med, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(1.2f))
        Text(inst, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(2.2f))
        Text(cant, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(0.8f))
    }
}