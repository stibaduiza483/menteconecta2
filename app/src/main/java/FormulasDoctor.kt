package com.example.menteconecta

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Surface
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
fun FormulasDoctorScreen(navController: NavController) {

    var filtroSeleccionado by remember { mutableStateOf("Formulas") }

    Scaffold(
        bottomBar = {
            com.example.menteconecta.ui.theme.BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Formulas",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }


                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val opciones = listOf("Todo", "Formulas", "Psicologia", "Psiquiatria")
                        opciones.forEach { opcion ->
                            PildoraFiltroLocal(
                                texto = opcion,
                                seleccionado = (filtroSeleccionado == opcion),
                                onClick = { filtroSeleccionado = opcion }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                item {
                    Button(
                        onClick = { /* Aquí agregarás tu lógica o diálogo para crear receta */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0084FF)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text("Nueva formula", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }


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
                                    Text(
                                        text = "MENTECONECTA",
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF0033CC),
                                        fontSize = 18.sp,
                                        letterSpacing = 0.5.sp
                                    )
                                    Text("Salud Mental", fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("Paciente: ____________________", fontSize = 10.sp, color = Color.DarkGray)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Fecha: ____________________", fontSize = 10.sp, color = Color.DarkGray)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = Color(0xFF0084FF), thickness = 2.dp)
                            Spacer(modifier = Modifier.height(10.dp))


                            Text("PATIENT INFORMATION", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.height(8.dp))

                            FilaCampoTextoDoble("First Name:", "_______________________", "Last Name:", "_______________________")
                            FilaCampoTextoDoble("Birth Date:", "_______________________", "Address:", "_______________________")
                            FilaCampoTextoDoble("City:", "_______________________", "State:", "_______________")
                            FilaCampoTextoDoble("Email:", "_______________________", "Cell Phone:", "_______________________")


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Gender:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(1f))
                                Row(
                                    modifier = Modifier.weight(3f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = false,
                                        onClick = {},
                                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text("Male", fontSize = 10.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 4.dp))

                                    Spacer(modifier = Modifier.size(16.dp))

                                    RadioButton(
                                        selected = false,
                                        onClick = {},
                                        colors = RadioButtonDefaults.colors(unselectedColor = Color.Gray),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text("Female", fontSize = 10.sp, color = Color.DarkGray, modifier = Modifier.padding(start = 4.dp))
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


                            FilaMedicamentoTalonario("Sertralina 50mg", "Forma de consumo: Oral, tomar dos capsulas antes de ir a dormir", "60 capsulas")
                            FilaMedicamentoTalonario("Venlafaxina XR", "Forma de consumo: Oral, Tomar una capsula despues de cada comida", "90 capsulas")
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                item {
                    Button(
                        onClick = { /* Aquí agregarás la lógica para insertar filas a la receta */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0084FF)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text("Nuevo Medicamento", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
fun FilaCampoTextoDoble(label1: String, linea1: String, label2: String, linea2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(label1, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Text(linea1, fontSize = 10.sp, color = Color.Gray)
        }
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(label2, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Text(linea2, fontSize = 10.sp, color = Color.Gray)
        }
    }
}

@Composable
fun FilaMedicamentoTalonario(med: String, inst: String, cant: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, Color.Black)
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(med, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(1.2f))
        Text(inst, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(2.2f))
        Text(cant, fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.weight(0.8f))
    }
}

@Composable
fun PildoraFiltroLocal(texto: String, seleccionado: Boolean, onClick: () -> Unit) {
    Surface(
        color = if (seleccionado) Color(0xFF0084FF) else Color.White,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, if (seleccionado) Color.Transparent else Color(0xFFE0E0E0)),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = texto,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = if (seleccionado) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        )
    }
}