package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HistoriaClinica(navController: NavController) {
    val darkBlue = Color(0xFF1A237E)
    val lightBlue = Color(0xFF2196F3)

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F7F8))
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                item {
                    Text(
                        text = "HISTORIA CLÍNICA",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkBlue
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    InfoCardPaciente(
                        titulo = "Resumen del Paciente",
                        contenido = "Paciente: Nikolay Borrero\nID: 1002345678\nÚltima actualización: 12 Mayo 2026",
                        colorBorde = lightBlue
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    InfoCardPaciente(
                        titulo = "Evolución Médica",
                        contenido = "El paciente muestra una mejora progresiva en su estado de ánimo. Se recomienda continuar con la terapia cognitivo-conductual. Próxima revisión en 15 días.",
                        colorBorde = Color.Gray
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    InfoCardPaciente(
                        titulo = "Diagnóstico Actual",
                        contenido = "F41.1 - Trastorno de ansiedad generalizada.\nEstado: En tratamiento activo.",
                        colorBorde = Color(0xFFFF5252)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCardPaciente(titulo: String, contenido: String, colorBorde: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, color = colorBorde, fontSize = 18.sp)
            Divider(modifier = Modifier.padding(vertical = 10.dp), thickness = 0.5.dp)
            Text(text = contenido, fontSize = 15.sp, color = Color.DarkGray, lineHeight = 22.sp)
        }
    }
}