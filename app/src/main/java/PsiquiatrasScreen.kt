package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun PsiquiatrasScreen(navController: NavController) {

    val listaCitasPsiquiatria = listOf(
        CitaDisponible("Andres Martinez", "Psiquiatra", "Viernes 7 de noviembre", "10:30am", "1 hora"),
        CitaDisponible("Andres Martinez", "Psiquiatra", "Viernes 31 de octubre", "11:30am", "1 hora"),
        CitaDisponible("Linda Perez", "Psiquiatra", "Viernes 31 de octubre", "3:30pm", "1 hora")
    )

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F7F8))
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Agenda tu cita",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(listaCitasPsiquiatria) { cita ->

                    TarjetaEspecialista(cita, navController)
                }
            }
        }
    }
}