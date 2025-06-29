package com.example.myapplication.model

data class RespuestaLogin(
    val message: String,
    val usuario: Usuario? = null
)

data class Usuario(
    val codigo: Long,
    val nombres: String,
    val apellidos: String,
    val correo: String
)