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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class PacienteDoctor(
    val id: String,
    val nombre: String,
    val tiempo: String,
    val fechaHora: String,
    val edad: String,
    val diagnostico: String,
    val tienePuntoIndicador: Boolean
)

@Composable
fun PacientesListaScreen(navController: NavController) {
    val listaPacientes = listOf(
        PacienteDoctor("1", "Paula Paez", "1d", "1 de noviembre 9:30am", "35 años...", "Depresion", true),
        PacienteDoctor("2", "Lucia Ramirez", "1d", "31 de octubre 4:30pm", "7 años...", "Depresion", true),
        PacienteDoctor("3", "Miguel Torres", "5d", "30 de octubre 2:30pm", "22 años...", "Depresion", true),
        PacienteDoctor("4", "Saray Medina", "5d", "24 de octubre 10:00am", "7 años...", "Depresion", true),
        PacienteDoctor("5", "Danna Murcia", "5d", "8 de octubre 9:30am", "7 años...", "Depresion", false)
    )

    Scaffold(

        bottomBar = {
            com.example.menteconecta.ui.theme.BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PildoraPacientesLocal("Citas")
                PildoraPacientesLocal("Recordatorios")
                PildoraPacientesLocal("Pacientes")
                PildoraPacientesLocal("Formulas")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /* Lógica para añadir horarios */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .height(38.dp)
            ) {
                Text(
                    text = "Añadir horarios",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listaPacientes) { paciente ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 6.dp, end = 8.dp)
                                .size(8.dp)
                                .background(
                                    color = if (paciente.tienePuntoIndicador) Color(0xFFFF3366) else Color.Transparent,
                                    shape = CircleShape
                                )
                        )

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFFE5E7EB), shape = CircleShape)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = paciente.nombre,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = paciente.tiempo,
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }

                            Text(text = paciente.fechaHora, fontSize = 13.sp, color = Color.Gray)

                            Spacer(modifier = Modifier.height(4.dp))

                            Column(
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .border(width = (1.5).dp, color = Color(0xFFE5E7EB))
                                    .padding(start = 8.dp)
                            ) {
                                Text(text = "Edad: ${paciente.edad}", fontSize = 13.sp, color = Color.Gray)
                                Text(text = "Diagnosticos: ${paciente.diagnostico}", fontSize = 13.sp, color = Color.Gray)
                            }

                            if (paciente.nombre != "Danna Murcia") {
                                Spacer(modifier = Modifier.height(10.dp))
                                Button(
                                    onClick = {
                                        navController.navigate("historia_clinica_doctor/${paciente.nombre}")
                                    },

                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF)),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Historia Clinica", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PildoraPacientesLocal(texto: String) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = Color.White,
        modifier = Modifier.border(0.5.dp, Color.LightGray, RoundedCornerShape(6.dp))
    ) {
        Text(
            text = texto,
            fontSize = 11.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontWeight = FontWeight.Normal
        )
    }
}