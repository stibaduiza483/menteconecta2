package com.example.menteconecta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {

    var esPacienteSelected by remember { mutableStateOf(true) }

    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val azulBrillante = Color(0xFF0084FF)
    val azulOscuroTexto = Color(0xFF1A237E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F9FC))
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MenteConecta",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = azulOscuroTexto
        )

        Text(
            text = "Bienvenido",
            fontSize = 18.sp,
            color = azulOscuroTexto.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = { esPacienteSelected = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (esPacienteSelected) azulBrillante else Color(0xFFD0E3F7),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(130.dp)
                    .height(42.dp)
            ) {
                Text("Paciente", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.width(20.dp))


            Button(
                onClick = { esPacienteSelected = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!esPacienteSelected) azulBrillante else Color(0xFFD0E3F7),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(130.dp)
                    .height(42.dp)
            ) {
                Text("Especialista", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(50.dp))


        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("email@domain.com", color = Color.White.copy(alpha = 0.8f)) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = azulBrillante,
                unfocusedContainerColor = azulBrillante,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            placeholder = { Text("Contraseña", color = Color.White.copy(alpha = 0.8f)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = azulBrillante,
                unfocusedContainerColor = azulBrillante,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                if (esPacienteSelected) {

                    UserSession.rolActivo = "paciente"
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {

                    UserSession.rolActivo = "especialista"
                    navController.navigate("home_doctor") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "By clicking continue, you agree to our Terms of Service\nand Privacy Policy",
            fontSize = 11.sp,
            color = azulOscuroTexto.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}