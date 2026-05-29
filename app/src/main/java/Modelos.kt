package com.example.menteconecta.models


data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val rol: String = "",
    val edad: Int = 0,
    val diagnostico: String = "",
    val enTratamiento: Boolean = false
)

data class Cita(
    val id: String = "",
    val idPaciente: String = "",
    val pacienteNombre: String = "",
    val idDoctor: String = "",
    val doctorNombre: String = "",
    val especialidad: String = "",
    val fecha: String = "",
    val hora: String = "",
    val motivo: String = "",
    val estado: String = "pendiente"
)

data class Formula(
    val id: String = "",
    val idPaciente: String = "",
    val idDoctor: String = "",
    val fechaEmision: String = "",
    val medicamentos: List<Medicamento> = emptyList(),
    val autorizada: Boolean = false
)

data class Medicamento(
    val nombre: String = "",
    val dosis: String = "",
    val frecuencia: String = ""
)