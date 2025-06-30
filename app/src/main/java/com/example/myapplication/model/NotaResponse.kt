package com.example.myapplication.model

data class NotaResponse(
    val materia: String,
    val parcial: String,
    val puntaje: Int,
    val faltas: Int
)