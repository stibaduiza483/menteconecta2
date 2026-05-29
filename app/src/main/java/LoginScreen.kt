package com.example.menteconecta

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val azulPrincipal = Color(0xFF1A237E)
    val azulClaro = Color(0xFF2196F3)
    val fondoApp = Color(0xFFF0F7F8)


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rolSeleccionado by remember { mutableStateOf("Paciente") } // "Paciente" o "Doctor"
    var cargando by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoApp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "MenteConecta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = azulPrincipal
            )
            Text(
                text = "Bienvenido",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Text(
                text = "Selecciona tu rol de ingreso:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = azulPrincipal,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val esPaciente = rolSeleccionado == "Paciente"
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (esPaciente) azulPrincipal else Color.White, RoundedCornerShape(10.dp))
                        .border(1.dp, if (esPaciente) azulPrincipal else Color.LightGray, RoundedCornerShape(10.dp))
                        .clickable { rolSeleccionado = "Paciente" }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "👤 Paciente",
                        color = if (esPaciente) Color.White else azulPrincipal,
                        fontWeight = FontWeight.Bold
                    )
                }

                val esDoctor = rolSeleccionado == "Doctor"
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (esDoctor) azulPrincipal else Color.White, RoundedCornerShape(10.dp))
                        .border(1.dp, if (esDoctor) azulPrincipal else Color.LightGray, RoundedCornerShape(10.dp))
                        .clickable { rolSeleccionado = "Doctor" }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🩺 Especialista",
                        color = if (esDoctor) Color.White else azulPrincipal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))


            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        cargando = true
                        auth.signInWithEmailAndPassword(email.trim(), password.trim())
                            .addOnSuccessListener {
                                cargando = false
                                Toast.makeText(context, "¡Ingreso exitoso!", Toast.LENGTH_SHORT).show()


                                if (rolSeleccionado == "Paciente") {
                                    navController.navigate("home_paciente") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    navController.navigate("home_doctor") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            }
                            .addOnFailureListener { error ->
                                cargando = false
                                Toast.makeText(context, "Error: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        Toast.makeText(context, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = azulClaro),
                enabled = !cargando
            ) {
                if (cargando) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("CONTINUAR", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}