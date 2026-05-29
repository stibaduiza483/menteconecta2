package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EscogeEspecialistaScreen(navController: NavController) {
    val azulPrincipal = Color(0xFF1A237E)
    val fondoApp = Color(0xFFF0F7F8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoApp)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Escoge tu especialista",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = azulPrincipal,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { navController.navigate("psiquiatras") },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
        ) {
            Text("🩺 Psiquiatras", fontSize = 20.sp, color = azulPrincipal, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = { navController.navigate("psicologos") },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
        ) {
            Text("🧠 Psicólogos", fontSize = 20.sp, color = azulPrincipal, fontWeight = FontWeight.Bold)
        }
    }
}