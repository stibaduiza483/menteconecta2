package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(

        bottomBar = {
            com.example.menteconecta.ui.theme.BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F7F8))
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
                        text = "HOME",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clickable { navController.navigate("que_es_menteconecta") },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF03A9F4))
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "¿QUÉ ES MENTE CONECTA?",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }

                item {
                    Text(text = "Servicios", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(15.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f).clickable { navController.navigate("calendario") }) {
                                ServiceItem("Calendario", Icons.Default.DateRange)
                            }

                            Box(modifier = Modifier.weight(1f).clickable { navController.navigate("historia_clinica_paciente") }) {
                                ServiceItem("Historia", Icons.Default.Description)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f).clickable { navController.navigate("formulas") }) {
                                ServiceItem("Formulas", Icons.Default.Description)
                            }
                            Box(modifier = Modifier.weight(1f).clickable { navController.navigate("especialista") }) {
                                ServiceItem("Citas", Icons.Default.DateRange)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Novedades", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        NovedadItem(
                            titulo = "Psicologo",
                            nombre = "Laura Gomez",
                            colorFondo = Color(0xFFD9D9D9),
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate("psicologos_lista") }
                        )
                        NovedadItem(
                            titulo = "Medicamento",
                            nombre = "Sertralina",
                            colorFondo = Color.White,
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate("formulas") }
                        )
                        NovedadItem(
                            titulo = "Psiquiatra",
                            nombre = "Andres Martinez",
                            colorFondo = Color(0xFFD9D9D9),
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate("psiquiatras_lista") }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun NovedadItem(titulo: String, nombre: String, colorFondo: Color, modifier: Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.clickable { onClick() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = colorFondo),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {}
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = titulo, fontSize = 11.sp, color = Color.Gray)
        Text(text = nombre, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun ServiceItem(titulo: String, icono: ImageVector) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = Color(0xFF2196F3),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = titulo, fontWeight = FontWeight.Medium, fontSize = 12.sp)
        }
    }
}