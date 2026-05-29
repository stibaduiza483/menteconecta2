package com.example.menteconecta

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseManager {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()


    val citasRef: DatabaseReference = database.getReference("citas")
    val horariosRef: DatabaseReference = database.getReference("horarios_doctores")


    fun guardarHorario(dia: String, inicio: String, fin: String, onResult: (Boolean) -> Unit) {
        val nuevoHorario = mapOf(
            "dia" to dia,
            "inicio" to inicio,
            "fin" to fin
        )

        horariosRef.push().setValue(nuevoHorario)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }


    fun obtenerCitasPorTipo(tipo: String, onResult: (List<Cita>) -> Unit, onError: (String) -> Unit) {

        citasRef.orderByChild("tipo").equalTo(tipo)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listaCitas = mutableListOf<Cita>()
                    for (child in snapshot.children) {
                        val cita = child.getValue(Cita::class.java)
                        if (cita != null) {
                            listaCitas.add(cita)
                        }
                    }
                    onResult(listaCitas)
                }

                override fun onCancelled(error: DatabaseError) {
                    onError(error.message)
                }
            })
    }
}