package com.example.menteconecta

data class Cita(
    val id: String = "",
    val pacienteNombre: String = "",
    val doctorNombre: String = "",
    val fecha: String = "",
    val hora: String = "",
    val motivo: String = "",
    val disponible: Boolean = true,
)