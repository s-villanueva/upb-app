package com.example.myapplication.model

data class MateriaHorario(
    val Nombre_Materia: String,
    val Fecha_Inicio: String,
    val Fecha_Final: String,
    val Horario: String,
    val Aula: String,
    val Docente: String
)
data class HorariosResponse(
    val horarios: List<MateriaHorario>
)