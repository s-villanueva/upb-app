package com.example.myapplication

data class Subject(
    val name: String,
    val startDate: String,
    val endDate: String,
    val time: String,
    val room: String,
    val professors: List<String>
)
