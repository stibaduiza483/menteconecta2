package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class CitaDisponible(
    val nombre: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val duracion: String
)

@Composable
fun PsicologosScreen(navController: NavController) {
    val listaCitas = listOf(
        CitaDisponible("LAURA GOMEZ", "Psicologa Clinica", "Viernes 7 de noviembre", "11:30am", "1 hora"),
        CitaDisponible("LAURA GOMEZ", "Psicologa Clinica", "Martes 11 de noviembre", "11:30am", "1 hora"),
        CitaDisponible("Margarita Paez", "Psicologa Clinica", "Viernes 7 de noviembre", "10:30am", "1 hora")
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
                items(listaCitas) { cita ->
                    // USAMOS LA TARJETA QUE TIENE EL CLICKABLE
                    TarjetaEspecialista(cita, navController)
                }
            }
        }
    }
}

@Composable
fun TarjetaEspecialista(cita: CitaDisponible, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .clickable {

            }
            .border(2.dp, Color(0xFF2196F3), RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = Color.LightGray
            ) {

            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = cita.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1A237E))
                Text(text = cita.especialidad, fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "FECHA: ${cita.fecha}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "HORA: ${cita.hora}", fontSize = 12.sp)
                Text(text = "Duracion: ${cita.duracion}", fontSize = 12.sp)


                Text(
                    text = "Toca para agendar",
                    color = Color(0xFF2196F3),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 4.dp)
                )
            }
        }
    }
}