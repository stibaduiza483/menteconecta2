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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class NovedadItem(val titulo: String, val subtitulo: String, val inicial: String)

@Composable
fun HomeScreen(navController: NavController) {
    val fondoApp = Color(0xFFFFFFFF)
    val azulTexto = Color(0xFF1A237E)

    val novedades = listOf(
        NovedadItem("Psicologo", "Laura Gomez", "LG"),
        NovedadItem("Medicamento", "Sertralina", "S"),
        NovedadItem("Psiquiatra", "Andres Martinez", "AM")
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(fondoApp)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "HOME",
                fontSize = 14.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )


            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ChipFiltro("♡ Citas")
                ChipFiltro("🕒 Recordatorios")
                ChipFiltro("👤 Especialistas")
                ChipFiltro("📄 Formulas")
            }

            // ---- BANNER PRINCIPAL ----
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0052CC))
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text(
                        text = "¿QUE ES\nMENTECONECTA?",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }

            // ---- SECCIÓN SERVICIOS ----
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Servicios", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Icon(Icons.Default.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemServicioHome("Calendario", Icons.Default.CalendarMonth) { navController.navigate("calendario") }
                ItemServicioHome("Historia\nClinica", Icons.Default.Description) { }
                ItemServicioHome("Asignacion\nde Citas", Icons.Default.MedicalServices) { navController.navigate("asignacion_citas") }
                ItemServicioHome("Formulas", Icons.Default.Description) { }
            }

            // ---- SECCIÓN NOVEDADES ----
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Novedades", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Icon(Icons.Default.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)
            }

            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(novedades) { novedad ->
                    Card(
                        modifier = Modifier.width(130.dp).height(170.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Contenedor simulador de foto
                            Box(
                                modifier = Modifier.fillMaxWidth().weight(1f).background(Color(0xFFE0E0E0)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(novedad.inicial, fontWeight = FontWeight.Bold, color = Color.Gray)
                            }
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(novedad.titulo, fontSize = 11.sp, color = Color.Gray)
                                Text(novedad.subtitulo, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChipFiltro(texto: String) {
    Box(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .background(Color.Transparent)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White
        ) {
            Text(
                text = texto,
                fontSize = 11.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun ItemServicioHome(titulo: String, icono: ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(75.dp).clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFFF0F4FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icono, contentDescription = titulo, tint = Color(0xFF0084FF), modifier = Modifier.size(26.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = titulo,
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            lineHeight = 14.sp
        )
    }
}